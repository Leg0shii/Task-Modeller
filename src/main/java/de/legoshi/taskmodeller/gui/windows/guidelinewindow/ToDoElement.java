package de.legoshi.taskmodeller.gui.windows.guidelinewindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ToDoElement extends HBox {

    private String text;

    public ToDoElement(String text) {
        this.text = " ⚫ " + text;

        Label task = new Label(this.text);
        task.setWrapText(true);
        task.setMinWidth(150);
        CheckBox checkBox = new CheckBox();
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(task, checkBox);
    }

}
