package de.legoshi.taskmodeller.gui.windows.guidelinewindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ToDoTitle extends Label {

    private String title;

    public ToDoTitle(String title) {
        this.title = title;
        this.setAlignment(Pos.BASELINE_CENTER);
        this.setFont(new Font("Arial", 20));
        this.setText(title);
        this.setHeight(50);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setStyle("-fx-border-color: black;");
        this.setPadding(new Insets(10, 10, 10, 10));
    }

}
