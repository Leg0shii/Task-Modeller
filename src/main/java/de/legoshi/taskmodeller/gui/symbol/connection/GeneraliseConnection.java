package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GeneralisedNode;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.CalculationLine;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GeneraliseConnection extends Connection {

    private Bounds node1Bounds;
    private Bounds node2Bounds;

    // assumed node1 is the generalisedNode
    public GeneraliseConnection(Workplace workplace, ModelNode node1, ModelNode node2) {
        super(workplace, node1, node2);

        this.node1Bounds = node1.getBoundsInParent();
        this.node2Bounds = node2.getBoundsInParent();

        // to switch nodes
        if (!(node1 instanceof GeneralisedNode)) {
            ModelNode temp = node1;
            node1 = node2;
            node2 = temp;
        }

        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                // add edit window
            }
        });

        for (Node n : node1.getChildren()) n.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> recalculateBindings());
        for (Node n : node2.getChildren()) n.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> recalculateBindings());
    }

    @Override
    public void recalculateBindings() {
        this.node1Bounds = node1.getBoundsInParent();
        this.node2Bounds = node2.getBoundsInParent();

        PaintWindow genPW = node1.getPaintWindowOfNode(getWorkplace());
        PaintWindow currentPW = node2.getPaintWindowOfNode(getWorkplace());

        // stops updating when connection is deleted... (bad)
        if (genPW == null || currentPW == null) return;

        double xCurrentPWOffset = currentPW.getXPosition() * ProjectWindow.SIZE - 1.5 * currentPW.getXPosition() * ProjectWindow.HGAP;
        double yCurrentPWOffset = currentPW.getYPosition() * ProjectWindow.SIZE - 1.5 * currentPW.getYPosition() * ProjectWindow.VGAP;
        double xGenPWOffset = genPW.getXPosition() * ProjectWindow.SIZE - 1.5 * genPW.getXPosition() * ProjectWindow.HGAP;
        double yGenPWOffset = genPW.getYPosition() * ProjectWindow.SIZE - 1.5 * genPW.getYPosition() * ProjectWindow.VGAP;

        Point2D centerNode1 = new Point2D(node1Bounds.getCenterX() + xGenPWOffset, node1Bounds.getCenterY() + yGenPWOffset);
        Point2D centerNode2 = new Point2D(node2Bounds.getCenterX() + xCurrentPWOffset, node2Bounds.getCenterY() + yCurrentPWOffset);
        CalculationLine connection = new CalculationLine(centerNode1.getX(), centerNode1.getY(), centerNode2.getX(), centerNode2.getY());

        Point2D node1Point = getIntersectionPoint(node1Bounds, centerNode2, connection, xGenPWOffset, yGenPWOffset);
        Point2D node2Point = getIntersectionPoint(node2Bounds, centerNode1, connection, xCurrentPWOffset, yCurrentPWOffset);

        if (node1Point != null) {
            double node1XScaleShift = (node1.getWidth() / 2 * (node1.getScaleX() - 1));
            double node1YScaleShift = (node1.getHeight() / 2 * (node1.getScaleY() - 1));
            this.xStartProperty = node1.translateXProperty().add(Math.abs(node1Bounds.getMinX() + xGenPWOffset - node1Point.getX()) + xGenPWOffset - node1XScaleShift);
            this.yStartProperty = node1.translateYProperty().add(Math.abs(node1Bounds.getMinY() + yGenPWOffset - node1Point.getY()) + yGenPWOffset - node1YScaleShift);
        }
        if (node2Point != null) {
            double node2XScaleShift = (node2.getWidth() / 2 * (node2.getScaleX() - 1));
            double node2YScaleShift = (node2.getHeight() / 2 * (node2.getScaleY() - 1));
            this.xEndProperty = node2.translateXProperty().add(Math.abs(node2Bounds.getMinX() + xCurrentPWOffset - node2Point.getX()) + xCurrentPWOffset - node2XScaleShift);
            this.yEndProperty = node2.translateYProperty().add(Math.abs(node2Bounds.getMinY() + yCurrentPWOffset - node2Point.getY()) + yCurrentPWOffset - node2YScaleShift);
        }
        setBindings();
    }

    private Point2D getIntersectionPoint(Bounds nodeBounds, Point2D centerNode, CalculationLine connection, double xOffset, double yOffset) {
        double nX = nodeBounds.getMinX() + xOffset;
        double nXFar = nodeBounds.getMaxX() + xOffset;
        double nY = nodeBounds.getMinY() + yOffset;
        double nYFar = nodeBounds.getMaxY() + yOffset;

        CalculationLine calculationLineTop = new CalculationLine(nX, nY, nXFar, nY);
        CalculationLine calculationLineRight = new CalculationLine(nXFar, nY, nXFar, nYFar);
        CalculationLine calculationLineLeft = new CalculationLine(nX, nY, nX, nYFar);
        CalculationLine calculationLineBottom = new CalculationLine(nX, nYFar, nXFar, nYFar);

        Point2D p1 = connection.intersect(calculationLineBottom);
        Point2D p2 = connection.intersect(calculationLineTop);
        Point2D p3 = connection.intersect(calculationLineLeft);
        Point2D p4 = connection.intersect(calculationLineRight);
        ArrayList<Point2D> list = new ArrayList<>() {{ add(p1); add(p2); add(p3); add(p4); }};

        Point2D nodePoint = null;
        double min = Double.MAX_VALUE;
        for (Point2D p : list) {
            if (p.getX() >= nX && p.getX() <= nXFar && p.getY() >= nY && p.getY() <= nYFar) {
                if (centerNode.distance(p) <= min) {
                    min = centerNode.distance(p);
                    nodePoint = p;
                }
            }
        }
        return nodePoint;
    }

    public static GeneraliseConnection generateShape(Workplace workplace, ModelNode node1, ModelNode node2) {
        return new GeneraliseConnection(workplace, node1, node2);
    }

}
