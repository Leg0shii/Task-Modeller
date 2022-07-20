package de.legoshi.taskmodeller.gui.model.symbols;

import de.legoshi.taskmodeller.gui.model.windows.editwindows.LineEditWindow;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Line;
import lombok.Getter;

@Getter
public class NodeConnection extends Line {

    // FIX on scaling
    // HIGHLIGHT when connection is ready

    private final DrawnSymbol node1;
    private final DrawnSymbol node2;

    private DoubleBinding xStartProperty;
    private DoubleBinding yStartProperty;
    private DoubleBinding xEndProperty;
    private DoubleBinding yEndProperty;

    public NodeConnection(DrawnSymbol node1, DrawnSymbol node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.setStrokeWidth(3);

        recalculateBindings();

        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                LineEditWindow lineEditWindow = new LineEditWindow(this);
                lineEditWindow.show();
            }
        });
    }

    public void recalculateBindings() {
        this.xStartProperty = node1.translateXProperty().add(node1.getWidth()/2);
        this.yStartProperty = node1.translateYProperty().add(node1.getHeight()/2);
        this.xEndProperty = node2.translateXProperty().add(node2.getWidth()/2);
        this.yEndProperty = node2.translateYProperty().add(node2.getHeight()/2);
        setBindings();
    }

    public void setBindings() {
        this.startXProperty().bind(this.xStartProperty);
        this.startYProperty().bind(this.yStartProperty);
        this.endXProperty().bind(this.xEndProperty);
        this.endYProperty().bind(this.yEndProperty);
    }

}
