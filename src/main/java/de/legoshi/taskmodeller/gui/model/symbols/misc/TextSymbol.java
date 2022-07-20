package de.legoshi.taskmodeller.gui.model.symbols.misc;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class TextSymbol extends Drawable {

    public TextSymbol(Rectangle polyShape) {
        super(polyShape);
    }

    public static Rectangle generateTextSymbol() {
        return new Rectangle(50, 50);
    }
}
