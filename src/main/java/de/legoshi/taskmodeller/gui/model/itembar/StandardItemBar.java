package de.legoshi.taskmodeller.gui.model.itembar;

import de.legoshi.taskmodeller.gui.model.symbols.standard.Square;
import de.legoshi.taskmodeller.gui.model.symbols.standard.Triangle;
import lombok.Getter;

@Getter
public class StandardItemBar extends ItemBar {

    public StandardItemBar() {
        prepareItemBar();
    }

    public void prepareItemBar() {
        itemBar.add(new Square(colorPoly(Square.generateSquare())));
        itemBar.add(new Triangle(colorPoly(Triangle.generateTriangle())));
    }

}
