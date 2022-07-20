package de.legoshi.taskmodeller.gui.model.symbols.standard;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Square extends Drawable {

    public Square(Shape shape) {
        super(shape);
    }

    public static Square generateShape() {
        return new Square(new Rectangle(50, 50));
    }

}
