package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.CTTConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.NodesHelper;
import javafx.scene.layout.HBox;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ItemBarManager {

    private final Workplace workplace;
    private final HBox toolBar;

    private final StandardItemBar standardItemBar;
    private final CTTItemBar cttItemBar;
    private final ConnectionItemBar connectionItemBar;
    private final MiscItemBar miscItemBar;

    public ItemBarManager(Workplace workplace, HBox toolBar) {
        this.standardItemBar = new StandardItemBar();
        this.cttItemBar = new CTTItemBar();
        this.connectionItemBar = new ConnectionItemBar();
        this.miscItemBar = new MiscItemBar();
        this.workplace = workplace;
        this.toolBar = toolBar;
    }

    public void reloadItemBarWithModel(PaintWindow selectedWindow, ModelType modelType1) {
        ModelType modelType = selectedWindow.getModelType();
        ArrayList<Drawable> itemBar = this.getModelItemBar(modelType);
        this.reloadItemBar(selectedWindow, modelType);
        toolBar.getChildren().clear();

        for (Drawable drawable : itemBar) {
            toolBar.getChildren().add(drawable);
        }
    }

    public ArrayList<Drawable> getModelItemBar(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else if (modelType.equals(ModelType.CONN)) itemBar = connectionItemBar.itemBar;
        else if (modelType.equals(ModelType.MISC)) itemBar = miscItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;
        return itemBar;
    }
    
    public void reloadItemBar(PaintWindow selectedWindow, ModelType modelType) {
        if (modelType.equals(ModelType.CTT)) onCTTAction(selectedWindow);
        else if (modelType.equals(ModelType.CONN)) onConnAction();
        else if (modelType.equals(ModelType.MISC)) onMiscAction();
        else onFreeAction(selectedWindow);
    }

    private void onFreeAction(PaintWindow selectedWindow) {
        for (Drawable drawable : this.standardItemBar.itemBar) {
            drawable.setOnMouseClicked(event -> {
                selectedWindow.removeSelectedNodes();

                ModelNode modelNode = (ModelNode) NodesHelper.getDuplicate(workplace, drawable);
                selectedWindow.addNodeToCanvas(modelNode);
                event.consume();
            });
        }
    }

    private void onCTTAction(PaintWindow selectedWindow) {
        for (Drawable drawable : this.cttItemBar.itemBar) {
            drawable.setOnMouseClicked(event -> {
                selectedWindow.removeSelectedNodes();

                ModelNode parent = workplace.getSelectedSymbol();
                ModelNode child = (ModelNode) NodesHelper.getDuplicate(workplace, drawable);
                selectedWindow.addNodeToCanvas(child);
                if (parent == null) return;

                Connection connection = CTTConnection.generateShape(workplace, parent, child);
                selectedWindow.addConnection(connection);
            });
        }
    }

    private void onConnAction() {

    }

    private void onMiscAction() {
        for (Drawable drawable : this.miscItemBar.itemBar) {
            drawable.setOnMouseClicked(event -> {
                Drawable modelNode = NodesHelper.getDuplicate(workplace, drawable);
                if (modelNode instanceof GroupingNode) {
                    workplace.getSelectedPaintWindow().addGenNodeToCanvas((ModelNode) modelNode);
                }
                else if (modelNode instanceof TextSymbol) {
                    workplace.getCommentList().add((WorkplaceNode) modelNode);
                    workplace.getChildren().add(modelNode);
                }
            });
        }
    }

}
