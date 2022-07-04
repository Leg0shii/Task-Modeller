package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.gui.model.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LineEditWindow extends Stage {

    private final NodeConnection nodeConnection;

    public LineEditWindow(NodeConnection nodeConnection) {
        this.nodeConnection = nodeConnection;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Bearbeite Linie");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label checkBoxLabel = new Label("Auto-Positionierung");
        gridPane.add(checkBoxLabel, 0, 0);

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(this.nodeConnection.isPosition());
        gridPane.add(checkBox, 1, 0);
        checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> this.nodeConnection.setPosition(t1));

        Button delBtn = new Button("Löschen");
        gridPane.add(delBtn, 3, 0);
        delBtn.setOnMouseClicked( mouseEvent -> onDelete());

        Button doneBtn = new Button("Fertig");
        gridPane.add(doneBtn, 10, 0);
        doneBtn.setOnMouseClicked( mouseEvent -> this.close());

        Scene dialogScene = new Scene(gridPane, 400, 200);
        this.setScene(dialogScene);
    }

    private void onDelete() {
        MainController mainController = MainController.getInstance();
        DrawArea drawArea = mainController.getSelectedPaintWindow().getDrawArea();
        drawArea.removeConnection(this.nodeConnection);
        this.close();
    }

}
