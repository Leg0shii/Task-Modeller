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
        ToDoWindow toDoWindow = generateCTTToDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);

        toDoWindow = generateMISCoDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);

        toDoWindow = generateStandardToDo();
        this.toDoWindows.put(toDoWindow.getToDoTitle(), toDoWindow);

        // toDoWindow = generateNEWoDo();
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

    private ToDoWindow generateStandardToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("Standard Guide");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("Task 1");
        toDoWindow.addToDoElement("Task 2");
        toDoWindow.addToDoElement("Task 3");
        return toDoWindow;
    }

    private ToDoWindow generateCTTToDo() {
        ToDoWindow toDoWindow = new ToDoWindow("CTT Guide");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("CTT-Task 1");
        toDoWindow.addToDoElement("CTT-Task 2");
        toDoWindow.addToDoElement("CTT-Task 3");
        return toDoWindow;
    }

    private ToDoWindow generateMISCoDo() {
        ToDoWindow toDoWindow = new ToDoWindow("MISC Guide");
        toDoWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        toDoWindow.addToDoElement("MISC-Task 1");
        toDoWindow.addToDoElement("MISC-Task 2");
        toDoWindow.addToDoElement("MISC-Task 3");
        return toDoWindow;
    }

    private ToDoWindow generateNEWoDo() {
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
