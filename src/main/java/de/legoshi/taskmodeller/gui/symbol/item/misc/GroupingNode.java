package de.legoshi.taskmodeller.gui.symbol.item.misc;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GroupingNode extends ModelNode {

    public GroupingNode(Rectangle shape) {
        super(shape);
        this.getLabel().setText("");
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 3, 3, 3))));
    }

    public static GroupingNode generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setStrokeWidth(3);
        return new GroupingNode(rectangle);
    }
}
