package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.ConnectionItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.MiscItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import de.legoshi.taskmodeller.gui.model.windows.NewProjectCountWindow;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.model.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @Getter
    public static MainController mainController;

    public VBox mainFrame;

    public MenuBar menuBar;
    public ScrollPane contentPane;
    private Group scrollPaneContent;
    public HBox toolBar;

    public ProjectWindow project;

    public RadioButton standardRBTN;
    public RadioButton connectionRBTN;
    public RadioButton miscRBTN;

    private StandardItemBar standardItemBar;
    private CTTItemBar cttItemBar;
    private ConnectionItemBar connectionItemBar;
    private MiscItemBar miscItemBar;
    private Group commentGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        this.standardItemBar = new StandardItemBar();
        this.cttItemBar = new CTTItemBar();
        this.connectionItemBar = new ConnectionItemBar();
        this.miscItemBar = new MiscItemBar();

        this.project = new ProjectWindow();
        this.commentGroup = new Group(project);
        this.scrollPaneContent = new Group(commentGroup);
        this.contentPane.setContent(this.scrollPaneContent);

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
        else if (modelType.equals(ModelType.CONN)) itemBar = connectionItemBar.itemBar;
        else if (modelType.equals(ModelType.MISC)) itemBar = miscItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;


        PaintWindow selectedWindow = project.getSelectedPaintWindow();
        toolBar.getChildren().clear();

        // handle this
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                if (modelType.equals(ModelType.FREE)) {
                    DrawnSymbol drawnSymbol = drawable.getDuplicate(false);
                    selectedWindow.addNode(drawnSymbol);
                    project.setSelectedSymbol(drawnSymbol);
                    event.consume();
                } else if (modelType.equals(ModelType.CTT)) {
                    DrawnSymbol parent = project.getSelectedSymbol();
                    DrawnSymbol child = drawable.getDuplicate(false);
                    if (parent == null) {
                        selectedWindow.addNode(child);
                        project.setSelectedSymbol(child);
                        return;
                    }
                    selectedWindow.addNode(child);
                    project.setSelectedSymbol(child);
                    NodeConnection nodeConnection = new NodeConnection(parent, child);
                    selectedWindow.addConnection(nodeConnection);
                } else if (modelType.equals(ModelType.MISC)) {
                    DrawnSymbol drawnSymbol = drawable.getDuplicate(true);
                    this.commentGroup.getChildren().add(drawnSymbol);
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
        Group group = this.commentGroup;
        group.setScaleX(group.getScaleX() * scalingFactor);
        group.setScaleY(group.getScaleY() * scalingFactor);
    }

    public static MainController getInstance() {
        return mainController;
    }

    public void createProject() {
        NewProjectCountWindow newProjectCountWindow = new NewProjectCountWindow(this.project);
        newProjectCountWindow.show();
    }

    @FXML
    private void onRadioButtonChange() {
        ModelType modelType = ModelType.MISC;
        if (standardRBTN.isSelected()) {
            if (this.project.getSelectedPaintWindow() == null) return;
            modelType = this.project.getSelectedPaintWindow().getModelType();
        }
        else if (connectionRBTN.isSelected()) modelType = ModelType.CONN;
        reloadItemBarWithModel(modelType);
    }

}
