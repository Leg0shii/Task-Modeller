package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.standard.SmallLine;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Square;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Triangle;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class StandardItemBar extends ItemBar {

    public StandardItemBar() {
        this.itemBar = new ArrayList<>();
        prepareItemBar();
    }

    public void prepareItemBar() {
        itemBar.add(new Square(colorPoly(Square.generateSquare())));
        itemBar.add(new Triangle(colorPoly(Triangle.generateTriangle())));
        itemBar.add(new SmallLine(colorPoly(SmallLine.generateLine())));
    }

}
