package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class CTTItemBar extends ItemBar {

    private final Workplace workplace;

    public CTTItemBar(Workplace workplace) {
        this.workplace = workplace;
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(AbstractTask.generateShape(this.workplace));
        itemBar.add(SimpleTask.generateShape(this.workplace));
    }

}
