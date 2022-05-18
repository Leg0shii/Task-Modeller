package de.legoshi.taskmodeller.util;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class PolygonHelper {

    public static Polygon createPolygon(Point2D ... points) {
        Polygon polygon = new Polygon();
        for (Point2D p : points) {
            polygon.getPoints().addAll(p.getX(), p.getY());
        }
        return polygon;
    }

}
