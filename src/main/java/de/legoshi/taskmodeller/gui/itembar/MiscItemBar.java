package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;

public class MiscItemBar extends ItemBar {

    public MiscItemBar() {
        prepareItemBar();
    }

    @Override
    void prepareItemBar() {
        itemBar.add(TextSymbol.generateShape());
        itemBar.add(GroupingNode.generateShape());
    }

}
