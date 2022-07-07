package de.legoshi.taskmodeller.gui.model.symbols;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.gui.model.windows.ItemEditWindow;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import lombok.Getter;

public abstract class Drawable extends StackPane {

    @Getter
    private final Polygon polyShape;

    private double lastX = 0;
    private double lastY = 0;
    private static final int marginLeftX = 5; //10;
    private static final int marginTopY = 5; //10;
    private static final int marginRightX = 55; //110;
    private static final int marginBottomY = 55; //240;

    public Drawable(Polygon polyShape) {
        this.polyShape = polyShape;
        this.getChildren().add(polyShape);
    }

    private void onMouseClick(MouseEvent event, DrawnSymbol drawnSymbol) {

        MainController mainController = MainController.getInstance();
        PaintWindow paintWindow = mainController.getProject().getSelectedPaintWindow();
        for (DrawnSymbol dS : paintWindow.getDrawnNodes()) {
            if (dS.isAttemptsConnect()) {
                if (dS.equals(drawnSymbol)) return;
                NodeConnection nodeConnection = new NodeConnection(dS, drawnSymbol);
                paintWindow.addConnection(nodeConnection);
                dS.setAttemptsConnect(false);
            }

        }

        if (event.isSecondaryButtonDown()) {
            ItemEditWindow itemEditWindow = new ItemEditWindow(drawnSymbol);
            itemEditWindow.show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

    private void onMouseDrag(MouseEvent event, DrawnSymbol stPane) {
        double polyTranslateX = stPane.getTranslateX();
        double objectScaleFactorWidth = stPane.getScaleX() * stPane.getLayoutBounds().getWidth() - stPane.getLayoutBounds().getWidth();
        double sceneWidth = stPane.getParent().getLayoutBounds().getWidth();

        if (polyTranslateX < (marginLeftX + objectScaleFactorWidth / 2))
            stPane.setTranslateX(marginLeftX + objectScaleFactorWidth / 2);
        else if (polyTranslateX > (sceneWidth - marginRightX - (objectScaleFactorWidth / 2)))
            stPane.setTranslateX((sceneWidth - marginRightX - (objectScaleFactorWidth / 2)));
        else {
            stPane.setTranslateX(polyTranslateX + event.getSceneX() - lastX);
            this.lastX = event.getSceneX();
        }

        double polyTranslateY = stPane.getTranslateY();
        double objectScaleFactorHeight = stPane.getScaleY() * stPane.getLayoutBounds().getHeight() - stPane.getLayoutBounds().getHeight();
        double sceneHeight = stPane.getParent().getLayoutBounds().getHeight();

        if (polyTranslateY < (marginTopY + objectScaleFactorHeight / 2))
            stPane.setTranslateY(marginTopY + objectScaleFactorHeight / 2);
        else if (polyTranslateY > (sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)))
            stPane.setTranslateY((sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)));
        else {
            stPane.setTranslateY(polyTranslateY + event.getSceneY() - lastY);
            this.lastY = event.getSceneY();
        }

        MainController mainController = MainController.getInstance();
        PaintWindow paintWindow = mainController.getProject().getSelectedPaintWindow();
        for (NodeConnection nC : paintWindow.getConnections()) {
            if (nC.getNode1().getId().equals(stPane.getId()) || nC.getNode2().getId().equals(stPane.getId())) {
                if (nC.isPosition()) nC.recalculateBindings();
            }
        }

    }

    public DrawnSymbol getDuplicate() {
        DrawnSymbol drawnSymbol = new DrawnSymbol(polyShape);
        registerEvents(drawnSymbol);
        return drawnSymbol;
    }

    private void registerEvents(DrawnSymbol drawnSymbol) {
        for (Node n : drawnSymbol.getChildren()) {
            n.setOnMousePressed(event -> onMouseClick(event, drawnSymbol));
            n.setOnMouseDragged(event -> onMouseDrag(event, drawnSymbol));
        }
    }

}
