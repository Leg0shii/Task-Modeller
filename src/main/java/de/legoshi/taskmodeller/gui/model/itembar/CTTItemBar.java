package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.model.symbols.ctt.SimpleTask;
import javafx.scene.paint.Color;

public class CTTItemBar extends ItemBar {

    public CTTItemBar() {
        this.color = Color.ALICEBLUE;
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(AbstractTask.generateShape());
        itemBar.add(SimpleTask.generateShape());
        colorize();
    }

}
