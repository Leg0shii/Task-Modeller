package de.legoshi.taskmodeller.util;

import javafx.geometry.Point2D;

public class CalculationLine {

    public double m, b, x1;

    public CalculationLine(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.m = ((y2 - y1) / (x2 - x1));
        this.b = (y2 - m * x2);
    }

    public Point2D intersect(CalculationLine line) {
        double x, y;
        if (line.m == Double.POSITIVE_INFINITY || line.m == Double.NEGATIVE_INFINITY) x = line.x1;
        else x = Math.abs(this.b - line.b) / Math.abs(this.m - line.m);
        y = this.m * x + this.b;
        return new Point2D(x, y);
    }

}
