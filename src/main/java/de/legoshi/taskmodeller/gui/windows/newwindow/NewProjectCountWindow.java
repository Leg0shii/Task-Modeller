package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NewProjectCountWindow extends NewProject {

    public NewProjectCountWindow(ProjectWindow project) {
        this.setTitle("Set Paintwindow Count");

        Label existingL = new Label("Existing Model Count:");
        TextField existingTF = new TextField("1");
        this.gridPane.add(existingL, 0, 0);
        this.gridPane.add(existingTF, 1, 0);

        Label compositeL = new Label("Composite Model Count:");
        TextField compositeTF = new TextField("1");
        this.gridPane.add(compositeL, 0, 1);
        this.gridPane.add(compositeTF, 1, 1);

        Label envisionedL = new Label("Envisioned Model Count:");
        TextField envisionedTF = new TextField("1");
        this.gridPane.add(envisionedL, 0, 2);
        this.gridPane.add(envisionedTF, 1, 2);

        Label errLabel = new Label("");
        errLabel.setMinWidth(200);
        this.gridPane.add(errLabel, 0, 3);

        Button continueBtn = new Button("Continue");
        GridPane.setHalignment(continueBtn, HPos.RIGHT);
        continueBtn.setOnMouseClicked(mouseEvent -> {
            try {
                project.setExistentCount(Integer.parseInt(existingTF.getText()));
                project.setCompositeCount(Integer.parseInt(compositeTF.getText()));
                project.setEnvisionedCount(Integer.parseInt(envisionedTF.getText()));
                new NewProjectSettingsWindow(project).show();
                this.close();
            } catch (NumberFormatException exception) {
                errLabel.setText("!!!Please use integer values!!!");
                existingTF.setText("");
                compositeTF.setText("");
                envisionedTF.setText("");
            }
        });
        this.gridPane.add(continueBtn, 1, 4, 2, 1);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());
        gridPane.add(cancelBtn, 0, 4, 2, 1);
    }

}
