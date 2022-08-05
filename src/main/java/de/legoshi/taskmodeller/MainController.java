package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
import de.legoshi.taskmodeller.gui.windows.newwindow.NewProjectCountWindow;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.NodesHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public VBox mainFrame;

    public MenuBar menuBar;

    public ScrollPane contentPane;
    private Workplace workplace;

    public HBox toolBar;

    public RadioButton standardRBTN;
    public RadioButton connectionRBTN;
    public RadioButton miscRBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.workplace = new Workplace(this.toolBar);
        this.contentPane.setContent(new Group(workplace));

        contentPane.addEventFilter(ScrollEvent.ANY, (scrollEvent -> {
            if (!scrollEvent.isShiftDown()) return;

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
        new NewProjectCountWindow(workplace.getProjectWindow()).show();
    }

    @FXML
    private void onRadioButtonChange() {
        PaintWindow selectedWindow = workplace.getSelectedPaintWindow();
        ModelType modelType = ModelType.MISC;
        if (standardRBTN.isSelected()) {
            if (selectedWindow == null) return;
            modelType = selectedWindow.getModelType();
        } else if (connectionRBTN.isSelected()) modelType = ModelType.CONN;
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
            Connection mNC = NormalConnection.generateShape(workplace, firstNode, secondNode);
            paintWindow.addConnection(mNC);
        }
    }

}
