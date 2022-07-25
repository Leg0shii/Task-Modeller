package de.legoshi.taskmodeller.gui.symbol.item.ctt;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SimpleTask extends ModelNode {

    public SimpleTask(Shape shape) {
        super(shape);
    }

    public static SimpleTask generateShape(Workplace workplace) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        SimpleTask simpleTask = new SimpleTask(rectangle);
        simpleTask.registerEvents(workplace);
        return simpleTask;
    }

}
