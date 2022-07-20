package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.misc.TextSymbol;
import javafx.scene.paint.Color;

public class MiscItemBar extends ItemBar {

    public MiscItemBar() {
        this.color = Color.LIGHTYELLOW;
        prepareItemBar();
    }

    @Override
    void prepareItemBar() {
        itemBar.add(new TextSymbol(colorPoly(TextSymbol.generateTextSymbol())));
    }

}
