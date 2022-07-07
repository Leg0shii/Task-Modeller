package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemEditWindow extends Stage {

    private final DrawnSymbol drawnSymbol;

    public ItemEditWindow(DrawnSymbol drawnSymbol) {
        this.drawnSymbol = drawnSymbol;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Bearbeite Objekt");

        Polygon polygon = (Polygon) drawnSymbol.getChildren().get(0);
        Label label = (Label) drawnSymbol.getChildren().get(1);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        TextField textField = new TextField(label.getText());
        gridPane.add(new Text("Name: "), 0, 0);
        gridPane.add(textField, 1, 0);

        textField.textProperty().addListener((observableValue, s, t1) -> label.setText(t1));

        Slider slider = new Slider(0.4, 5, drawnSymbol.getScaleX());
        gridPane.add(new Text("Skalieren:"), 0, 1);
        gridPane.add(slider, 1, 1);
        slider.valueProperty().addListener((observableValue, number, t1) -> onScale(drawnSymbol, number.doubleValue(), t1.doubleValue()));

        ColorPicker colorPicker = new ColorPicker((Color) polygon.getFill());
        gridPane.add(colorPicker, 2, 0, 3, 3);
        colorPicker.valueProperty().addListener((observableValue, color, t1) -> polygon.setFill(t1));

        Button connectBtn = new Button("Verbindung");
        gridPane.add(connectBtn, 1, 3, 10, 1);
        connectBtn.setOnMouseClicked(mouseEvent -> onConnect());

        Button deleteBtn = new Button("Löschen");
        gridPane.add(deleteBtn, 1, 4, 10, 1);
        deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());

        Button closeBtn = new Button("Fertig");
        gridPane.add(closeBtn, 1, 10, 10, 1);
        closeBtn.setOnMouseClicked(mouseEvent -> this.close());

        Scene dialogScene = new Scene(gridPane, 400, 200);
        this.setScene(dialogScene);
    }

    private void onConnect() {
        MainController mainController = MainController.getInstance();
        for (DrawnSymbol dS : mainController.getProject().getSelectedPaintWindow().getDrawnNodes()) {
            dS.setAttemptsConnect(false);
        }
        drawnSymbol.setAttemptsConnect(true);
        this.close();
    }

    private void onScale(DrawnSymbol drawnSymbol, double number, double t1) {
        Polygon polygon = (Polygon) drawnSymbol.getChildren().get(0);
        Label label = (Label) drawnSymbol.getChildren().get(1);

        drawnSymbol.setScaleX(drawnSymbol.getScaleX() + (t1 - number));
        drawnSymbol.setScaleY(drawnSymbol.getScaleY() + (t1 - number));
        label.setFont(new Font("Arial", 12/drawnSymbol.getScaleX()));

        // polyShape.getStrokeWidth()
        polygon.setStrokeWidth(3/drawnSymbol.getScaleX());
    }

    private void onDelete() {
        MainController mainController = MainController.getInstance();
        PaintWindow paintWindow = mainController.getProject().getSelectedPaintWindow();
        paintWindow.removeNode(this.drawnSymbol);
        this.close();
    }

}
