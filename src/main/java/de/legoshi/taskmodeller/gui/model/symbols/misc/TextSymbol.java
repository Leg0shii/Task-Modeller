package de.legoshi.taskmodeller.gui.model.symbols.misc;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TextSymbol extends Drawable {

    public TextSymbol(Shape polyShape) {
        super(polyShape);
    }

    public static Drawable generateShape() {
        return new TextSymbol(new Rectangle(50, 50));
    }

}
