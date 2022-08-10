package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CCircle extends ModelNode {

    public CCircle(Shape shape) {
        super(shape);

        this.getLabel().setText("Task");
    }

    public static CCircle generateShape() {
        Circle circle = new Circle(25);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
        return new CCircle(circle);
    }

}
