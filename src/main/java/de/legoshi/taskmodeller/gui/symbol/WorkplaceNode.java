package de.legoshi.taskmodeller.gui.symbol;

import de.legoshi.taskmodeller.gui.symbol.item.misc.GeneralisedNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.CommentEditWindow;
import de.legoshi.taskmodeller.gui.windows.editwindow.GeneralisedEditWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceNode extends Drawable {

    private Label label;

    public WorkplaceNode(Shape shape) {
        super(shape);
        label = new Label("Comment");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));

        Rectangle newShape = new Rectangle();
        this.setPolyShape(newShape);
        newShape.setHeight(((Rectangle) shape).getHeight());
        newShape.setWidth(((Rectangle) shape).getWidth());

        if (this instanceof TextSymbol) {
            newShape.widthProperty().bind(label.widthProperty());
            newShape.heightProperty().bind(label.heightProperty());
        }

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
            if (this instanceof TextSymbol) {
                new CommentEditWindow(workplace, this).show();
            } else if (this instanceof GeneralisedNode) {
                new GeneralisedEditWindow(workplace, this).show();
            }
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

}
