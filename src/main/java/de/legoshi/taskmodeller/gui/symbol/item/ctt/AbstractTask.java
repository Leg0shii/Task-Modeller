package de.legoshi.taskmodeller.gui.symbol.item.ctt;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class AbstractTask extends ModelNode {

    public AbstractTask(Shape shape) {
        super(shape);
    }

    public static AbstractTask generateShape(Workplace workplace) {
        Circle circle = new Circle(25);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
        AbstractTask abstractTask = new AbstractTask(circle);
        abstractTask.registerEvents(workplace);
        return abstractTask;
    }

}
