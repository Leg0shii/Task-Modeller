package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML public Pane drawPane;
    @FXML public HBox itemPane;

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
}
