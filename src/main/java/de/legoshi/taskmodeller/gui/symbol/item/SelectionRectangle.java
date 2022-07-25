package de.legoshi.taskmodeller.gui.symbol.item;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectionRectangle extends Group {

    private Line firstLine = new Line();
    private Line secondLine = new Line();
    private Line thirdLine = new Line();
    private Line fourthLine = new Line();

    private double startX;
    private double startY;
    private double newXPos;
    private double newYPos;

    public SelectionRectangle(MouseEvent mouseEvent) {
        this.startX = mouseEvent.getX();
        this.startY = mouseEvent.getY();
        this.getChildren().add(firstLine);
        this.getChildren().add(secondLine);
        this.getChildren().add(thirdLine);
        this.getChildren().add(fourthLine);
        updateRect(startX, startY);
    }

    public void updateRect(double newXPos, double newYPos) {
        if (newXPos > 0 && newXPos < 499) this.newXPos = newXPos;
        else this.newXPos = ((newXPos > 0) ? 497 : 3);
        if (newYPos > 0 && newYPos < 499) this.newYPos = newYPos;
        else this.newYPos = ((newYPos > 0) ? 497 : 3);
        updateLine(firstLine, startX, startY, startX, newYPos);
        updateLine(secondLine, startX, newYPos, newXPos, newYPos);
        updateLine(thirdLine, startX, startY, newXPos, startY);
        updateLine(fourthLine, newXPos, startY, newXPos, newYPos);
    }

    private void updateLine(Line line, double startX, double startY, double endX, double endY) {
        if (startX > 0 && startX < 499) line.setStartX(startX);
        else line.setStartX((startX > 0) ? 497 : 3);
        if (startY > 0 && startY < 499) line.setStartY(startY);
        else line.setStartY((startY > 0) ? 497 : 3);
        if (endX > 0 && endX < 499) line.setEndX(endX);
        else line.setEndX((endX > 0) ? 497 : 3);
        if (endY > 0 && endY < 499) line.setEndY(endY);
        else line.setEndY((endY > 0) ? 497 : 3);
    }

    public void invalidate() {
        this.startX = 0;
        this.startY = 0;
        this.newXPos = 0;
        this.newYPos = 0;
    }

}
