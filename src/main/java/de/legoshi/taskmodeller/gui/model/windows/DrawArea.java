package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DrawArea extends AnchorPane {

    private ArrayList<DrawnSymbol> drawnNodes;
    private ArrayList<NodeConnection> connections;

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

    public void addConnection(NodeConnection connection) {
        this.connections.add(connection);
    }

}
