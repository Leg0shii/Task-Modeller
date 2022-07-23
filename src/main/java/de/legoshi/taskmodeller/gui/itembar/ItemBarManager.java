package de.legoshi.taskmodeller.gui.itembar;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.ModelConnectionNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.NormalConnection;
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
        this.standardItemBar = new StandardItemBar(workplace);
        this.cttItemBar = new CTTItemBar(workplace);
        this.connectionItemBar = new ConnectionItemBar();
        this.miscItemBar = new MiscItemBar(workplace);
        this.workplace = workplace;
        this.toolBar = toolBar;
    }

    public void reloadItemBarWithModel(PaintWindow selectedWindow, ModelType modelType) {
        ArrayList<Drawable> itemBar = this.getModelItemBar(modelType);
        this.reloadItemBar(selectedWindow, modelType);
        toolBar.getChildren().clear();

        for (Drawable drawable : itemBar) {
            toolBar.getChildren().add(drawable.getPolyShape());
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
            drawable.getPolyShape().setOnMouseClicked(event -> {
                ModelNode modelNode = (ModelNode) NodesHelper.getDuplicate(workplace, drawable);
                selectedWindow.addNodeToCanvas(modelNode);
                event.consume();
            });
        }
    }

    private void onCTTAction(PaintWindow selectedWindow) {
        for (Drawable drawable : this.cttItemBar.itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                ModelNode parent = workplace.getSelectedSymbol();
                ModelNode child = (ModelNode) NodesHelper.getDuplicate(workplace, drawable);
                selectedWindow.addNodeToCanvas(child);
                if (parent == null) return;

                ModelConnectionNode modelConnectionNode = NormalConnection.generateShape(workplace, parent, child);
                selectedWindow.addConnection(modelConnectionNode);
            });
        }
    }

    private void onConnAction() {

    }

    private void onMiscAction() {
        for (Drawable drawable : this.miscItemBar.itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                WorkplaceNode modelNode = (WorkplaceNode) NodesHelper.getDuplicate(workplace, drawable);
                workplace.getChildren().add(modelNode);
            });
        }
    }

}
