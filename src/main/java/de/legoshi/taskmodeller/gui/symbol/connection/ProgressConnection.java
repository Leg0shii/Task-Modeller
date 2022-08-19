package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.input.MouseEvent;

public class ProgressConnection extends Connection {
    public ProgressConnection(Workplace workplace, ModelNode node1, MouseEvent mouseEvent) {
        super(workplace, node1, mouseEvent);

        workplace.getChildren().add(this);
    }

    public void deleteConnection() {
        getWorkplace().getChildren().remove(this);
    }

}
