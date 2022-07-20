package de.legoshi.taskmodeller.gui.model.symbols.ctt;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SimpleTask extends Drawable {
    public SimpleTask(Shape shape) {
        super(shape);
    }

    public static Drawable generateShape() {
        return new SimpleTask(new Rectangle(50, 50));
    }

}
