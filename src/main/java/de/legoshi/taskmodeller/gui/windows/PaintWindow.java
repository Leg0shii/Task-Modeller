package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.ModelConnectionNode;
import de.legoshi.taskmodeller.gui.symbol.item.SelectionRectangle;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PaintWindow extends AnchorPane {

    private final Workplace workplace;

    private String name;

    private ArrayList<ModelNode> drawnNodes;
    private ArrayList<ModelNode> selectedNodes;

    private ArrayList<ModelConnectionNode> connections;

    private final StatusType statusType;
    private final ModelType modelType;

    private SelectionRectangle selectionRectangle;

    public PaintWindow(Workplace workplace, StatusType statusType, ModelType modelType) {
        this.workplace = workplace;
        this.modelType = modelType;
        this.statusType = statusType;

        this.initialize();
    }

    private void initialize() {
        this.minWidth(0);
        this.minHeight(0);
        this.setPrefWidth(500);
        this.setPrefHeight(500);
        this.maxWidth(500);
        this.maxHeight(500);
        this.getStyleClass().add("draw-pane-bg");

        drawnNodes = new ArrayList<>();
        selectedNodes = new ArrayList<>();

        connections = new ArrayList<>();

        this.setOnMousePressed(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) return;
            if (workplace.getSelectedPaintWindow() != this) {
                this.setActiveWindow();
            }
            selectionRectangle = new SelectionRectangle(mouseEvent);
            this.getChildren().add(selectionRectangle);
            removeSelectedNodes();
        });

        this.setOnMouseDragged(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) return;
            this.selectionRectangle.updateRect(mouseEvent.getX(), mouseEvent.getY());
        });
        this.setOnMouseReleased(mouseEvent -> {
            Point2D pStart = new Point2D(selectionRectangle.getStartX(), selectionRectangle.getStartY());
            Point2D pEnd = new Point2D(selectionRectangle.getNewXPos(), selectionRectangle.getNewYPos());
            if (Math.abs(pStart.getX() - pEnd.getX()) > 10) putSelectedNodes(pStart, pEnd);
            // otherwise other releases would trigger the not existing selection again
            selectionRectangle.invalidate();
            this.getChildren().remove(selectionRectangle);
        });
    }

    public void addNodeToCanvas(ModelNode node) {
        this.getChildren().add(node);
        drawnNodes.add(node);
        selectedNodes.add(node);
        node.colorSelected();
        workplace.setSelectedSymbol(node);
    }

    public void removeNode(ModelNode node) {
        String nodeId = node.getId();
        ArrayList<ModelConnectionNode> nCToRemove = new ArrayList<>();
        for (ModelConnectionNode nC : connections) {
            String nodeCId1 = nC.getNode1().getId();
            String nodeCId2 = nC.getNode2().getId();
            if (nodeCId1.equals(nodeId) || nodeCId2.equals(nodeId)) nCToRemove.add(nC);
        }
        connections.removeAll(nCToRemove);
        this.getChildren().removeAll(nCToRemove);
        this.getChildren().remove(node);
        drawnNodes.remove(node);
    }

    public void addConnection(ModelConnectionNode connection) {
        if (!isNodeSameWindow(connection)) return;

        if (!isConnected(connection)) {
            connections.add(connection);
            this.getChildren().add(0, connection);
        }
    }

    public void removeConnection(ModelConnectionNode connection) {
        this.getChildren().remove(connection);
        connections.remove(connection);
    }

    private boolean isConnected(ModelConnectionNode connection) {
        String checkNode1 = connection.getNode1().getId();
        String checkNode2 = connection.getNode2().getId();
        for (ModelConnectionNode modelConnectionNode : connections) {
            String nodeID1 = modelConnectionNode.getNode1().getId();
            String nodeID2 = modelConnectionNode.getNode2().getId();
            if (!(nodeID1.equals(checkNode1) || nodeID2.equals(checkNode1))) return false;
            if (!(nodeID1.equals(checkNode2) || nodeID2.equals(checkNode2))) return false;
        }
        return connections.size() != 0;
    }

    private boolean isNodeSameWindow(ModelConnectionNode connection) {
        return (drawnNodes.contains(connection.getNode1()) && drawnNodes.contains(connection.getNode2()));
    }

    private void putSelectedNodes(Point2D pStart, Point2D pEnd) {
        removeSelectedNodes();
        for (ModelNode modelNode : drawnNodes) {
            if (nodeInsideSelection(modelNode, pStart, pEnd)) {
                selectedNodes.add(modelNode);
                modelNode.colorSelected();
            }
        }
    }

    public void removeSelectedNodes() {
        for (ModelNode modelNode : selectedNodes) {
            modelNode.colorUnselected();
        }
        selectedNodes.clear();
    }

    private boolean nodeInsideSelection(ModelNode modelNode, Point2D pStart, Point2D pEnd) {
        double x1 = modelNode.getTranslateX();
        double y1 = modelNode.getTranslateY();
        double x2 = modelNode.getTranslateX() + 50;
        double y2 = modelNode.getTranslateY() + 50;
        double x3 = Math.min(pStart.getX(), pEnd.getX());
        double y3 = Math.min(pStart.getY(), pEnd.getY());
        double x4 = Math.max(pStart.getX(), pEnd.getX());
        double y4 = Math.max(pStart.getY(), pEnd.getY());
        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    public void setActiveWindow() {
        workplace.setSelectedPaintWindow(this);
        for (PaintWindow paintWindow : workplace.getAllWindows()) {
            paintWindow.getStyleClass().remove("active-pane");
            workplace.getItemBarManager().reloadItemBarWithModel(this, modelType);
            paintWindow.removeSelectedNodes();
        }
        this.getStyleClass().add("active-pane");
        removeSelectedNodes();
    }

}
