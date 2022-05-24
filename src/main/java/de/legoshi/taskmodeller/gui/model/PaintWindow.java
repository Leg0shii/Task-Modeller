package de.legoshi.taskmodeller.gui.model;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
public class PaintWindow {

    public ScrollPane scrollPane;
    public AnchorPane anchorPane;
    private final MainController mainController;

    public PaintWindow(MainController mainController) {
        this.mainController = mainController;
    }
    public void initialize() {
        anchorPane = new AnchorPane();
        anchorPane.minWidth(0);
        anchorPane.minHeight(0);
        anchorPane.setPrefWidth(500);
        anchorPane.setPrefHeight(500);
        anchorPane.maxWidth(Integer.MAX_VALUE);
        anchorPane.maxHeight(Integer.MAX_VALUE);
        anchorPane.getStyleClass().add("draw-pane-bg");

        scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("draw-pane");
        scrollPane.setPannable(true);
        scrollPane.setPadding(new Insets(15, 15, 15, 15));
        scrollPane.setContent(anchorPane);
        // gridPane.add(scrollPane, 0, 1, 2, 2);
        // gridPane.add(new Rectangle(500, 500, Color.BLACK), 0, 1, 2, 2);
    }

    public void addFirstWindow() {
        mainController.getGridPane().add(scrollPane, 0, 1, 2, 2);
    }

    public void addAnotherWindow(ArrayList<PaintWindow> arrayList) {
        //mainController.getGridPane().add(scrollPane, 2, 1, 2, 2);
        // mainController.getGridPane().getChildren().remove(0);
        GridPane gridPane = mainController.getGridPane();
        gridPane.getChildren().remove(2);

        gridPane.add(arrayList.get(0).getScrollPane(), 0, 1, 1, 2);
        gridPane.add(arrayList.get(1).getScrollPane(), 1, 1, 1, 2);

        // int i = 0;
        // for(PaintWindow w : arrayList) {
        //    gridPane.add(w.getScrollPane(), 2 * i, 1, 2-i, 2-i);
        //    i++;
        // }
    }
}
