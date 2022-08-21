package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import javafx.scene.control.Label;

public class AboutWindow extends DialogWindow {

    public AboutWindow() {
        this.setTitle("About");
        Label label = new Label("Created by Benjamin Müller. (2022)");
        label.setWrapText(true);
        this.gridPane.add(label, 0, this.gridPane.getRowCount());
    }

}
