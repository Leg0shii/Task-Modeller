package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class ItemBar {

    public ArrayList<Drawable> itemBar = new ArrayList<>();
    public Color color = Color.YELLOW;
    public Color border = Color.BLACK;
    public int strokeWidth = 3;

    public void colorize() {
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setFill(this.color);
            drawable.getPolyShape().setStroke(this.border);
            drawable.getPolyShape().setStrokeWidth(this.strokeWidth);
        }
    }

    abstract void prepareItemBar();

}
