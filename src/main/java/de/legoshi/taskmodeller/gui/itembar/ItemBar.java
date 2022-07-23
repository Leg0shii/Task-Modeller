package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.Drawable;

import java.util.ArrayList;

public abstract class ItemBar {

    public ArrayList<Drawable> itemBar = new ArrayList<>();

    abstract void prepareItemBar();

}
