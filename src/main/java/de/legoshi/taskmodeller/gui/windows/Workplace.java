package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.gui.itembar.ItemBarManager;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Workplace extends Group {

    private final ProjectWindow projectWindow;

    private ModelType copiedFromModelType;
    private ArrayList<ModelNode> copiedSymbols;
    private ArrayList<Connection> copiedConnections;

    private ArrayList<WorkplaceNode> commentList;
    private ArrayList<ModelNode> generalisedList;
    private ArrayList<GroupingConnection> groupingConnections;

    private ItemBarManager itemBarManager;

    private PaintWindow selectedPaintWindow;
    private ModelNode selectedSymbol;

    private boolean existent;
    private boolean pWSelected;

    public Workplace(HBox toolBar) {
        this.projectWindow = new ProjectWindow(this);
        this.itemBarManager = new ItemBarManager(this, toolBar);
        this.copiedSymbols = new ArrayList<>();
        this.copiedConnections = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.generalisedList = new ArrayList<>();
        this.groupingConnections = new ArrayList<>();

        this.getChildren().add(projectWindow);
        this.existent = false;
        this.pWSelected = true;
    }

    public ArrayList<PaintWindow> getAllWindows() {
        return projectWindow.getAllWindows();
    }

}
