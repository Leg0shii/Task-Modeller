package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.LineEditWindow;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Line;
import lombok.Getter;

@Getter
public class Connection extends Line {

    // HIGHLIGHT when connection is ready
    private final Workplace workplace;

    final ModelNode node1;
    final ModelNode node2;

    DoubleBinding xStartProperty;
    DoubleBinding yStartProperty;
    DoubleBinding xEndProperty;
    DoubleBinding yEndProperty;

    public Connection(Workplace workplace, ModelNode node1, ModelNode node2) {
        this.setStrokeWidth(3);

        this.workplace = workplace;

        this.node1 = node1;
        this.node2 = node2;

        recalculateBindings();

        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                new LineEditWindow(workplace, this).show();
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
