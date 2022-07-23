package de.legoshi.taskmodeller.gui.symbol.item.misc;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TextSymbol extends WorkplaceNode {

    public TextSymbol(Shape polyShape) {
        super(polyShape);
    }

    public static Drawable generateShape(Workplace workplace) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.ORANGE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        TextSymbol textSymbol = new TextSymbol(rectangle);
        textSymbol.registerEvents(workplace);
        return textSymbol;
    }

}
