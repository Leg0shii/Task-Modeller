package de.legoshi.taskmodeller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/de/legoshi/taskmodeller/main-view.fxml"));
        Parent page = fxmlLoader.load();
        Scene pageScene = new Scene(page);
        stage.setTitle("Task Modeller");
        pageScene.getStylesheets().add("styles.css");
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(pageScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}