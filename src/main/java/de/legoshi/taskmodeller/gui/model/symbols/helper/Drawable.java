package de.legoshi.taskmodeller.gui.model.symbols.helper;

import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import lombok.Getter;

import java.util.UUID;

public abstract class Drawable implements IShape<Polygon> {

    private final UUID id;
    @Getter private final Polygon shape;

    private double lastX = 0;
    private double lastY = 0;

    public Drawable(UUID randomUUID, Polygon shape) {
        this.id = randomUUID;
        this.shape = shape;
        StandardItemBar.colorPoly(shape);
    }

    private void onMouseClick(MouseEvent event) {
        this.lastX = event.getSceneX();
        this.lastY = event.getSceneY();
        event.consume();
    }

    private void onMouseDrag(MouseEvent event, Polygon poly) {
        poly.setTranslateX(poly.getTranslateX() + event.getSceneX() - lastX);
        poly.setTranslateY(poly.getTranslateY() + event.getSceneY() - lastY);
        this.lastX = event.getSceneX();
        this.lastY = event.getSceneY();
    }

    public Polygon getDuplicate() {
        Polygon newPoly = new Polygon();
        newPoly.getPoints().addAll(shape.getPoints());
        newPoly.setFill(shape.getFill());
        newPoly.setStroke(shape.getStroke());
        newPoly.setStrokeWidth(shape.getStrokeWidth());
        newPoly.setOnMousePressed(this::onMouseClick);
        newPoly.setOnMouseDragged(event -> onMouseDrag(event, newPoly));
        return newPoly;
    }

}
