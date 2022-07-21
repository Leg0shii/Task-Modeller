package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import de.legoshi.taskmodeller.gui.model.symbols.ModelConnectionNode;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.model.windows.Workplace;
import javafx.scene.control.Label;

public class LineEditWindow extends EditWindow<ModelConnectionNode> {

    private final Workplace workplace;

    public LineEditWindow(Workplace workplace, ModelConnectionNode item) {
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
