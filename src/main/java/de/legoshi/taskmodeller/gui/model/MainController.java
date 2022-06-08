package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML public HBox itemPane;
    @FXML public GridPane gridPane;
    private PaintWindow selectedWindow;
    private ArrayList<PaintWindow> allWindows;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StandardItemBar standardItemBar = new StandardItemBar();
        standardItemBar.prepareItemBar();

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        this.allWindows = new ArrayList<>();

        for (Drawable d : standardItemBar.getItemBar()) {
            d.getShape().setOnMouseClicked(event -> {
                double scaleFactor = selectedWindow.getScaleFactor();
                Polygon poly = d.getDuplicate();
                poly.setScaleX(scaleFactor);
                poly.setScaleY(scaleFactor);
                selectedWindow.getAnchorPane().getChildren().add(poly);
                event.consume();
            });
            itemPane.getChildren().add(d.getShape());
        }

        selectedWindow = new PaintWindow(this);
        allWindows.add(selectedWindow);
        selectedWindow.initialize();
        selectedWindow.addFirstWindow();
    }

    public void zoomIn() {
        AnchorPane drawPane = selectedWindow.getAnchorPane();
        selectedWindow.setScaleFactor(selectedWindow.getScaleFactor()*1.2);
        scaleDrawPane(drawPane, 1.2);
    }

    public void zoomOut() {
        AnchorPane drawPane = selectedWindow.getAnchorPane();
        selectedWindow.setScaleFactor(selectedWindow.getScaleFactor()*0.8);
        scaleDrawPane(drawPane, 0.8);
    }

    private void scaleDrawPane(AnchorPane drawPane, double scalingFactor) {
        drawPane.setPrefHeight(drawPane.getPrefHeight()*scalingFactor);
        drawPane.setPrefWidth(drawPane.getPrefWidth()*scalingFactor);
        for(Node n :  drawPane.getChildren()) {
            n.setTranslateX(n.getTranslateX()*scalingFactor);
            n.setTranslateY(n.getTranslateY()*scalingFactor);
            n.setScaleX(n.getScaleX()*scalingFactor);
            n.setScaleY(n.getScaleY()*scalingFactor);
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void addWindow(ActionEvent actionEvent) {
        PaintWindow paintWindow = new PaintWindow(this);
        paintWindow.initialize();
        allWindows.add(paintWindow);
        paintWindow.addAnotherWindow(this.allWindows);
    }
}
