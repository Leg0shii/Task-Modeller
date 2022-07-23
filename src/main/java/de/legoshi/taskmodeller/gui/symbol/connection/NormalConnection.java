package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class NormalConnection extends ModelConnectionNode {

    public NormalConnection(Workplace workplace, ModelNode node1, ModelNode node2) {
        super(workplace, node1, node2);
    }

    public static NormalConnection generateShape(Workplace workplace, ModelNode node1, ModelNode node2) {
        return new NormalConnection(workplace, node1, node2);
    }

}
