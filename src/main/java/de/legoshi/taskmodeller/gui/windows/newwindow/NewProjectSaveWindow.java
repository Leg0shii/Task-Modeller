package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewProjectSaveWindow extends Stage {

    public NewProjectSaveWindow(ProjectWindow project, ArrayList<HBox> exHBoxes, ArrayList<HBox> coHBoxes, ArrayList<HBox> evHBoxes) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        TextField name = new TextField();
        TextField location = new TextField();

        gridPane.add(name, 0, 0);
        gridPane.add(location, 0, 1);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());

        Button continueBtn = new Button("Finish");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            project.generatePaintWindows(exHBoxes, coHBoxes, evHBoxes);
            // create save file
            this.close();
        });

        gridPane.add(cancelBtn, 0, 2);
        gridPane.add(continueBtn, 1, 2);

        Scene scene = new Scene(gridPane, 700, 300);
        this.setScene(scene);
    }
}
