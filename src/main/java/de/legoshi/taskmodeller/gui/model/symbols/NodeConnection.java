package de.legoshi.taskmodeller.gui.model.symbols;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class NodeConnection {

    // CHECK if nodes are already connected
    // CHECK that both nodes are on the same window
    // DELETE if a node gets deleted
    // HIGHLIGHT when connection is ready

    private DrawnSymbol node1;
    private DrawnSymbol node2;

    private Line line;

    public NodeConnection(DrawnSymbol node1, DrawnSymbol node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.line = new Line(node1.getTranslateX(), node1.getTranslateY(), node2.getTranslateX(), node2.getTranslateY());
    }

    public void draw(AnchorPane anchorPane) {
        anchorPane.getChildren().add(this.line);
    }

    private void calculateClosestPoints() {

    }
}
