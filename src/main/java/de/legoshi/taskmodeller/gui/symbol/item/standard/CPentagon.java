package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CPentagon extends ModelNode {
    public CPentagon(Shape shape) {
        super(shape);

        this.getLabel().setText("Task");
    }

    public static CPentagon generateShape() {
        Point2D p1 = new Point2D(26.23576,50);
        Point2D p2 = new Point2D(52.47152,30.906);
        Point2D p3 = new Point2D(52.47152,0);
        Point2D p4 = new Point2D(2.60984,0);
        Point2D p5 = new Point2D(0,30.906); // 28.09
        Polygon polygon = PolygonHelper.createPolygon(p1, p2, p3, p4, p5);
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(3);
        return new CPentagon(polygon);
    }

}
