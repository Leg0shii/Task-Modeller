package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.windows.NewProjectCountWindow;
import de.legoshi.taskmodeller.gui.model.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @Getter public static MainController mainController;

    @FXML public VBox mainFrame;

    @FXML public MenuBar menuBar;
    @FXML public ScrollPane contentPane;
    @FXML public HBox toolBar;

    public ProjectWindow project;

    private StandardItemBar standardItemBar;
    private CTTItemBar cttItemBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        this.standardItemBar = new StandardItemBar();
        this.cttItemBar = new CTTItemBar();

        this.contentPane.setHmax(500);
        this.contentPane.setVmax(500);

        this.project = new ProjectWindow();
        this.contentPane.setContent(project);
    }

    public void reloadItemBarWithModel(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;

        toolBar.getChildren().clear();
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                DrawnSymbol drawnSymbol = drawable.getDuplicate();
                project.getSelectedPaintWindow().addNode(drawnSymbol);
                event.consume();
            });
            toolBar.getChildren().add(drawable.getPolyShape());
        }
    }

    public void zoomIn() {
        scaleProjectView(1.2);
    }

    public void zoomOut() {
        scaleProjectView(0.8);
    }

    private void scaleProjectView(double scalingFactor) {
        GridPane drawPane = this.project;
        drawPane.setScaleX(drawPane.getScaleX()*scalingFactor);
        drawPane.setScaleY(drawPane.getScaleY()*scalingFactor);
    }

    public static MainController getInstance() {
        return mainController;
    }

    public void createProject() {
        NewProjectCountWindow newProjectCountWindow = new NewProjectCountWindow(this.project);
        newProjectCountWindow.show();
    }

    private void onRadioButtonChange() {
        // exchange the hotbars
    }

}
