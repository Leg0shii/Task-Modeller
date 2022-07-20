package de.legoshi.taskmodeller.gui.model.symbols.ctt;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class AbstractTask extends Drawable {

    public AbstractTask(Shape shape) {
        super(shape);
    }

    public static Drawable generateShape() {
        return new AbstractTask(new Circle(25));
    }

}
