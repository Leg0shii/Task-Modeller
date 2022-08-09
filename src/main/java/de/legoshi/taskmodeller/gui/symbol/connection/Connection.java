package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.LineEditWindow;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import lombok.Getter;

@Getter
public class Connection extends Line {

    // HIGHLIGHT when connection is ready
    private final Workplace workplace;

    public ModelNode node1;
    public ModelNode node2;

    public DoubleBinding xStartProperty;
    public DoubleBinding yStartProperty;
    public DoubleBinding xEndProperty;
    public DoubleBinding yEndProperty;

    public Connection(Workplace workplace, ModelNode node1, ModelNode node2) {
        this.setStrokeWidth(3);

        this.workplace = workplace;

        this.node1 = node1;
        this.node2 = node2;

        if (!(node1 instanceof GroupingNode)) {
            this.node1 = node2;
            this.node2 = node1;
        }

        recalculateBindings();
        this.setOnMousePressed(this::onMousePressed);
    }

    public void onMousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.isSecondaryButtonDown()) {
            new LineEditWindow(workplace, this).show();
        }
    }

    public void recalculateBindings() {
        this.xStartProperty = node1.translateXProperty().add(node1.getWidth()/2);
        this.yStartProperty = node1.translateYProperty().add(node1.getHeight()/2);
        this.xEndProperty = node2.translateXProperty().add(node2.getWidth()/2);
        this.yEndProperty = node2.translateYProperty().add(node2.getHeight()/2);
        setBindings();
    }

    public void setBindings() {
        this.startXProperty().unbind();
        this.startYProperty().unbind();
        this.endXProperty().unbind();
        this.endYProperty().unbind();
        this.startXProperty().bind(this.xStartProperty);
        this.startYProperty().bind(this.yStartProperty);
        this.endXProperty().bind(this.xEndProperty);
        this.endYProperty().bind(this.yEndProperty);
    }

}
