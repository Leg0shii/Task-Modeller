package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import javafx.scene.control.Label;

public class HelpWindow extends DialogWindow {

    public HelpWindow() {
        this.setMaxWidth(750);
        this.setMaxHeight(750);
        this.setTitle("Help");
        addHelpLabel("You can right click every node and every connection to change its properties.");
        addHelpLabel("Hold down your left mouse key while on a drawing area, to select multiple nodes. \n" +
                "     By pressing CTRL + C, you copy all selected nodes. \n" +
                "     By pressing CTRL + V, you can paste those nodes.");
        addHelpLabel("Make sure to click on Save Project. Otherwise your progress will be lost.");
    }

    private void addHelpLabel(String s) {
        Label label = new Label(" ⚫ " + s);
        label.setWrapText(true);
        this.gridPane.add(label, 0, this.gridPane.getRowCount());
    }

}
