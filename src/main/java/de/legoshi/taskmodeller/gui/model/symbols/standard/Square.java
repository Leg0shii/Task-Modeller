package de.legoshi.taskmodeller.gui.model.symbols.standard;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Square extends Drawable {

    public Square(Polygon shape) {
        super(shape);
    }

    public static Polygon generateSquare() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(0, 50);
        Point2D p3 = new Point2D(50, 50);
        Point2D p4 = new Point2D(50, 0);
        return PolygonHelper.createPolygon(p1, p2, p3, p4);
    }

}
