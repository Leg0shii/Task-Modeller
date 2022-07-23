package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Square extends ModelNode {

    public Square(Shape shape) {
        super(shape);
    }

    public static Square generateShape(Workplace workplace) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.YELLOW);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        Square square = new Square(rectangle);
        square.registerEvents(workplace);
        return square;
    }

}
