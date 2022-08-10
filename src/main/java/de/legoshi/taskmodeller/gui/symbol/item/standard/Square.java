package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Square extends ModelNode {

    public Square(Shape shape) {
        super(shape);

        this.getLabel().setText("Task");
    }

    public static Square generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        return new Square(rectangle);
    }

}
