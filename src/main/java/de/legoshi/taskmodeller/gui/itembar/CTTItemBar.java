package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class CTTItemBar extends ItemBar {

    public CTTItemBar() {
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(AbstractTask.generateShape());
        itemBar.add(SimpleTask.generateShape());
    }

}
