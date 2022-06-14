package de.legoshi.taskmodeller.gui.model.symbols.standard;

import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.util.UUID;

public class SmallLine extends Drawable {
    public SmallLine(Polygon shape) {
        super(UUID.randomUUID(), shape);
    }

    public static Polygon generateLine() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(50, 50);
        return PolygonHelper.createPolygon(p1, p2);
    }

}
