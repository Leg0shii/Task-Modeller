package de.legoshi.taskmodeller.gui.model.symbols;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DrawnSymbol extends StackPane {

    private boolean attemptsConnect = false;

    public DrawnSymbol(Polygon polyShape) {
        this.setId(UUID.randomUUID().toString());

        Polygon newPoly = new Polygon();

        newPoly.getPoints().addAll(polyShape.getPoints());
        newPoly.setFill(polyShape.getFill());
        newPoly.setStroke(polyShape.getStroke());
        newPoly.setStrokeWidth(polyShape.getStrokeWidth());

        Label label = new Label(UUID.randomUUID().toString().substring(0,3));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(50);
        label.setMaxHeight(50);
        label.setFont(new Font("Arial", 12));

        this.getChildren().add(newPoly);
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }
}
