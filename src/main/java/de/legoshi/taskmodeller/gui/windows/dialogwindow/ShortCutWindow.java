package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import javafx.scene.control.Label;

public class ShortCutWindow extends DialogWindow {

    public ShortCutWindow() {
        this.setMaxWidth(750);
        this.setMaxHeight(750);
        this.setTitle("Shortcuts");
        addShortCutLabel("CTRL + C: Copy selected nodes");
        addShortCutLabel("CTRL + V: Paste copied nodes");
        addShortCutLabel("CTRL + F: Center on selected modelling area");
        addShortCutLabel("CTRL + G: Center entire screen");
    }

    private void addShortCutLabel(String s) {
        Label label = new Label(" ⚫ " + s);
        label.setWrapText(true);
        this.gridPane.add(label, 0, this.gridPane.getRowCount());
    }

}
