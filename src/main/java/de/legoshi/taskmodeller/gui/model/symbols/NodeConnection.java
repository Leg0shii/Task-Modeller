package de.legoshi.taskmodeller.gui.model.symbols;

import de.legoshi.taskmodeller.gui.model.windows.LineEditWindow;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private boolean position = false;

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
        double x1 = this.node1.getTranslateX();
        double y1 = this.node1.getTranslateY();
        double x2 = this.node2.getTranslateX();
        double y2 = this.node2.getTranslateY();

        double x1Side = x1 + this.node1.getWidth();
        double y1Side = y1 + this.node1.getHeight();
        double x2Side = x2 + this.node2.getWidth();
        double y2Side = y2 + this.node2.getHeight();

        if (Math.abs(x1Side - x2) <= Math.abs(x1Side - x2Side)) this.xStartProperty = node1.translateXProperty().add(node1.getWidth()); // x1Side
        else this.xStartProperty = node1.translateXProperty().add(0); // x1
        if (Math.abs(y1Side - y2) <= Math.abs(y1Side - y2Side)) this.yStartProperty = node1.translateYProperty().add(node1.getHeight()); // x1Side
        else this.yStartProperty = node1.translateYProperty().add(0); // y1
        if (Math.abs(x1 - x2) <= Math.abs(x1 - x2Side)) this.xEndProperty = node2.translateXProperty().add(0); // x2
        else this.xEndProperty = node2.translateXProperty().add(node2.getWidth()); // x2Side
        if (Math.abs(y1 - y2) <= Math.abs(y1 - y2Side)) this.yEndProperty = node2.translateYProperty().add(0); // x2
        else this.yEndProperty = node2.translateYProperty().add(node2.getHeight());// y2Side

        setBindings();
    }

    public void setBindings() {
        unBindAll();
        this.startXProperty().bind(this.xStartProperty);
        this.startYProperty().bind(this.yStartProperty);
        this.endXProperty().bind(this.xEndProperty);
        this.endYProperty().bind(this.yEndProperty);
    }

    private void unBindAll() {
        this.startXProperty().unbind();
        this.startYProperty().unbind();
        this.endXProperty().unbind();
        this.endYProperty().unbind();
    }

}
