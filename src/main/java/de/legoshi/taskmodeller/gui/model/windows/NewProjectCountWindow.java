package de.legoshi.taskmodeller.gui.model.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewProjectCountWindow extends Stage {

    public NewProjectCountWindow(ProjectWindow project) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        Label existingL = new Label("Existing Model Count:");
        TextField existingTF = new TextField();
        gridPane.add(existingL, 0, 0);
        gridPane.add(existingTF, 1, 0);

        Label compositeL = new Label("Composite Model Count:");
        TextField compositeTF = new TextField();
        gridPane.add(compositeL, 0, 1);
        gridPane.add(compositeTF, 1, 1);

        Label envisionedL = new Label("Envisioned Model Count:");
        TextField envisionedTF = new TextField();
        gridPane.add(envisionedL, 0, 2);
        gridPane.add(envisionedTF, 1, 2);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            int existentCount;
            int compositeCount;
            int envisionedCount;

            try {
                existentCount = Integer.parseInt(existingTF.getText());
                compositeCount = Integer.parseInt(compositeTF.getText());
                envisionedCount = Integer.parseInt(envisionedTF.getText());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                System.out.println("New Project ERROR");
                this.close();
                return;
            }

            project.setExistentCount(existentCount);
            project.setCompositeCount(compositeCount);
            project.setEnvisionedCount(envisionedCount);

            NewProjectSettingsWindow newProjectSettingsWindow = new NewProjectSettingsWindow(project);
            newProjectSettingsWindow.show();
            this.close();
        });

        gridPane.add(cancelBtn, 0, 3, 2, 1);
        gridPane.add(continueBtn, 1, 3, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        this.setScene(scene);
    }

}
