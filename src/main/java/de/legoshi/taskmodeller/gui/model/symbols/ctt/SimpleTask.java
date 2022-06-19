package de.legoshi.taskmodeller.gui.model.symbols.ctt;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.util.UUID;

public class SimpleTask extends Drawable {
    public SimpleTask(Polygon shape) {
        super(UUID.randomUUID(), shape);
    }

    public static Polygon createSimpleTask() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(0, 50);
        Point2D p3 = new Point2D(50, 50);
        Point2D p4 = new Point2D(50, 0);
        return PolygonHelper.createPolygon(p1, p2, p3, p4);
    }
}
