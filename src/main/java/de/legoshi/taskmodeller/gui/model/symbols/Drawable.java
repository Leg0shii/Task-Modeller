package de.legoshi.taskmodeller.gui.model.symbols;

import de.legoshi.taskmodeller.gui.model.windows.Workplace;
import de.legoshi.taskmodeller.gui.model.windows.editwindows.CommentEditWindow;
import de.legoshi.taskmodeller.gui.model.windows.editwindows.ItemEditWindow;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import lombok.Getter;

public abstract class Drawable extends StackPane {

    @Getter
    private final Shape polyShape;

    private double lastX = 0;
    private double lastY = 0;
    private static final int marginLeftX = 5; //10;
    private static final int marginTopY = 5; //10;
    private static final int marginRightX = 55; //110;
    private static final int marginBottomY = 55; //240;

    public Drawable(Shape polyShape) {
        this.polyShape = polyShape;
        this.getChildren().add(polyShape);
    }

    private void onMouseClick(Workplace workplace, MouseEvent event, ModelNode modelNode) {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        if (paintWindow != null) {
            workplace.setSelectedSymbol(modelNode);

            for (ModelNode dS : paintWindow.getDrawnNodes()) {
                if (dS.isAttemptsConnect()) {
                    if (dS.equals(modelNode)) return;
                    ModelConnectionNode modelConnectionNode = new ModelConnectionNode(workplace, dS, modelNode);
                    paintWindow.addConnection(modelConnectionNode);
                    dS.setAttemptsConnect(false);
                }
            }
        }

        if (event.isSecondaryButtonDown()) {
            if (modelNode.isProjectObject()) {
                new CommentEditWindow(workplace, modelNode).show();
                return;
            }
            new ItemEditWindow(workplace, modelNode).show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

    private void onMouseDrag(Workplace workplace, MouseEvent event, ModelNode stPane) {
        double scaleX = workplace.getScaleX();
        double polyTranslateX = stPane.getTranslateX();
        double objectScaleFactorWidth = stPane.getScaleX() * stPane.getLayoutBounds().getWidth() - stPane.getLayoutBounds().getWidth();
        double sceneWidth = stPane.getParent().getLayoutBounds().getWidth();

        double scaleY = workplace.getScaleY();
        double polyTranslateY = stPane.getTranslateY();
        double objectScaleFactorHeight = stPane.getScaleY() * stPane.getLayoutBounds().getHeight() - stPane.getLayoutBounds().getHeight();
        double sceneHeight = stPane.getParent().getLayoutBounds().getHeight();

        if (stPane.isProjectObject()) {
            stPane.setTranslateX(polyTranslateX + (event.getSceneX() - lastX) / scaleX);
            stPane.setTranslateY(polyTranslateY + (event.getSceneY() - lastY) / scaleY);
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            return;
        }

        if (polyTranslateX < (marginLeftX + objectScaleFactorWidth / 2))
            stPane.setTranslateX(marginLeftX + objectScaleFactorWidth / 2);
        else if (polyTranslateX > (sceneWidth - marginRightX - (objectScaleFactorWidth / 2)))
            stPane.setTranslateX((sceneWidth - marginRightX - (objectScaleFactorWidth / 2)));
        else {
            stPane.setTranslateX(polyTranslateX + (event.getSceneX() - lastX) / scaleX);
            this.lastX = event.getSceneX();
        }

        if (polyTranslateY < (marginTopY + objectScaleFactorHeight / 2))
            stPane.setTranslateY(marginTopY + objectScaleFactorHeight / 2);
        else if (polyTranslateY > (sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)))
            stPane.setTranslateY((sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)));
        else {
            stPane.setTranslateY(polyTranslateY + (event.getSceneY() - lastY) / scaleY);
            this.lastY = event.getSceneY();
        }

    }

    public ModelNode getDuplicate(Workplace workplace, boolean projectObject) {
        ModelNode modelNode = new ModelNode(polyShape, projectObject);
        registerEvents(workplace, modelNode);
        return modelNode;
    }

    private void registerEvents(Workplace workplace, ModelNode modelNode) {
        for (Node n : modelNode.getChildren()) {
            n.setOnMousePressed(event -> onMouseClick(workplace, event, modelNode));
            n.setOnMouseDragged(event -> onMouseDrag(workplace, event, modelNode));
        }
    }

}
