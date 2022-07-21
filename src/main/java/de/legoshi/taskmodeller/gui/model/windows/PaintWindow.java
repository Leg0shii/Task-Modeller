package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.gui.model.symbols.ModelNode;
import de.legoshi.taskmodeller.gui.model.symbols.ModelConnectionNode;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;
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
    private ArrayList<ModelConnectionNode> connections;

    private final StatusType statusType;
    private final ModelType modelType;

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
        connections = new ArrayList<>();

        this.setOnMouseClicked(mouseEvent -> {
            workplace.setSelectedPaintWindow(this);
            for(PaintWindow paintWindow : workplace.getAllWindows()) {
                paintWindow.getStyleClass().remove("active-pane");
                workplace.getItemBarManager().reloadItemBarWithModel(this, modelType);
            }
            this.getStyleClass().add("active-pane");
        });
    }

    public void addNodeToCanvas(ModelNode node) {
        this.getChildren().add(node);
        drawnNodes.add(node);
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
            this.draw(connection);
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
        return connection.getNode1().getParent().equals(connection.getNode2().getParent());
    }

    public void draw(ModelConnectionNode modelConnectionNode) {
        this.getChildren().add(0, modelConnectionNode);
    }
}
