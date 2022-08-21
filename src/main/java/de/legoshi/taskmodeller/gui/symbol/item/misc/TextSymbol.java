package de.legoshi.taskmodeller.gui.symbol.item.misc;

import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TextSymbol extends WorkplaceNode {

    public TextSymbol(Shape polyShape) {
        super(polyShape);
        this.getLabel().setText("Comment");
    }

    public static TextSymbol generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.ORANGE.brighter().brighter());
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        return new TextSymbol(rectangle);
    }

}
