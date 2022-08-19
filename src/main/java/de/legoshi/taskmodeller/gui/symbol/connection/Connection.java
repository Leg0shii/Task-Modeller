package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.LineEditWindow;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import lombok.Getter;

@Getter
public class Connection extends Line {

    // HIGHLIGHT when connection is ready
    private final Workplace workplace;
    private final Label label;

    public ModelNode node1;
    public ModelNode node2;

    public DoubleBinding xStartProperty;
    public DoubleBinding yStartProperty;
    public DoubleBinding xEndProperty;
    public DoubleBinding yEndProperty;

    public Connection(Workplace workplace, ModelNode node1, ModelNode node2) {
        this.setStrokeWidth(3);

        this.workplace = workplace;
        this.label = new Label("Name");

        this.node1 = node1;
        this.node2 = node2;

        if (!(node1 instanceof GroupingNode)) {
            this.node1 = node2;
            this.node2 = node1;
        }

        recalculateBindings();
        this.setOnMousePressed(this::onMousePressed);
        this.label.setOnMousePressed(this::onMousePressed);
    }

    public Connection(Workplace workplace, ModelNode node1, MouseEvent mouseEvent) {
        this.workplace = workplace;
        this.label = new Label("");
        this.node1 = node1;

        recalculateMouseBindings(mouseEvent);
    }

    private void recalculateMouseBindings(MouseEvent mouseEvent) {
        this.setStartX(mouseEvent.getX());
        this.setStartY(mouseEvent.getY());
        this.setEndX(node1.getTranslateX() + node1.getWidth()/2);
        this.setEndY(node1.getTranslateY() + node1.getHeight()/2);
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

        DoubleBinding xLabelProperty;
        DoubleBinding yLabelProperty;
        if ((node2.translateXProperty().get() - node1.translateXProperty().get()) >= 0) {
            xLabelProperty = (node2.translateXProperty().subtract(node1.translateXProperty())).divide(2).add(node1.translateXProperty()).add(node1.getWidth()/2);
            yLabelProperty = (node2.translateYProperty().subtract(node1.translateYProperty())).divide(2).add(node1.translateYProperty()).add(node1.getHeight()/2);
        } else {
            xLabelProperty = (node1.translateXProperty().subtract(node2.translateXProperty())).divide(2).add(node2.translateXProperty()).add(node2.getWidth()/2);
            yLabelProperty = (node1.translateYProperty().subtract(node2.translateYProperty())).divide(2).add(node2.translateYProperty()).add(node2.getHeight()/2);
        }
        label.translateXProperty().unbind();
        label.translateYProperty().unbind();
        label.translateXProperty().bind(xLabelProperty.subtract(label.widthProperty().divide(2)));
        label.translateYProperty().bind(yLabelProperty.subtract(20));
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
