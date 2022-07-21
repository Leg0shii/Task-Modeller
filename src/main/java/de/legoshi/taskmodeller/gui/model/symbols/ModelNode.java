package de.legoshi.taskmodeller.gui.model.symbols;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ModelNode extends StackPane {

    private boolean projectObject;
    private boolean attemptsConnect = false;
    private String description;

    public ModelNode(Shape polyShape, boolean projectObject) {
        this.setId(UUID.randomUUID().toString());
        this.projectObject = projectObject;

        Shape newShape;

        Label label = new Label(UUID.randomUUID().toString().substring(0,3));
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));

        if (polyShape instanceof Rectangle) {
            newShape = new Rectangle();
            ((Rectangle) newShape).setHeight(((Rectangle) polyShape).getHeight());
            ((Rectangle) newShape).setWidth(((Rectangle) polyShape).getWidth());
            if (projectObject) {
                ((Rectangle) newShape).widthProperty().bind(label.widthProperty());
                ((Rectangle) newShape).heightProperty().bind(label.heightProperty());
            } else {
                label.setMaxWidth(50);
                label.setMaxHeight(50);
            }
        } else if (polyShape instanceof Polygon) {
            newShape = new Polygon();
            ((Polygon) newShape).getPoints().addAll(((Polygon) polyShape).getPoints());
            label.setMaxWidth(50);
            label.setMaxHeight(50);
        } else {
            newShape = new Circle();
            ((Circle) newShape).setRadius(((Circle) polyShape).getRadius());
            label.setMaxWidth(50);
            label.setMaxHeight(50);
        }

        newShape.setFill(polyShape.getFill());
        newShape.setStroke(polyShape.getStroke());
        newShape.setStrokeWidth(polyShape.getStrokeWidth());

        this.getChildren().add(newShape);
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }

}
