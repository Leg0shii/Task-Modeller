package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.PWInitObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewSettingsWindow extends DialogWindow {

    public NewSettingsWindow(ProjectWindow project) {
        this.setTitle("PaintWindow Settings");

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

        this.gridPane.add(exModel, 0, 0);
        this.gridPane.add(coModel, 1, 0);
        this.gridPane.add(evModel, 2, 0);

        Button continueBtn = new Button("Continue");
        GridPane.setHalignment(continueBtn, HPos.RIGHT);
        continueBtn.setOnMouseClicked(mouseEvent -> {
            ArrayList<PWInitObject> exO = getPWObject(exHBoxes);
            ArrayList<PWInitObject> coO = getPWObject(coHBoxes);
            ArrayList<PWInitObject> evO = getPWObject(evHBoxes);
            SaveWindow newProjectSaveWindow = new SaveWindow(project, exO, coO, evO);
            newProjectSaveWindow.show();
            this.close();
        });
        this.gridPane.add(continueBtn, 2, 1);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());
        gridPane.add(cancelBtn, 0, 1, 2, 1);
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

    private ArrayList<PWInitObject> getPWObject(ArrayList<HBox> hBoxes) {
        ArrayList<PWInitObject> arrayList = new ArrayList<>();
        for (HBox hBox : hBoxes) {
            TextField textField = (TextField) hBox.getChildren().get(0);
            ComboBox<String> comboBox = (ComboBox<String>) hBox.getChildren().get(1);
            PWInitObject pwInitObject = new PWInitObject(textField.getText(), comboBox.getValue());
            arrayList.add(pwInitObject);
        }
        return arrayList;
    }

}
