package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.PWInitObject;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class SaveWindow extends DialogWindow {

    private Label errLabel;

    public SaveWindow(ProjectWindow project, ArrayList<PWInitObject> exO, ArrayList<PWInitObject> coO, ArrayList<PWInitObject> evO) {
        this.setTitle("Save Project");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project Files", "*.json"));
        File file = fileChooser.showSaveDialog(this);

        if (file == null) {
            this.close();
            return;
        }

        project.getWorkplace().setWorkplaceName(file.getName());
        project.getWorkplace().setSavePath(file.getAbsolutePath());

        try {
            project.getWorkplace().initWorkplace();
            project.getWorkplace().getChildren().clear();
            project.getWorkplace().getChildren().add(project);
            project.generatePaintWindows(exO, coO, evO);
            project.getWorkplace().setExistent(true);
            project.getWorkplace().saveWorkspace();
            this.close();
        } catch (Exception e) {
            this.errLabel = new Label();
            this.errLabel.setMinWidth(200);
            this.gridPane.add(errLabel, 0, 0);
            errLabel.setText("Something went wrong...");
            e.printStackTrace();
        }
    }

    public SaveWindow(ProjectWindow project) {
        this.setTitle("Save Project");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project Files", "*.json"));
        File file = fileChooser.showSaveDialog(this);

        if (file == null) {
            this.close();
            return;
        }

        project.getWorkplace().setWorkplaceName(file.getName());
        project.getWorkplace().setSavePath(file.getAbsolutePath());

        try {
            project.getWorkplace().saveWorkspace();
            this.close();
        } catch (Exception e) {
            this.errLabel = new Label();
            this.errLabel.setMinWidth(200);
            this.gridPane.add(errLabel, 0, 0);
            errLabel.setText("Something went wrong...");
            e.printStackTrace();
        }
    }

}
