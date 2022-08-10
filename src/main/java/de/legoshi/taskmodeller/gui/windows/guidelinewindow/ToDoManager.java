package de.legoshi.taskmodeller.gui.windows.guidelinewindow;

import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ToDoManager {

    private HashMap<ToDoTitle, ToDoWindow> toDoWindows;
    private ToDoWindow activeWindow;
    private final VBox todoBar;

    public ToDoManager(VBox todoBar) {
        this.toDoWindows = new HashMap<>();
        registerToDoWindow();
        registerTitleClick();

        Map.Entry<ToDoTitle, ToDoWindow> firstValue = toDoWindows.entrySet().stream().findFirst().get();
        this.activeWindow = firstValue.getValue();
        this.todoBar = todoBar;
        todoBar.getChildren().add(this.getActiveWindow().getToDoTitle());
        todoBar.getChildren().add(this.getActiveWindow());

        for (ToDoTitle title : toDoWindows.keySet()) {
            if (this.getActiveWindow().getToDoTitle() == title) continue;
            todoBar.getChildren().add(todoBar.getChildren().size(), title);
        }
    }

    public void registerToDoWindow() {
        ToDoWindow toDoWindow = generateStandardToDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);

        toDoWindow = generateCTTToDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);

        toDoWindow = generateMISCoDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);
    }

    private void registerTitleClick() {
        for (ToDoTitle toDoTitle : toDoWindows.keySet()) {
            toDoTitle.setOnMouseClicked(mouseEvent -> {
                ToDoWindow toDoWindow = toDoWindows.get(toDoTitle);
                if (this.activeWindow == toDoWindow) return;
                todoBar.getChildren().clear();

                this.activeWindow = toDoWindow;

                todoBar.getChildren().add(this.getActiveWindow().getToDoTitle());
                todoBar.getChildren().add(this.getActiveWindow());

                for (ToDoTitle title : toDoWindows.keySet()) {
                    if (this.getActiveWindow().getToDoTitle() == title) continue;
                    todoBar.getChildren().add(todoBar.getChildren().size(), title);
                }
            });
        }
    }

    private ToDoWindow generateStandardToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("Standard Guide");
        toDoWindow.addToDoElement("Task 1");
        toDoWindow.addToDoElement("Task 2");
        toDoWindow.addToDoElement("Task 3");
        return toDoWindow;
    }

    private ToDoWindow generateCTTToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("CTT Guide");
        toDoWindow.addToDoElement("CTT-Task 1");
        toDoWindow.addToDoElement("CTT-Task 2");
        toDoWindow.addToDoElement("CTT-Task 3");
        return toDoWindow;
    }

    private ToDoWindow generateMISCoDo() {
        ToDoWindow toDoWindow = new ToDoWindow("MISC Guide");
        toDoWindow.addToDoElement("MISC-Task 1");
        toDoWindow.addToDoElement("MISC-Task 2");
        toDoWindow.addToDoElement("MISC-Task 3");
        return toDoWindow;
    }

}
