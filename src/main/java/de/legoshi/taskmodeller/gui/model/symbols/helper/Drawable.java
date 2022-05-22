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
    private static final int marginLeftX = 10;
    private static final int marginTopY = 10;
    private static final int marginRightX = 80;
    private static final int marginBottomY = 210;

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
        double polyTranslateX = poly.getTranslateX();
        double sceneWidth = poly.getScene().getWidth();

        if(polyTranslateX < marginLeftX) poly.setTranslateX(marginLeftX);
        else if (polyTranslateX > (sceneWidth - marginRightX)) poly.setTranslateX((sceneWidth - marginRightX));
        else {
            poly.setTranslateX(polyTranslateX + event.getSceneX() - lastX);
            this.lastX = event.getSceneX();
        }

        double polyTranslateY = poly.getTranslateY();
        double sceneHeight = poly.getScene().getHeight();

        if(polyTranslateY < marginTopY) poly.setTranslateY(marginTopY);
        else if (polyTranslateY > (sceneHeight - marginBottomY)) poly.setTranslateY((sceneHeight - marginBottomY));
        else {
            poly.setTranslateY(polyTranslateY + event.getSceneY() - lastY);
            this.lastY = event.getSceneY();
        }
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
