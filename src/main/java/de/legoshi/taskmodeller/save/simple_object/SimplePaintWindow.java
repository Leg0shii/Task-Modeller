package de.legoshi.taskmodeller.save.simple_object;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SimplePaintWindow {

    private String name;

    private ModelType modelType;
    private StatusType statusType;

    private ArrayList<ModelNode> modelNodes;
    private ArrayList<SimpleConnection> connections;

}
