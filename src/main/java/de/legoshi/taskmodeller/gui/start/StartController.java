package de.legoshi.taskmodeller.gui.start;

import de.legoshi.taskmodeller.gui.model.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @FXML private ComboBox<String> startCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void onStartButtonClick(ActionEvent event) throws IOException {
        String selectedMode = startCB.getValue();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/legoshi/taskmodeller/main-view.fxml"));
        Parent page = fxmlLoader.load();
        Scene pageScene = new Scene(page);
        pageScene.getStylesheets().add("styles.css");
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // MainController mc = fxmlLoader.getController();
        // mc.getSelectLID().setText(selectedMode);
        appStage.setScene(pageScene);
        appStage.show();
    }
}