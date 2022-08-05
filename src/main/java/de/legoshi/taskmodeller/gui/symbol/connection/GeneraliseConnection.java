package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class GeneraliseConnection extends Connection {

    // assumed node1 is the generalisedNode
    public GeneraliseConnection(Workplace workplace, ModelNode node1, ModelNode node2) {
        super(workplace, node1, node2);

        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                System.out.println("Mouse Click");
            }
        });

    }

    @Override
    public void recalculateBindings() {
        PaintWindow currentPW = node2.getPaintWindowOfNode(getWorkplace());
        double xOffset = currentPW.getXPosition() * ProjectWindow.SIZE - 1.5 * currentPW.getXPosition() * ProjectWindow.HGAP;
        double yOffset = currentPW.getYPosition() * ProjectWindow.SIZE - 1.5 * currentPW.getYPosition() * ProjectWindow.VGAP;
        this.xStartProperty = node1.translateXProperty().add(node1.getWidth()/2);
        this.yStartProperty = node1.translateYProperty().add(node1.getHeight()/2);
        this.xEndProperty = node2.translateXProperty().add(node2.getWidth()/2).add(xOffset);
        this.yEndProperty = node2.translateYProperty().add(node2.getHeight()/2).add(yOffset);
        setBindings();
    }

    public static GeneraliseConnection generateShape(Workplace workplace, ModelNode node1, ModelNode node2) {
        return new GeneraliseConnection(workplace, node1, node2);
    }

}
