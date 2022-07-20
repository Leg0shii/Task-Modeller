package de.legoshi.taskmodeller.gui.model.itembar;

import javafx.scene.paint.Color;

public class ConnectionItemBar extends ItemBar {

    public ConnectionItemBar() {
        this.color = Color.ORANGE;
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {

        colorize();
    }

}
