package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.misc.GeneralisedNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class MiscItemBar extends ItemBar {

    public MiscItemBar() {
        prepareItemBar();
    }

    @Override
    void prepareItemBar() {
        itemBar.add(TextSymbol.generateShape());
        itemBar.add(GeneralisedNode.generateShape());
    }

}
