package de.legoshi.taskmodeller.gui.model.symbols;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DrawnSymbol extends StackPane {

    private boolean projectObject;
    private boolean attemptsConnect = false;
    private String description;

    public DrawnSymbol(Rectangle polyShape, boolean projectObject) {
        this.setId(UUID.randomUUID().toString());
        this.projectObject = projectObject;

        Rectangle newShape = new Rectangle();

        newShape.setHeight(polyShape.getHeight());
        newShape.setWidth(polyShape.getHeight());
        newShape.setFill(polyShape.getFill());
        newShape.setStroke(polyShape.getStroke());
        newShape.setStrokeWidth(polyShape.getStrokeWidth());

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Label label = new Label(UUID.randomUUID().toString().substring(0,3));
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));
        if (projectObject) {
            newShape.widthProperty().bind(label.widthProperty());
            newShape.heightProperty().bind(label.heightProperty());
        } else {
            label.setMaxWidth(50);
            label.setMaxHeight(50);
        }

        this.getChildren().add(newShape);
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }
}
