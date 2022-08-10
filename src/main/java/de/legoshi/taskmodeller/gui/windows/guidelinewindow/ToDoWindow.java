package de.legoshi.taskmodeller.gui.windows.guidelinewindow;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ToDoWindow extends VBox {

    private ToDoTitle toDoTitle;
    private ArrayList<ToDoElement> toDoElements;

    public ToDoWindow(String title) {
        this.toDoTitle = new ToDoTitle(title);
        this.toDoTitle.setOnMouseEntered((event) -> {
            toDoTitle.setStyle("-fx-background-color: rgb(243, 243, 231);");
        });
        this.toDoTitle.setOnMouseExited((event) -> {
            toDoTitle.setStyle("-fx-background-color: rgb(245, 235, 224);");
        });
        this.toDoElements = new ArrayList<>();
        VBox.setVgrow(this, Priority.ALWAYS);
        this.getChildren().add(this.toDoTitle);
    }

    public void addToDoElement(String task) {
        ToDoElement toDoElement = new ToDoElement(task);
        this.getChildren().add(toDoElement);
        this.toDoElements.add(toDoElement);
    }

}
