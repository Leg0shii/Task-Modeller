package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.gui.symbol.item.SelectionRectangle;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
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

    private final int xPosition;
    private final int yPosition;

    private String name;

    private ArrayList<ModelNode> drawnNodes;
    private ArrayList<ModelNode> selectedNodes;

    private ArrayList<Connection> connections;

    private final StatusType statusType;
    private final ModelType modelType;

    private SelectionRectangle selectionRectangle;

    public PaintWindow(Workplace workplace, int xPosition, int yPosition, StatusType statusType, ModelType modelType) {
        this.workplace = workplace;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
            // problem occurs when generalised node gets added without a pW being selected
            if (selectionRectangle == null) return;
            Point2D pStart = new Point2D(selectionRectangle.getStartX(), selectionRectangle.getStartY());
            Point2D pEnd = new Point2D(selectionRectangle.getNewXPos(), selectionRectangle.getNewYPos());
            if (Math.abs(pStart.getX() - pEnd.getX()) > 10) putSelectedNodes(pStart, pEnd);
            // otherwise other releases would trigger the not existing selection again
            selectionRectangle.invalidate();
            this.getChildren().remove(selectionRectangle);
        });
    }

    public void addNodeToCanvas(ModelNode node) {
        if (!(node instanceof GroupingNode)) {
            this.getChildren().add(node);
        }
        drawnNodes.add(node);
        selectedNodes.add(node);
        node.colorSelected();
        workplace.setSelectedSymbol(node);
    }

    public void addGenNodeToCanvas(ModelNode node) {
        workplace.getGeneralisedList().add(node);
        workplace.getChildren().add(node);
        workplace.getSelectedPaintWindow().addNodeToCanvas(node);
    }

    public void removeGenNodeFromCanvas(ModelNode node) {
        String nodeId = node.getId();
        ArrayList<Connection> nCToRemove = new ArrayList<>();
        for (Connection nC : workplace.getGroupingConnections()) {
            String nodeCId1 = nC.getNode1().getId();
            String nodeCId2 = nC.getNode2().getId();
            if (nodeCId1.equals(nodeId) || nodeCId2.equals(nodeId)) {
                nCToRemove.add(nC);
                this.workplace.getChildren().remove(nC.getLabel());
            }
        }
        workplace.getGroupingConnections().removeAll(nCToRemove);
        workplace.getChildren().removeAll(nCToRemove);
        workplace.getChildren().remove(node);
        drawnNodes.remove(node);
    }

    public void removeNode(ModelNode node) {
        String nodeId = node.getId();
        ArrayList<Connection> nCToRemove = new ArrayList<>();
        for (Connection nC : connections) {
            String nodeCId1 = nC.getNode1().getId();
            String nodeCId2 = nC.getNode2().getId();
            if (nodeCId1.equals(nodeId) || nodeCId2.equals(nodeId)) {
                nCToRemove.add(nC);
                this.getChildren().remove(nC.getLabel());
            }
        }
        connections.removeAll(nCToRemove);
        this.getChildren().removeAll(nCToRemove);
        this.getChildren().remove(node);
        drawnNodes.remove(node);

        for (Connection nC : workplace.getGroupingConnections()) {
            String nodeCId1 = nC.getNode1().getId();
            String nodeCId2 = nC.getNode2().getId();
            if (nodeCId1.equals(nodeId) || nodeCId2.equals(nodeId)) {
                nCToRemove.add(nC);
                this.workplace.getChildren().remove(nC.getLabel());
            }
        }
        workplace.getGroupingConnections().removeAll(nCToRemove);
        workplace.getChildren().removeAll(nCToRemove);
    }

    public void addConnection(Connection connection) {
        if(isConnected(connection)) return;
        if (connection instanceof GroupingConnection) {
            workplace.getGroupingConnections().add((GroupingConnection) connection);
            this.workplace.getChildren().add(connection);
            this.workplace.getChildren().add(connection.getLabel());
            return;
        }
        if (!isNodeSameWindow(connection)) return;
        connections.add(connection);
        int count = 0;
        for (ModelNode modelNode : this.drawnNodes) {
            if (modelNode instanceof GroupingNode) count++;
        }
        this.getChildren().add(count, connection);
        this.getChildren().add(count+1, connection.getLabel());
    }

    public void removeConnection(Connection connection) {
        this.getChildren().remove(connection.getLabel());
        this.getChildren().remove(connection);
        this.workplace.getChildren().remove(connection.getLabel());
        this.workplace.getChildren().remove(connection);
        connections.remove(connection);
    }

    private boolean isConnected(Connection connection) {
        String checkNode1 = connection.getNode1().getId();
        String checkNode2 = connection.getNode2().getId();
        for (Connection modelConnection : connections) {
            String nodeID1 = modelConnection.getNode1().getId();
            String nodeID2 = modelConnection.getNode2().getId();
            if (!(nodeID1.equals(checkNode1) || nodeID2.equals(checkNode1))) return false;
            if (!(nodeID1.equals(checkNode2) || nodeID2.equals(checkNode2))) return false;
        }
        return connections.size() != 0;
    }

    private boolean isNodeSameWindow(Connection connection) {
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

    public ArrayList<Connection> getSelectedConnections() {
        ArrayList<Connection> connectionNodeArrayList = new ArrayList<>();
        for (Connection normalConnection : this.connections) {
            if (this.selectedNodes.contains(normalConnection.getNode1()) && this.selectedNodes.contains(normalConnection.getNode2())) {
                connectionNodeArrayList.add(normalConnection);
            }
        }
        return connectionNodeArrayList;
    }

}
