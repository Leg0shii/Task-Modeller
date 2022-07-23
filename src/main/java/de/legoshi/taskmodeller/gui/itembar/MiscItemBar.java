package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class MiscItemBar extends ItemBar {

    private final Workplace workplace;

    public MiscItemBar(Workplace workplace) {
        this.workplace = workplace;
        prepareItemBar();
    }

    @Override
    void prepareItemBar() {
        itemBar.add(TextSymbol.generateShape(this.workplace));
    }

}
