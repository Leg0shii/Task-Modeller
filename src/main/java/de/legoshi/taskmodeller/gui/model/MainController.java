package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.ItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import de.legoshi.taskmodeller.util.ModelType;
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
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML public HBox itemPane;
    @FXML public GridPane gridPane;
    private PaintWindow selectedWindow;
    private ArrayList<PaintWindow> allWindows = new ArrayList<>();

    private StandardItemBar standardItemBar = new StandardItemBar();
    private CTTItemBar cttItemBar = new CTTItemBar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        onItemBarReload(ModelType.CTT);

        selectedWindow = new PaintWindow(this, ModelType.CTT);
        allWindows.add(selectedWindow);
        selectedWindow.initialize();
        selectedWindow.addFirstWindow();
    }

    public void onItemBarReload(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;

        itemPane.getChildren().clear();
        for (Drawable d : itemBar) {
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

    public void addWindow() {
        PaintWindow paintWindow = new PaintWindow(this, ModelType.FREE);
        paintWindow.initialize();
        allWindows.add(paintWindow);
        paintWindow.addAnotherWindow(this.allWindows);
    }
}
