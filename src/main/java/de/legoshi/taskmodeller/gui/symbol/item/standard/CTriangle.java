package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CTriangle extends ModelNode {

    public CTriangle(Shape shape) {
        super(shape);
    }

    public static Drawable generateShape(Workplace workplace) {
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(25,50);
        Point2D p3 = new Point2D(50,0);
        Polygon polygon = PolygonHelper.createPolygon(p1, p2, p3);
        polygon.setFill(Color.YELLOW);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(3);
        CTriangle triangle = new CTriangle(polygon);
        triangle.registerEvents(workplace);
        return triangle;
    }

}
