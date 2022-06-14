package de.legoshi.taskmodeller.gui.model.symbols.helper;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import lombok.Getter;

import java.util.UUID;

public abstract class Drawable implements IShape<Polygon> {

    private final UUID id;
    @Getter
    private final Polygon shape;

    private double lastX = 0;
    private double lastY = 0;
    private static final int marginLeftX = 5; //10;
    private static final int marginTopY = 5; //10;
    private static final int marginRightX = 55; //110;
    private static final int marginBottomY = 55; //240;

    public Drawable(UUID randomUUID, Polygon shape) {
        this.id = randomUUID;
        this.shape = shape;
        // StandardItemBar.colorPoly(shape);
    }

    private void onMouseClick(MouseEvent event) {
        this.lastX = event.getSceneX();
        this.lastY = event.getSceneY();
        event.consume();
    }

    private void onMouseDrag(MouseEvent event, Polygon poly) {
        double polyTranslateX = poly.getTranslateX();
        double objectScaleFactorWidth = poly.getScaleX() * poly.getLayoutBounds().getWidth() - poly.getLayoutBounds().getWidth();
        double sceneWidth = poly.getParent().getLayoutBounds().getWidth();

        if (polyTranslateX < (marginLeftX + objectScaleFactorWidth / 2))
            poly.setTranslateX(marginLeftX + objectScaleFactorWidth / 2);
        else if (polyTranslateX > (sceneWidth - marginRightX - (objectScaleFactorWidth / 2)))
            poly.setTranslateX((sceneWidth - marginRightX - (objectScaleFactorWidth / 2)));
        else {
            poly.setTranslateX(polyTranslateX + event.getSceneX() - lastX);
            this.lastX = event.getSceneX();
        }

        double polyTranslateY = poly.getTranslateY();
        double objectScaleFactorHeight = poly.getScaleX() * poly.getLayoutBounds().getWidth() - poly.getLayoutBounds().getHeight();
        double sceneHeight = poly.getParent().getLayoutBounds().getHeight();

        if (polyTranslateY < (marginTopY + objectScaleFactorHeight / 2))
            poly.setTranslateY(marginTopY + objectScaleFactorHeight / 2);
        else if (polyTranslateY > (sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)))
            poly.setTranslateY((sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)));
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
