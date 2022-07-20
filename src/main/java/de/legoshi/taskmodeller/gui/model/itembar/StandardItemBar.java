package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.standard.CTriangle;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Square;
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
        colorize();
    }

}
