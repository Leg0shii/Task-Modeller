package de.legoshi.taskmodeller.gui.symbol;

import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.CommentEditWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

import java.util.UUID;

public class WorkplaceNode extends Drawable {

    public WorkplaceNode(Shape shape) {
        super(shape);
        Label label = new Label(UUID.randomUUID().toString().substring(0, 3));
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));

        Rectangle newShape = new Rectangle();
        newShape.setHeight(((Rectangle) shape).getHeight());
        newShape.setWidth(((Rectangle) shape).getWidth());
        newShape.widthProperty().bind(label.widthProperty());
        newShape.heightProperty().bind(label.heightProperty());

        newShape.setFill(shape.getFill());
        newShape.setStroke(shape.getStroke());
        newShape.setStrokeWidth(shape.getStrokeWidth());

        this.getChildren().add(newShape);
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void onMouseDrag(Workplace workplace, MouseEvent event) {
        double scaleX = workplace.getScaleX();
        double polyTranslateX = this.getTranslateX();
        double scaleY = workplace.getScaleY();
        double polyTranslateY = this.getTranslateY();

        this.setTranslateX(polyTranslateX + (event.getSceneX() - this.lastX) / scaleX);
        this.setTranslateY(polyTranslateY + (event.getSceneY() - this.lastY) / scaleY);
        this.lastX = event.getSceneX();
        this.lastY = event.getSceneY();
    }

    @Override
    public void onMouseClick(Workplace workplace, MouseEvent event) {
        if (event.isSecondaryButtonDown()) {
            new CommentEditWindow(workplace, this).show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

}
