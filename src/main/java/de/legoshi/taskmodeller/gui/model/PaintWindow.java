package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.util.ModelType;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PaintWindow {

    public ScrollPane scrollPane;
    public AnchorPane anchorPane;
    private double scaleFactor;
    private final MainController mainController;
    private final ModelType modelType;

    public PaintWindow(MainController mainController, ModelType modelType) {
        this.mainController = mainController;
        this.scaleFactor = 1;
        this.modelType = modelType;
    }
    public void initialize() {
        scrollPane = new ScrollPane();

        anchorPane = new AnchorPane();
        anchorPane.minWidth(0);
        anchorPane.minHeight(0);
        anchorPane.setPrefWidth(500);
        anchorPane.setPrefHeight(500);
        anchorPane.maxWidth(Integer.MAX_VALUE);
        anchorPane.maxHeight(Integer.MAX_VALUE);
        anchorPane.getStyleClass().add("draw-pane-bg");

        scrollPane.getStyleClass().add("draw-pane");
        scrollPane.setPadding(new Insets(15, 15, 15, 15));
        scrollPane.setContent(anchorPane);

        scrollPane.setOnMouseClicked(mouseEvent -> {
            mainController.setSelectedWindow(this);
            for(PaintWindow paintWindow : mainController.getAllWindows()) {
                paintWindow.getScrollPane().getStyleClass().remove("active-pane");
                mainController.onItemBarReload(this.modelType);
            }
            scrollPane.getStyleClass().add("active-pane");
        });
    }

    public void addFirstWindow() {
        mainController.getGridPane().add(scrollPane, 0, 1, 2, 2);
    }

    public void addAnotherWindow(ArrayList<PaintWindow> arrayList) {
        GridPane gridPane = mainController.getGridPane();
        gridPane.getChildren().remove(2);

        gridPane.add(arrayList.get(0).getScrollPane(), 0, 1, 1, 2);
        gridPane.add(arrayList.get(1).getScrollPane(), 1, 1, 1, 2);
    }
}
