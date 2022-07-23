package de.legoshi.taskmodeller.util;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CTriangle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.Square;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class NodesHelper {

    public static Drawable getDuplicate(Workplace workplace, Drawable drawable) {
        if (drawable instanceof Square) return Square.generateShape(workplace);
        else if (drawable instanceof CTriangle) return CTriangle.generateShape(workplace);
        else if (drawable instanceof TextSymbol) return TextSymbol.generateShape(workplace);
        else if (drawable instanceof SimpleTask) return SimpleTask.generateShape(workplace);
        else return AbstractTask.generateShape(workplace);
    }

}
