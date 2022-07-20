package de.legoshi.taskmodeller.gui.model.symbols.standard;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.shape.Rectangle;

public class Square extends Drawable {

    public Square(Rectangle shape) {
        super(shape);
    }

    public static Rectangle generateSquare() {
        return new Rectangle(50, 50);
    }

}
