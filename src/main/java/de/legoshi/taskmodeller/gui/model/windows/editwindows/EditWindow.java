package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class EditWindow<T> extends Stage {

    public GridPane gridPane;

    public final T item;
    public Button deleteBtn;

    public EditWindow(T item, String title) {
        this.item = item;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(title);

        this.gridPane = new GridPane();
        this.gridPane.setPadding(new Insets(10, 10, 10, 10));
        this.gridPane.setHgap(5);
        this.gridPane.setVgap(5);

        Button closeBtn = new Button("Fertig");
        this.gridPane.add(closeBtn, 0, 10);
        closeBtn.setOnMouseClicked(mouseEvent -> this.close());

        this.deleteBtn = new Button("Löschen");
        this.gridPane.add(deleteBtn, 1, 10);

        Scene dialogScene = new Scene(this.gridPane, 400, 200);
        this.setScene(dialogScene);
    }

    public abstract void onDelete();

}
