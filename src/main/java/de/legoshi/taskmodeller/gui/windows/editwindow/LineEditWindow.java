package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.Label;

public class LineEditWindow extends EditWindow<Connection> {

    private final Workplace workplace;

    public LineEditWindow(Workplace workplace, Connection item) {
        super(item, "Bearbeite Linie");
        this.workplace = workplace;

        Label checkBoxLabel = new Label("Auto-Positionierung");
        this.gridPane.add(checkBoxLabel, 0, 0);
        this.deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());
    }

    @Override
    public void onDelete() {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        paintWindow.removeConnection(this.item);
        this.close();
    }

}
