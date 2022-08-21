package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import javafx.scene.control.Label;

public class PopUpWindow extends DialogWindow {

    public PopUpWindow(String title, String text) {
        this.setTitle(title);
        this.gridPane.add(new Label(text), 0, 0);
    }

}
