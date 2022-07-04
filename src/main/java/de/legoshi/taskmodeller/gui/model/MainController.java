package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML public GridPane gridPane;
    @FXML public HBox itemPane;

    @Getter public static MainController mainController;

    private ArrayList<PaintWindow> allWindows = new ArrayList<>();
    @Getter private PaintWindow selectedPaintWindow;

    private final StandardItemBar standardItemBar = new StandardItemBar();
    private final CTTItemBar cttItemBar = new CTTItemBar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        reloadItemBarWithModel(ModelType.CTT);
        this.selectedPaintWindow = new PaintWindow(this, ModelType.CTT);
        addFirstPaintWindow(this.selectedPaintWindow);
    }

    public void reloadItemBarWithModel(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;

        itemPane.getChildren().clear();
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                DrawnSymbol drawnSymbol = drawable.getDuplicate();
                selectedPaintWindow.getDrawArea().addNode(drawnSymbol);
                event.consume();
            });
            itemPane.getChildren().add(drawable.getPolyShape());
        }
    }

    private void addWindow(PaintWindow paintWindow) {
        this.allWindows.add(paintWindow);
    }

    private void addFirstPaintWindow(PaintWindow paintWindow) {
        getGridPane().add(paintWindow, 0, 1, 2, 2);
        addWindow(paintWindow);
    }

    @FXML
    public void onAddAnotherPaintWindow() {
        addWindow(new PaintWindow(this, ModelType.FREE));

        gridPane.getChildren().remove(2);
        gridPane.add(allWindows.get(0), 0, 1, 1, 2);
        gridPane.add(allWindows.get(1), 1, 1, 1, 2);
    }

    public void zoomIn() {
        AnchorPane drawPane = selectedPaintWindow.getDrawArea();
        scaleDrawPane(drawPane, 1.2);
    }

    public void zoomOut() {
        AnchorPane drawPane = selectedPaintWindow.getDrawArea();
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

    public static MainController getInstance() {
        return mainController;
    }

}
