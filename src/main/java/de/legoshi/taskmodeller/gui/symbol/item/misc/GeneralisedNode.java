package de.legoshi.taskmodeller.gui.symbol.item.misc;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GeneralisedNode extends WorkplaceNode {

    public GeneralisedNode(Rectangle shape) {
        super(shape);
        this.getLabel().setText("");
    }

    public static Drawable generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        return new GeneralisedNode(rectangle);
    }
}
