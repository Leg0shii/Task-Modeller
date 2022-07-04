package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DrawArea extends AnchorPane {

    private final ArrayList<DrawnSymbol> drawnNodes;
    private final ArrayList<NodeConnection> connections;

    public DrawArea() {
        this.minWidth(0);
        this.minHeight(0);
        this.setPrefWidth(500);
        this.setPrefHeight(500);
        this.maxWidth(Integer.MAX_VALUE);
        this.maxHeight(Integer.MAX_VALUE);
        this.getStyleClass().add("draw-pane-bg");

        this.drawnNodes = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public void addNode(DrawnSymbol node) {
        this.getChildren().add(node);
        this.drawnNodes.add(node);
    }

    public void removeNode(DrawnSymbol node) {
        String nodeId = node.getId();
        ArrayList<NodeConnection> nCToRemove = new ArrayList<>();
        for (NodeConnection nC : this.connections) {
            String nodeCId1 = nC.getNode1().getId();
            String nodeCId2 = nC.getNode2().getId();
            if (nodeCId1.equals(nodeId) || nodeCId2.equals(nodeId)) nCToRemove.add(nC);
        }
        this.connections.removeAll(nCToRemove);
        this.getChildren().removeAll(nCToRemove);
        this.getChildren().remove(node);
        this.drawnNodes.remove(node);
    }

    public void addConnection(NodeConnection connection) {
        if (!isNodeSameWindow(connection)) return;

        if (!isConnected(connection)) {
            this.connections.add(connection);
            this.draw(connection);
        }
    }

    public void removeConnection(NodeConnection connection) {
        this.getChildren().remove(connection);
        this.connections.remove(connection);
    }

    private boolean isConnected(NodeConnection connection) {
        String checkNode1 = connection.getNode1().getId();
        String checkNode2 = connection.getNode2().getId();
        for (NodeConnection nodeConnection : this.connections) {
            String nodeID1 = nodeConnection.getNode1().getId();
            String nodeID2 = nodeConnection.getNode2().getId();
            if (!(nodeID1.equals(checkNode1) || nodeID2.equals(checkNode1))) return false;
            if (!(nodeID1.equals(checkNode2) || nodeID2.equals(checkNode2))) return false;
        }
        return this.connections.size() != 0;
    }

    private boolean isNodeSameWindow(NodeConnection connection) {
        return connection.getNode1().getParent().equals(connection.getNode2().getParent());
    }

    public void draw(NodeConnection nodeConnection) {
        this.getChildren().add(nodeConnection);
    }

}
