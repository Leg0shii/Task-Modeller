package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import de.legoshi.taskmodeller.gui.model.windows.NewProjectCountWindow;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.model.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @Getter
    public static MainController mainController;

    @FXML
    public VBox mainFrame;

    @FXML
    public MenuBar menuBar;
    @FXML
    public ScrollPane contentPane;
    @FXML
    public HBox toolBar;

    public ProjectWindow project;

    private StandardItemBar standardItemBar;
    private CTTItemBar cttItemBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        this.standardItemBar = new StandardItemBar();
        this.cttItemBar = new CTTItemBar();

        this.project = new ProjectWindow();
        this.contentPane.setContent(new Group(project));

        contentPane.addEventFilter(ScrollEvent.ANY, (scrollEvent -> {
                if (!scrollEvent.isShiftDown()) return;

                double zoomFactor = 1.05;
                double deltaX = scrollEvent.getDeltaX();
                if (deltaX == 0) deltaX = scrollEvent.getDeltaY();
                if (deltaX < 0) zoomFactor = 2.0 - zoomFactor;

                this.scaleProjectView(zoomFactor);
            scrollEvent.consume();
        }));
    }

    public void reloadItemBarWithModel(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;

        toolBar.getChildren().clear();
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                PaintWindow selectedWindow = project.getSelectedPaintWindow();

                if (modelType.equals(ModelType.FREE)) {
                    DrawnSymbol drawnSymbol = drawable.getDuplicate();
                    selectedWindow.addNode(drawnSymbol);
                    project.setSelectedSymbol(drawnSymbol);
                    event.consume();
                } else if (modelType.equals(ModelType.CTT)) {
                    DrawnSymbol parent = project.getSelectedSymbol();
                    DrawnSymbol child = drawable.getDuplicate();

                    if (parent == null) {
                        selectedWindow.addNode(child);
                        project.setSelectedSymbol(child);
                        return;
                    }

                    selectedWindow.addNode(child);
                    project.setSelectedSymbol(child);
                    NodeConnection nodeConnection = new NodeConnection(parent, child);
                    nodeConnection.setPosition(true);
                    selectedWindow.addConnection(nodeConnection);
                }
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
        drawPane.setScaleX(drawPane.getScaleX() * scalingFactor);
        drawPane.setScaleY(drawPane.getScaleY() * scalingFactor);
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
