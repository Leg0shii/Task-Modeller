package de.legoshi.taskmodeller.gui.model.symbols.standard;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class CTriangle extends Drawable {

    public CTriangle(Shape shape) {
        super(shape);
    }

    public static Drawable generateShape() {
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(25,50);
        Point2D p3 = new Point2D(50,0);
        return new CTriangle(PolygonHelper.createPolygon(p1, p2, p3));
    }


}
