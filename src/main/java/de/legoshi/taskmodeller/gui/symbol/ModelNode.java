package de.legoshi.taskmodeller.gui.symbol;

import de.legoshi.taskmodeller.gui.symbol.connection.*;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.GroupingEditWindow;
import de.legoshi.taskmodeller.gui.windows.editwindow.ItemEditWindow;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ModelNode extends Drawable {

    private ProgressConnection progressConnection;
    private boolean attemptsConnect = false;
    private String description;
    private Label label;

    public ModelNode(Shape shape) {
        super(shape);
        this.setId(UUID.randomUUID().toString());

        label = new Label("Task");
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 12));
        label.setMaxWidth(50);
        label.setMaxHeight(50);
        this.label.setWrapText(true);

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

        if (calcUnderlyingElement(workplace, event, false)) return;

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

        if (calcUnderlyingElement(workplace, event, true)) return;

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
        }

        if (paintWindow != null) {
            for (ModelNode dS : paintWindow.getDrawnNodes()) {
                if (dS.isAttemptsConnect()) {
                    if (dS.equals(this)) return;
                    if (this instanceof GroupingNode) {
                        GroupingConnection modelConnectionNode = GroupingConnection.generateShape(workplace, dS, this);
                        paintWindow.addConnection(modelConnectionNode);
                        dS.setAttemptsConnect(false);
                        return;
                    }
                    if (dS instanceof GroupingNode) break;

                    if (dS instanceof AbstractTask || dS instanceof SimpleTask) {
                        CTTConnection connection = CTTConnection.generateShape(workplace, dS, this);
                        paintWindow.addConnection(connection);
                        dS.setAttemptsConnect(false);
                        return;
                    } else {
                        NormalConnection modelConnectionNode = NormalConnection.generateShape(workplace, dS, this);
                        paintWindow.addConnection(modelConnectionNode);
                        dS.setAttemptsConnect(false);
                    }
                }
            }
            // shouldn't be in here because it only effects generalisedNodes
            for (ModelNode dS : workplace.getGeneralisedList()) {
                if (dS.isAttemptsConnect()) {
                    if (dS.equals(this)) return;
                    GroupingConnection modelConnectionNode = GroupingConnection.generateShape(workplace, dS, this);
                    paintWindow.addConnection(modelConnectionNode);
                    dS.setAttemptsConnect(false);
                }
            }
        }

        if (event.isSecondaryButtonDown()) {
            // shouldn't be in here because only effects generalised Nodes
            if (this instanceof GroupingNode) {
                new GroupingEditWindow(workplace, this).show();
                return;
            }
            new ItemEditWindow(workplace, this).show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

    public void colorSelected() {
        this.getPolyShape().setStrokeWidth(this.getPolyShape().getStrokeWidth()*1.5);
    }

    public void colorUnselected() {
        this.getPolyShape().setStrokeWidth(this.getPolyShape().getStrokeWidth()/1.5);
    }

    private void performSingleSelection(PaintWindow paintWindow) {
        paintWindow.removeSelectedNodes();
        this.colorSelected();
        paintWindow.getSelectedNodes().add(this);
        paintWindow.getWorkplace().setSelectedSymbol(this);
    }

    private boolean windowSwitch(PaintWindow currentActiveWindow) {
        return !(currentActiveWindow.getChildren().contains(this));
    }

    public PaintWindow getPaintWindowOfNode(Workplace workplace) {
        for (PaintWindow paintWindow : workplace.getAllWindows()) {
            if (paintWindow.getChildren().contains(this)) return paintWindow;
        }
        return null;
    }

    private boolean calcUnderlyingElement(Workplace workplace, MouseEvent event, boolean type) {
        double viewportShiftX = workplace.getScrollPane().getViewportBounds().getMinX() *-1;
        double viewportShiftY = workplace.getScrollPane().getViewportBounds().getMinY() *-1;

        Point2D mouseClick = new Point2D((event.getSceneX() - 200 + viewportShiftX) / workplace.getScaleX(), (event.getSceneY() - 25 + viewportShiftY) / workplace.getScaleY());
        if (this instanceof GroupingNode) {
            for (PaintWindow pW : workplace.getAllWindows()) {
                double xCurrentPWOffset = pW.getXPosition() * ProjectWindow.SIZE - 1.5 * pW.getXPosition() * ProjectWindow.HGAP;
                double yCurrentPWOffset = pW.getYPosition() * ProjectWindow.SIZE - 1.5 * pW.getYPosition() * ProjectWindow.VGAP;
                for (ModelNode modelNode : pW.getDrawnNodes()) {
                    Bounds bounds = modelNode.getBoundsInParent();
                    double nX = bounds.getMinX() + xCurrentPWOffset + 100;
                    double nXFar = bounds.getMaxX() + xCurrentPWOffset + 100;
                    double nY = bounds.getMinY() + yCurrentPWOffset + 100 + pW.getYOffsetShift();
                    double nYFar = bounds.getMaxY() + yCurrentPWOffset + 100 + pW.getYOffsetShift();

                    if (mouseClick.getX() >= nX && mouseClick.getX() <= nXFar && mouseClick.getY() >= nY && mouseClick.getY() <= nYFar) {
                        if (modelNode == this) continue;
                        if (modelNode instanceof GroupingNode) continue;
                        if (type) modelNode.onMouseClick(workplace, event);
                        else {
                            if (!pW.getSelectedNodes().contains(modelNode)) continue;
                            modelNode.onMouseDrag(workplace, event);
                        }
                        return true;
                    }
                }
                for (Connection connection : pW.getConnections()) {
                    Bounds bounds = connection.getBoundsInParent();
                    double nX = bounds.getMinX() + xCurrentPWOffset + 100;
                    double nXFar = bounds.getMaxX() + xCurrentPWOffset + 100;
                    double nY = bounds.getMinY() + yCurrentPWOffset + 100 + pW.getYOffsetShift();
                    double nYFar = bounds.getMaxY() + yCurrentPWOffset + 100 + pW.getYOffsetShift();
                    if (mouseClick.getX() >= nX && mouseClick.getX() <= nXFar && mouseClick.getY() >= nY && mouseClick.getY() <= nYFar) {
                        if (event.isSecondaryButtonDown()) {
                            connection.onMousePressed(event);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
