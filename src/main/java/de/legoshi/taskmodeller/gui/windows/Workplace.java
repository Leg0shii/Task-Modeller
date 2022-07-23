package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.gui.itembar.ItemBarManager;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Workplace extends Group {

    private final ProjectWindow projectWindow;
    private ArrayList<ModelNode> commentList;

    private ItemBarManager itemBarManager;

    private PaintWindow selectedPaintWindow;
    private ModelNode selectedSymbol;

    public Workplace(HBox toolBar) {
        this.projectWindow = new ProjectWindow(this);
        this.itemBarManager = new ItemBarManager(this, toolBar);

        this.getChildren().add(projectWindow);
    }

    public ArrayList<PaintWindow> getAllWindows() {
        return projectWindow.getAllWindows();
    }

}
