package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.Getter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
public class MainController implements Initializable {

    @FXML public HBox itemPane;
    @FXML public GridPane gridPane;
    private PaintWindow selectedWindow;
    private ArrayList<PaintWindow> allWindows;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StandardItemBar standardItemBar = new StandardItemBar();
        standardItemBar.prepareItemBar();

        this.allWindows = new ArrayList<>();

        for (Drawable d : standardItemBar.getItemBar()) {
            d.getShape().setOnMouseClicked(event -> {
                selectedWindow.getAnchorPane().getChildren().add(d.getDuplicate());
                event.consume();
            });
            itemPane.getChildren().add(d.getShape());
        }

        selectedWindow = new PaintWindow(this);
        allWindows.add(selectedWindow);
        selectedWindow.initialize();
        selectedWindow.addFirstWindow();
    }

    public void zoomIn(ActionEvent actionEvent) {
        AnchorPane drawPane = selectedWindow.getAnchorPane();
        drawPane.setPrefHeight(drawPane.getPrefHeight()+200);
        drawPane.setPrefWidth(drawPane.getPrefWidth()+200);
    }

    public void zoomOut(ActionEvent actionEvent) {
        AnchorPane drawPane = selectedWindow.getAnchorPane();
        drawPane.setPrefHeight(drawPane.getPrefHeight()-200);
        drawPane.setPrefWidth(drawPane.getPrefWidth()-200);
    }

    public void addWindow(ActionEvent actionEvent) {
        PaintWindow paintWindow = new PaintWindow(this);
        paintWindow.initialize();
        allWindows.add(paintWindow);
        paintWindow.addAnotherWindow(this.allWindows);
    }
}
