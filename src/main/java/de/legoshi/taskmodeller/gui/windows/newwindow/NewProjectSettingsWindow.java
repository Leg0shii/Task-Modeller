package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewProjectSettingsWindow extends Stage {

    public NewProjectSettingsWindow(ProjectWindow project) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        Label existingModelL = new Label("Existing Model");
        Label compositeModelL = new Label("Composite Model");
        Label envisionedModelL = new Label("Envisioned Model");

        VBox exModel = new VBox();
        exModel.getChildren().add(existingModelL);
        ArrayList<HBox> exHBoxes = generateDisplayThing(project.getExistentCount());
        exModel.getChildren().addAll(exHBoxes);

        VBox coModel = new VBox();
        coModel.getChildren().add(compositeModelL);
        ArrayList<HBox> coHBoxes = generateDisplayThing(project.getCompositeCount());
        coModel.getChildren().addAll(coHBoxes);

        VBox evModel = new VBox();
        evModel.getChildren().add(envisionedModelL);
        ArrayList<HBox> evHBoxes = generateDisplayThing(project.getEnvisionedCount());
        evModel.getChildren().addAll(evHBoxes);

        gridPane.add(exModel, 0, 0);
        gridPane.add(coModel, 1, 0);
        gridPane.add(evModel, 2, 0);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            NewProjectSaveWindow newProjectSaveWindow = new NewProjectSaveWindow(project, exHBoxes, coHBoxes, evHBoxes);
            newProjectSaveWindow.show();
            this.close();
        });

        gridPane.add(cancelBtn, 0, 3);
        gridPane.add(continueBtn, 1, 3);

        Scene scene = new Scene(gridPane, 700, 300);
        this.setScene(scene);
    }

    private ArrayList<HBox> generateDisplayThing(int count) {
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            HBox hBox = new HBox();
            TextField textField = new TextField("Task Model " + (i+1));
            ComboBox<String> comboBox = new ComboBox<>();
            ObservableList<String> options = FXCollections.observableArrayList("FREE", "CTT");
            comboBox.setValue("FREE");
            comboBox.setItems(options);

            hBox.getChildren().add(textField);
            hBox.getChildren().add(comboBox);
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

}
