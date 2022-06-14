package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.model.symbols.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Square;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Triangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class CTTItemBar extends ItemBar {

    public CTTItemBar() {
        this.itemBar = new ArrayList<>();
        this.color = Color.ALICEBLUE;
        prepareItemBar();
    }

    @Override
    public void prepareItemBar() {
        itemBar.add(new AbstractTask(colorPoly(AbstractTask.generateAbstractTask())));
        itemBar.add(new SimpleTask(colorPoly(SimpleTask.createSimpleTask())));
    }
}
