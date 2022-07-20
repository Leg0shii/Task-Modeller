package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import javafx.scene.control.Label;

public class LineEditWindow extends EditWindow<NodeConnection> {

    public LineEditWindow(NodeConnection item) {
        super(item, "Bearbeite Linie");

        Label checkBoxLabel = new Label("Auto-Positionierung");
        this.gridPane.add(checkBoxLabel, 0, 0);

        this.deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());
    }

    private void onDelete() {
        MainController mainController = MainController.getInstance();
        PaintWindow paintWindow = mainController.getProject().getSelectedPaintWindow();
        paintWindow.removeConnection(this.item);
        this.close();
    }

}
