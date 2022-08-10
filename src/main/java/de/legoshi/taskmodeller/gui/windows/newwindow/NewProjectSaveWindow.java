package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewProjectSaveWindow extends NewProject {

    public NewProjectSaveWindow(ProjectWindow project, ArrayList<HBox> exHBoxes, ArrayList<HBox> coHBoxes, ArrayList<HBox> evHBoxes) {
        this.setTitle("Save Project");

        Label savingName = new Label("Project Name: ");
        Label savingLocation = new Label("Saving Location:");

        TextField name = new TextField();
        TextField location = new TextField();

        this.gridPane.add(name, 1, 0);
        this.gridPane.add(location, 1, 1);

        this.gridPane.add(savingName, 0, 0);
        this.gridPane.add(savingLocation, 0, 1);

        Button continueBtn = new Button("Finish");
        GridPane.setHalignment(continueBtn, HPos.RIGHT);
        continueBtn.setOnMouseClicked(mouseEvent -> {
            project.generatePaintWindows(exHBoxes, coHBoxes, evHBoxes);
            // create save file
            this.close();
        });
        this.gridPane.add(continueBtn, 1, 2, 2, 1);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());
        gridPane.add(cancelBtn, 0, 2, 2, 1);
    }
}
