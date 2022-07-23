package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.standard.CTriangle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.Square;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import lombok.Getter;

@Getter
public class StandardItemBar extends ItemBar {

    private final Workplace workplace;

    public StandardItemBar(Workplace workplace) {
        this.workplace = workplace;
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(Square.generateShape(workplace));
        itemBar.add(CTriangle.generateShape(this.workplace));
    }

}
