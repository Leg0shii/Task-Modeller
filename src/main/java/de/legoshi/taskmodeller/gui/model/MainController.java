package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public ScrollPane scrollPane;
    @FXML
    public AnchorPane drawPane;
    @FXML
    public HBox itemPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StandardItemBar standardItemBar = new StandardItemBar();
        standardItemBar.prepareItemBar();

        for (Drawable d : standardItemBar.getItemBar()) {
            d.getShape().setOnMouseClicked(event -> {
                drawPane.getChildren().add(d.getDuplicate());
                event.consume();
            });
            itemPane.getChildren().add(d.getShape());
        }
    }

    public void zoomIn(ActionEvent actionEvent) {
        drawPane.setPrefHeight(drawPane.getPrefHeight()+200);
        drawPane.setPrefWidth(drawPane.getPrefWidth()+200);
        System.out.println("X: " + drawPane.getHeight()*drawPane.getScaleX());
    }

    public void zoomOut(ActionEvent actionEvent) {
        drawPane.setPrefHeight(drawPane.getPrefHeight()-200);
        drawPane.setPrefWidth(drawPane.getPrefWidth()-200);
        System.out.println("X: " + drawPane.getHeight()*drawPane.getScaleX());
    }
}
