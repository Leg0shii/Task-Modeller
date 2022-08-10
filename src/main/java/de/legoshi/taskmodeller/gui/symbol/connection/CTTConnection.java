package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.editwindow.CTTLineEditWindow;

public class CTTConnection extends Connection {

    // different edit window

    public CTTConnection(Workplace workplace, ModelNode node1, ModelNode node2) {
        super(workplace, node1, node2);

        this.getLabel().setText("");

        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                new CTTLineEditWindow(workplace, this).show();
            }
        });

        this.getLabel().setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                new CTTLineEditWindow(workplace, this).show();
            }
        });
    }

    public static CTTConnection generateShape(Workplace workplace, ModelNode node1, ModelNode node2) {
        return new CTTConnection(workplace, node1, node2);
    }

}
