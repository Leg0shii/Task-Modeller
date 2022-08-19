package de.legoshi.taskmodeller.gui.windows.guidelinewindow;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    private Color color3 = Color.rgb(245, 235, 224);
    private Color color4 = Color.rgb(227, 213, 202);

    public ToDoManager(VBox todoBar) {
        this.toDoWindows = new HashMap<>();
        registerToDoWindow();
        registerTitleClick();

        Map.Entry<ToDoTitle, ToDoWindow> firstValue = toDoWindows.entrySet().stream().findFirst().get();
        this.activeWindow = firstValue.getValue();
        this.todoBar = todoBar;
        todoBar.getChildren().add(this.getActiveWindow().getToDoTitle());
        this.getActiveWindow().getToDoTitle().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 2, 0))));
        todoBar.getChildren().add(this.getActiveWindow());

        for (ToDoTitle title : toDoWindows.keySet()) {
            if (this.getActiveWindow().getToDoTitle() == title) continue;
            todoBar.getChildren().add(todoBar.getChildren().size(), title);
        }
    }

    public void registerToDoWindow() {
        ToDoWindow toDoWindowEnv = generateEnvisionedToDo();
        ToDoWindow toDoWindowCom = generateCompositeToDo();
        ToDoWindow toDoWindowEx = generateExistingToDo();
        // ToDoWindow toDoWindow = generateNEWToDo();

        this.toDoWindows.put(toDoWindowEnv.getToDoTitle(), toDoWindowEnv);
        this.toDoWindows.put(toDoWindowCom.getToDoTitle(), toDoWindowCom);
        this.toDoWindows.put(toDoWindowEx.getToDoTitle(), toDoWindowEx);
        // this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);
    }

    private void registerTitleClick() {
        for (ToDoTitle toDoTitle : toDoWindows.keySet()) {
            toDoTitle.setOnMouseClicked(mouseEvent -> {
                ToDoWindow toDoWindow = toDoWindows.get(toDoTitle);
                if (this.activeWindow == toDoWindow) return;
                todoBar.getChildren().clear();

                this.activeWindow = toDoWindow;

                todoBar.getChildren().add(this.activeWindow.getToDoTitle());
                this.activeWindow.getToDoTitle().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 2, 0))));
                todoBar.getChildren().add(this.activeWindow);

                for (ToDoTitle title : toDoWindows.keySet()) {
                    if (this.activeWindow.getToDoTitle() == title) continue;
                    title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0))));
                    todoBar.getChildren().add(todoBar.getChildren().size(), title);
                }
            });
        }
    }

    private ToDoWindow generateExistingToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("Existing Model");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("Identify characteristics of specific tasks");
        toDoWindow.addToDoElement("Analyse many different users performing each task");
        toDoWindow.addToDoElement("Produce a task description for each user on each task");
        return toDoWindow;
    }

    private ToDoWindow generateCompositeToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("Composite Model");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("From each task description for the same goal, produce a composite task model \n" +
                "which includes all the different ways of achieving the goal ");
        toDoWindow.addToDoElement("Identify all the different ways of achieving the same goal");
        toDoWindow.addToDoElement("Resolve conflicting descriptions");
        toDoWindow.addToDoElement("Identify optional aspects of a task");
        toDoWindow.addToDoElement("Identify compulsory aspects of a task");
        toDoWindow.addToDoElement("Identify commonalities of behaviour, patterns of behaviour and common \n" +
                "objects across the different tasks");
        toDoWindow.addToDoElement("Identify constraints and dependencies across tasks");
        toDoWindow.addToDoElement("Identify the different objects and typical instances of objects where there are a \n" +
                "number of different examples of the same object across the different tasks");
        return toDoWindow;
    }

    private ToDoWindow generateEnvisionedToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("Envisioned Model");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("Identify any tasks that can be avoided or that are unnecessary");
        toDoWindow.addToDoElement("Identify any tasks that can be carried out completely by the computer");
        toDoWindow.addToDoElement("Identify any tasks that can only be carried out by the user");

        toDoWindow.addToDoElement("Identify where users and the computer will need to interact to carry out a \n" +
                "task");
        toDoWindow.addToDoElement("Identify where sequences of activity can be made easier to perform");
        return toDoWindow;
    }

    private ToDoWindow generateNEWToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("NEW Guide");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("NEW-Task 1");
        toDoWindow.addToDoElement("NEW-Task 2");
        toDoWindow.addToDoElement("NEW-Task 3");
        toDoWindow.addToDoElement("NEW-Task 4");
        toDoWindow.addToDoElement("NEW-Task 5");
        toDoWindow.addToDoElement("NEW-Task 6");
        return toDoWindow;
    }

}
