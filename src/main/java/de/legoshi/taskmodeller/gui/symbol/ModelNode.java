package de.legoshi.taskmodeller.gui.symbol;

import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.ItemEditWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ModelNode extends Drawable {

    private boolean attemptsConnect = false;
    private String description;
    private Label label;

    public ModelNode(Shape shape) {
        super(shape);
        this.setId(UUID.randomUUID().toString());

        label = new Label("Task");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));
        label.setMaxWidth(50);
        label.setMaxHeight(50);

        this.getChildren().add(shape);
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void onMouseDrag(Workplace workplace, MouseEvent event) {
        if (this.getParent() == null) {
            event.consume();
            return;
        }

        double scaleX = workplace.getScaleX();
        double polyTranslateX = this.getTranslateX();
        double objectScaleFactorWidth = this.getScaleX() * this.getLayoutBounds().getWidth() - this.getLayoutBounds().getWidth();
        double sceneWidth = this.getParent().getLayoutBounds().getWidth();

        double scaleY = workplace.getScaleY();
        double polyTranslateY = this.getTranslateY();
        double objectScaleFactorHeight = this.getScaleY() * this.getLayoutBounds().getHeight() - this.getLayoutBounds().getHeight();
        double sceneHeight = this.getParent().getLayoutBounds().getHeight();

        if (polyTranslateX < (marginLeftX + objectScaleFactorWidth / 2))
            this.setTranslateX(marginLeftX + objectScaleFactorWidth / 2);
        else if (polyTranslateX > (sceneWidth - marginRightX - (objectScaleFactorWidth / 2)))
            this.setTranslateX((sceneWidth - marginRightX - (objectScaleFactorWidth / 2)));
        else {
            this.setTranslateX(polyTranslateX + (event.getSceneX() - lastX) / scaleX);
            this.lastX = event.getSceneX();
        }

        if (polyTranslateY < (marginTopY + objectScaleFactorHeight / 2))
            this.setTranslateY(marginTopY + objectScaleFactorHeight / 2);
        else if (polyTranslateY > (sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)))
            this.setTranslateY((sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)));
        else {
            this.setTranslateY(polyTranslateY + (event.getSceneY() - lastY) / scaleY);
            this.lastY = event.getSceneY();
        }
        event.consume();
    }


    @Override
    public void onMouseClick(Workplace workplace, MouseEvent event) {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();

        // to prevent manipulation in item bar area
        if (this.getParent() != null) performSingleSelection(paintWindow);
        if (paintWindow != null) {
            if (windowSwitch(paintWindow)) {
                PaintWindow newlyActiveWindow = getPaintWindowOfNode(workplace);
                if (newlyActiveWindow != null) {
                    newlyActiveWindow.setActiveWindow();
                    performSingleSelection(newlyActiveWindow);
                }
            }

            for (ModelNode dS : paintWindow.getDrawnNodes()) {
                if (dS.isAttemptsConnect()) {
                    if (dS.equals(this)) return;
                    NormalConnection modelConnectionNode = NormalConnection.generateShape(workplace, dS, this);
                    paintWindow.addConnection(modelConnectionNode);
                    dS.setAttemptsConnect(false);
                }
            }
        }

        if (event.isSecondaryButtonDown()) {
            new ItemEditWindow(workplace, this).show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

    public void colorSelected() {
        this.getPolyShape().setStroke(Color.RED);
    }

    public void colorUnselected() {
        this.getPolyShape().setStroke(Color.BLACK);
    }

    private void performSingleSelection(PaintWindow paintWindow) {
        paintWindow.removeSelectedNodes();
        this.colorSelected();
        paintWindow.getSelectedNodes().add(this);
    }

    private boolean windowSwitch(PaintWindow currentActiveWindow) {
        return !(currentActiveWindow.getChildren().contains(this));
    }

    private PaintWindow getPaintWindowOfNode(Workplace workplace) {
        for (PaintWindow paintWindow : workplace.getAllWindows()) {
            if (paintWindow.getChildren().contains(this)) return paintWindow;
        }
        return null;
    }

}
