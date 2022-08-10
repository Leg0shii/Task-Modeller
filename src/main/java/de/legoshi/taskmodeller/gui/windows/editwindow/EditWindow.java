package de.legoshi.taskmodeller.gui.windows.editwindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
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

        Button closeBtn = new Button("Done");
        this.gridPane.add(closeBtn, 1, 10);
        GridPane.setHalignment(closeBtn, HPos.RIGHT);
        GridPane.setValignment(closeBtn, VPos.CENTER);
        closeBtn.setOnMouseClicked(mouseEvent -> this.close());

        this.deleteBtn = new Button("Delete");
        this.gridPane.add(deleteBtn, 0, 10);
        GridPane.setHalignment(deleteBtn, HPos.LEFT);
        GridPane.setValignment(deleteBtn, VPos.CENTER);
        deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());

        Scene dialogScene = new Scene(this.gridPane);
        dialogScene.getStylesheets().add("styles.css");
        this.setScene(dialogScene);
    }

    public abstract void onDelete();

}
