package de.legoshi.taskmodeller.gui.symbol.item.ctt;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AbstractTask extends ModelNode {

    public AbstractTask(Shape shape) {
        super(shape);

        this.getLabel().setText("A-Task");
    }

    public static AbstractTask generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        return new AbstractTask(rectangle);
    }

}
