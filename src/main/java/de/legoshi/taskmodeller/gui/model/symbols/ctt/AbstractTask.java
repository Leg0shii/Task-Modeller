package de.legoshi.taskmodeller.gui.model.symbols.ctt;

import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.util.UUID;

public class AbstractTask extends Drawable {
    public AbstractTask(Polygon shape) {
        super(UUID.randomUUID(), shape);
    }

    public static Polygon generateAbstractTask() {
        Point2D p1 = new Point2D(0, 25);
        Point2D p2 = new Point2D(25, 0);
        Point2D p3 = new Point2D(50, 25);
        Point2D p4 = new Point2D(25, 50);
        return PolygonHelper.createPolygon(p1, p2, p3, p4);
    }
}
