package de.legoshi.taskmodeller.gui.symbol.connection;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.input.MouseEvent;

public class ProgressConnection extends Connection {

    private final Workplace workplace;
    public ProgressConnection(Workplace workplace, ModelNode node1) {
        super(workplace, node1);

        this.workplace = workplace;

        workplace.addEventFilter(MouseEvent.MOUSE_MOVED, super::recalculateMouseBindings);
        workplace.getChildren().add(this);
    }

    public void deleteConnection() {
        workplace.removeEventFilter(MouseEvent.MOUSE_MOVED, super::recalculateMouseBindings);
        getWorkplace().getChildren().remove(this);
    }

}
