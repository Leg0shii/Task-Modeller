package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.item.standard.CCircle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CPentagon;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CTriangle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.Square;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import lombok.Getter;

@Getter
public class StandardItemBar extends ItemBar {

    public StandardItemBar() {
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(Square.generateShape());
        itemBar.add(CTriangle.generateShape());
        itemBar.add(CCircle.generateShape());
        itemBar.add(CPentagon.generateShape());
    }

}
