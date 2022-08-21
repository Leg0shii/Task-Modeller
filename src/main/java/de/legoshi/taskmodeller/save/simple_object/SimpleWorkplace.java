package de.legoshi.taskmodeller.save.simple_object;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.CTTConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.NodesHelper;
import de.legoshi.taskmodeller.util.PWInitObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SimpleWorkplace {

    private String name;
    private String savePath;
    private int existingWindowCount;
    private int compositeWindowCount;
    private int envisionedWindowCount;

    private ArrayList<ModelNode> groupingNodes;
    private ArrayList<SimpleConnection> groupingConnections;
    private ArrayList<WorkplaceNode> comments;

    private ArrayList<SimplePaintWindow> paintWindows;

    public void applySettingsToWorkplace(Workplace workplace) {
        ProjectWindow projectWindow = workplace.getProjectWindow();
        workplace.setWorkplaceName(name);
        workplace.setSavePath(savePath);

        ArrayList<PWInitObject> exPW = new ArrayList<>();
        ArrayList<PWInitObject> comPW = new ArrayList<>();
        ArrayList<PWInitObject> envPW = new ArrayList<>();

        int count = 0;
        for (SimplePaintWindow simplePaintWindow : paintWindows) {
            PWInitObject pwInitObject = new PWInitObject(simplePaintWindow.getName(), simplePaintWindow.getModelType().name());
            if (count < existingWindowCount) exPW.add(pwInitObject);
            else if (count < (existingWindowCount + compositeWindowCount)) comPW.add(pwInitObject);
            else envPW.add(pwInitObject);
            count++;
        }
        projectWindow.generatePaintWindows(exPW, comPW, envPW);

        count = 0;
        for (SimplePaintWindow simplePaintWindow : paintWindows) {
            for (ModelNode modelNode : simplePaintWindow.getModelNodes()) {
                NodesHelper.prepareDrawable(workplace, modelNode);
                projectWindow.getAllWindows().get(count).addNodeToCanvas(modelNode);
            }
            count++;
        }

        ModelNode m1 = null;
        ModelNode m2 = null;
        for (SimplePaintWindow paintWindow : paintWindows) {
            for (SimpleConnection simpleConnection : paintWindow.getConnections()) {
                for (PaintWindow realPW : projectWindow.getAllWindows()) {
                    for (ModelNode modelNode : realPW.getDrawnNodes()) {
                        if (simpleConnection.getNode1ID().equals(modelNode.getId())) m1 = modelNode;
                        if (simpleConnection.getNode2ID().equals(modelNode.getId())) m2 = modelNode;
                        if (m1 != null && m2 != null) {
                            Connection connection;
                            if (simpleConnection.getClassName().equals("NormalConnection")) connection = new NormalConnection(workplace, m1, m2);
                            else connection = new CTTConnection(workplace, m1, m2);
                            connection.setStroke(simpleConnection.getColor());
                            connection.setStrokeWidth(simpleConnection.getStrokeWidth());
                            connection.getLabel().setText(simpleConnection.getName());
                            realPW.addConnection(connection);
                            m1 = null;
                            m2 = null;
                        }
                    }
                }
            }
        }

        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        for (ModelNode modelNode : groupingNodes) {
            NodesHelper.prepareDrawable(workplace, modelNode);
            paintWindow.addGenNodeToCanvas(modelNode);
        }

        ArrayList<ModelNode> allNodes = new ArrayList<>(groupingNodes);
        for (SimplePaintWindow pW : paintWindows) {
            for (ModelNode modelNode : pW.getModelNodes()) {
                if (!allNodes.contains(modelNode)) allNodes.add(modelNode);
            }
        }

        for (SimpleConnection simpleConnection : groupingConnections) {
            for (ModelNode modelNode : allNodes) {
                if (simpleConnection.getNode1ID().equals(modelNode.getId())) m1 = modelNode;
                if (simpleConnection.getNode2ID().equals(modelNode.getId())) m2 = modelNode;
                if (m1 != null && m2 != null) {
                    GroupingConnection groupingConnection = new GroupingConnection(workplace, m1, m2);
                    groupingConnection.setStroke(simpleConnection.getColor());
                    groupingConnection.setStrokeWidth(simpleConnection.getStrokeWidth());
                    groupingConnection.getLabel().setText(simpleConnection.getName());
                    paintWindow.addConnection(groupingConnection);
                    m1 = null;
                    m2 = null;
                }
            }
        }

        for (WorkplaceNode comment : comments) {
            NodesHelper.prepareDrawable(workplace, comment);
            workplace.getCommentList().add(comment);
            workplace.getChildren().add(comment);
        }
    }
}
