package de.legoshi.taskmodeller.gui.symbol;

import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import lombok.Getter;

public abstract class Drawable extends StackPane {

    @Getter
    private final Shape polyShape;

    public double lastX = 0;
    public double lastY = 0;
    public static final int marginLeftX = 5; //10;
    public static final int marginTopY = 5; //10;
    public static final int marginRightX = 55; //110;
    public static final int marginBottomY = 55; //240;

    public Drawable(Shape polyShape) {
        this.polyShape = polyShape;
    }

    public abstract void onMouseClick(Workplace workplace, MouseEvent event);
    public abstract void onMouseDrag(Workplace workplace, MouseEvent event);

    public void registerEvents(Workplace workplace) {
        for (Node n : this.getChildren()) {
            n.setOnMousePressed(event -> onMouseClick(workplace, event));
            n.setOnMouseDragged(event -> onMouseDrag(workplace, event));
        }
    }

}
