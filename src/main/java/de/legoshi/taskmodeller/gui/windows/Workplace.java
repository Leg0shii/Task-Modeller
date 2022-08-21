package de.legoshi.taskmodeller.gui.windows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.legoshi.taskmodeller.gui.itembar.ItemBarManager;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.save.DeserializeManager;
import de.legoshi.taskmodeller.save.serialize.ProjectSerializer;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public class Workplace extends Group {

    private ProjectWindow projectWindow;
    private String workplaceName;
    private String savePath;

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

    public Workplace(ItemBarManager itemBarManager) {
        initWorkplace();
        this.projectWindow = new ProjectWindow(this);
        this.getChildren().add(projectWindow);
        this.itemBarManager = itemBarManager;
        this.itemBarManager.setWorkplace(this);
        this.existent = false;
        this.pWSelected = true;
    }

    public ArrayList<PaintWindow> getAllWindows() {
        return projectWindow.getAllWindows();
    }

    public void initWorkplace() {
        this.copiedSymbols = new ArrayList<>();
        this.copiedConnections = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.generalisedList = new ArrayList<>();
        this.groupingConnections = new ArrayList<>();
        this.existent = false;
        this.pWSelected = true;
    }

    public void saveWorkspace() throws IOException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(Workplace.class, new ProjectSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File f = new File(this.savePath);
        mapper.writeValue(f, this);
    }

    public void reloadWorkspaceFromFile(File file) {
        this.initWorkplace();
        this.setWorkplaceName(file.getName());
        this.setSavePath(file.getParent());
        this.setExistent(true);
        DeserializeManager.applyWorkplaceChanges(this, file);
    }

}
