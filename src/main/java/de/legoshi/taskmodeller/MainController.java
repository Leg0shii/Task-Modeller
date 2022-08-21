package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.itembar.ItemBarManager;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.CTTConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
import de.legoshi.taskmodeller.gui.windows.dialogwindow.*;
import de.legoshi.taskmodeller.gui.windows.guidelinewindow.ToDoManager;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.NodesHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    public VBox mainFrame;

    public MenuBar menuBar;

    public ScrollPane contentPane;

    public VBox todoBar;
    public HBox toolBarHolder;
    public VBox itemBarSelector;
    public HBox middleHolder;
    public AnchorPane scrollPaneContent;
    private Workplace workplace;
    public HBox toolBar;
    private Group wGroup;

    private ToDoManager toDoManager;

    public RadioButton standardRBTN;
    public RadioButton connectionRBTN;
    public RadioButton miscRBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ItemBarManager itemBarManager = new ItemBarManager(toolBar);
        this.workplace = new Workplace(itemBarManager, contentPane);
        this.wGroup = new Group(workplace);
        this.contentPane.setContent(wGroup);
        this.toDoManager = new ToDoManager(todoBar);

        contentPane.addEventFilter(ScrollEvent.ANY, (scrollEvent -> {
            if (!scrollEvent.isShiftDown()) return;
            if (!workplace.isExistent()) return;

            double zoomFactor = 1.05;
            double deltaX = scrollEvent.getDeltaX();
            if (deltaX == 0) deltaX = scrollEvent.getDeltaY();
            if (deltaX < 0) zoomFactor = 2.0 - zoomFactor;

            this.scaleProjectView(zoomFactor);
            scrollEvent.consume();
        }));

        contentPane.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            if (keyEvent.isControlDown()) {
                if (keyEvent.getCode().equals(KeyCode.C)) onCopyNodes();
                if (keyEvent.getCode().equals(KeyCode.V)) onPasteNodes();
            }
        }));

        colorNodes();
    }

    public void zoomIn() {
        scaleProjectView(1.2);
    }

    public void zoomOut() {
        scaleProjectView(0.8);
    }

    private void scaleProjectView(double scalingFactor) {
        workplace.setScaleX(workplace.getScaleX() * scalingFactor);
        workplace.setScaleY(workplace.getScaleY() * scalingFactor);
    }

    public void createProject() {
        new NewCountWindow(workplace.getProjectWindow()).show();
    }

    @FXML
    private void onRadioButtonChange() {
        if (!workplace.isExistent()) return;

        PaintWindow selectedWindow = workplace.getSelectedPaintWindow();
        ModelType modelType = ModelType.MISC;
        if (standardRBTN.isSelected()) {
            if (selectedWindow == null) return;
            modelType = selectedWindow.getModelType();
            workplace.setPWSelected(true);
        } else workplace.setPWSelected(false);
        workplace.getItemBarManager().reloadItemBarWithModel(selectedWindow, modelType);
    }

    private void onCopyNodes() {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();

        workplace.getCopiedSymbols().clear();
        workplace.getCopiedConnections().clear();

        workplace.setCopiedFromModelType(paintWindow.getModelType());
        workplace.getCopiedSymbols().addAll(paintWindow.getSelectedNodes());
        workplace.getCopiedConnections().addAll(paintWindow.getSelectedConnections());
    }

    private void onPasteNodes() {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        ArrayList<ModelNode> newSymbols = new ArrayList<>();
        if (!workplace.getCopiedFromModelType().equals(paintWindow.getModelType())) return;
        for (ModelNode modelNode : workplace.getCopiedSymbols()) {
            ModelNode mN = (ModelNode) NodesHelper.getDuplicate(workplace, modelNode);
            paintWindow.addNodeToCanvas(mN);
            newSymbols.add(mN);
            NodesHelper.applyAttributes(modelNode, mN);
        }

        for (Connection currMCN : workplace.getCopiedConnections()) {
            ModelNode firstNode = null;
            ModelNode secondNode = null;
            for (ModelNode copiedNode : newSymbols) {
                ModelNode firstConNode = currMCN.getNode1();
                ModelNode secondConNode = currMCN.getNode2();
                if (firstConNode.getTranslateX() == copiedNode.getTranslateX() && firstConNode.getTranslateY() == copiedNode.getTranslateY()
                || secondConNode.getTranslateX() == copiedNode.getTranslateX() && secondConNode.getTranslateY() == copiedNode.getTranslateY()) {
                    if (firstNode == null) firstNode = copiedNode;
                    else secondNode = copiedNode;
                }
            }
            Connection mNC;
            if (currMCN instanceof NormalConnection) mNC = NormalConnection.generateShape(workplace, firstNode, secondNode);
            else if (currMCN instanceof CTTConnection) mNC = CTTConnection.generateShape(workplace, firstNode, secondNode);
            else mNC = GroupingConnection.generateShape(workplace, firstNode, secondNode);

            mNC.getLabel().setText(currMCN.getLabel().getText());
            mNC.setStroke(currMCN.getStroke());
            mNC.setStrokeWidth(currMCN.getStrokeWidth());
            paintWindow.addConnection(mNC);
        }
    }

    private void colorNodes() {
        Color color1 = Color.rgb(237, 237, 233);
        Color color2 = Color.rgb(214, 204, 194);
        Color color3 = Color.rgb(245, 235, 224);
        Color color4 = Color.rgb(227, 213, 202);
        Color color5 = Color.rgb(213, 189, 175);

        this.menuBar.setBackground(new Background(new BackgroundFill(color5, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        this.menuBar.getMenus().get(1).setStyle("-");

        this.toolBar.setBackground(new Background(new BackgroundFill(color5, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        this.toolBarHolder.setBackground(new Background(new BackgroundFill(color5, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));

        this.itemBarSelector.setBackground(new Background(new BackgroundFill(color5, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        this.itemBarSelector.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 0, 1))));

        this.middleHolder.setBackground(new Background(new BackgroundFill(color3, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        this.contentPane.setStyle("-fx-focus-color: transparent;");
        this.contentPane.setStyle("-fx-border-color: black;");

        this.toolBarHolder.setStyle("-fx-border-width: 1px");
        this.toolBarHolder.setStyle("-fx-border-color: black");

        this.todoBar.setStyle("-fx-border-width: 1px");
        this.todoBar.setStyle("-fx-border-color: black");
    }

    public void openProject() {
        new OpenWindow(workplace);
    }

    public void saveProject() {
        if (!workplace.isExistent()) return;
        new SaveWindow(workplace.getProjectWindow());
    }

    public void help() {
        new HelpWindow().show();
    }

    public void shortcut() {
        new ShortCutWindow().show();
    }

    public void about() {
        new AboutWindow().show();
    }

    public void centerWindow() {
    }

    public void centerModel() {
    }
}
