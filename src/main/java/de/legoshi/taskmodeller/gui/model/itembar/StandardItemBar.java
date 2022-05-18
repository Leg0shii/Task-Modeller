package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.SmallLine;
import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.Square;
import de.legoshi.taskmodeller.gui.model.symbols.Triangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class StandardItemBar {

    private ArrayList<Drawable> itemBar;
    private final static Color color = Color.YELLOW;
    private final static Color border = Color.BLACK;
    private final static int strokeWidth = 3;

    public StandardItemBar() {
        this.itemBar = new ArrayList<>();
    }

    public static void colorPoly(Polygon shape) {
        shape.setFill(color);
        shape.setStroke(border);
        shape.setStrokeWidth(strokeWidth);
    }

    public void prepareItemBar() {
        itemBar.add(new Square(Square.generateSquare()));
        itemBar.add(new Triangle(Triangle.generateTriangle()));
        itemBar.add(new SmallLine(SmallLine.generateLine()));
    }
}
