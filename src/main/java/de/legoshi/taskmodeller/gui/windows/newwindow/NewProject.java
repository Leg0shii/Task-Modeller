package de.legoshi.taskmodeller.gui.windows.newwindow;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewProject extends Stage {

    public final GridPane gridPane;

    public NewProject() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> this.close());

        gridPane.add(cancelBtn, 0, 3, 2, 1);

        gridPane.autosize();
        Scene scene = new Scene(gridPane, 400, 200);
        this.setScene(scene);
    }

}
