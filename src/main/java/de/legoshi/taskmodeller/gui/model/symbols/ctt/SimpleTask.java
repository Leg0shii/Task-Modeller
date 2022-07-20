package de.legoshi.taskmodeller.gui.model.symbols.ctt;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class SimpleTask extends Drawable {
    public SimpleTask(Rectangle shape) {
        super(shape);
    }

    public static Rectangle createSimpleTask() {
        return null;
    }
}
