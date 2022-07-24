package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewProjectCountWindow extends NewProject {

    public NewProjectCountWindow(ProjectWindow project) {
        Label existingL = new Label("Existing Model Count:");
        TextField existingTF = new TextField();
        this.gridPane.add(existingL, 0, 0);
        this.gridPane.add(existingTF, 1, 0);

        Label compositeL = new Label("Composite Model Count:");
        TextField compositeTF = new TextField();
        this.gridPane.add(compositeL, 0, 1);
        this.gridPane.add(compositeTF, 1, 1);

        Label envisionedL = new Label("Envisioned Model Count:");
        TextField envisionedTF = new TextField();
        this.gridPane.add(envisionedL, 0, 2);
        this.gridPane.add(envisionedTF, 1, 2);

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            try {
                project.setExistentCount(Integer.parseInt(existingTF.getText()));
                project.setCompositeCount(Integer.parseInt(compositeTF.getText()));
                project.setEnvisionedCount(Integer.parseInt(envisionedTF.getText()));
                new NewProjectSettingsWindow(project).show();
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                System.out.println("New Project ERROR");
            } finally {
                this.close();
            }
        });

        this.gridPane.add(continueBtn, 1, 3, 2, 1);
    }

}
