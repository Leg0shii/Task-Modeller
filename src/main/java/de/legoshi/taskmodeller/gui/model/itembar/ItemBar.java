package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class ItemBar {

    public ArrayList<Drawable> itemBar = new ArrayList<>();
    public Color color = Color.YELLOW;
    public Color border = Color.BLACK;
    public int strokeWidth = 3;

    public Rectangle colorPoly(Rectangle shape) {
        shape.setFill(color);
        shape.setStroke(border);
        shape.setStrokeWidth(strokeWidth);
        return shape;
    }

    abstract void prepareItemBar();

}
