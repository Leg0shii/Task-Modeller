package de.legoshi.taskmodeller.manager;

import de.legoshi.taskmodeller.gui.model.itembar.*;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.ModelNode;
import de.legoshi.taskmodeller.gui.model.symbols.ModelConnectionNode;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.model.windows.Workplace;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class ItemBarManager {

    private final StandardItemBar standardItemBar = new StandardItemBar();
    private final CTTItemBar cttItemBar = new CTTItemBar();
    private final ConnectionItemBar connectionItemBar = new ConnectionItemBar();
    private final MiscItemBar miscItemBar = new MiscItemBar();

    private final Workplace workplace;
    private final HBox toolBar;

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
        else if (modelType.equals(ModelType.CONN)) onConnAction(selectedWindow);
        else if (modelType.equals(ModelType.MISC)) onMiscAction();
        else onFreeAction(selectedWindow);
    }

    private void onFreeAction(PaintWindow selectedWindow) {
        for (Drawable drawable : this.standardItemBar.itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                ModelNode modelNode = drawable.getDuplicate(workplace, false);
                selectedWindow.addNodeToCanvas(modelNode);
                event.consume();
            });
        }
    }

    private void onCTTAction(PaintWindow selectedWindow) {
        for (Drawable drawable : this.cttItemBar.itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                ModelNode parent = workplace.getSelectedSymbol();
                ModelNode child = drawable.getDuplicate(workplace, false);
                if (parent == null) {
                    selectedWindow.addNodeToCanvas(child);
                    return;
                }
                selectedWindow.addNodeToCanvas(child);
                ModelConnectionNode modelConnectionNode = new ModelConnectionNode(workplace, parent, child);
                selectedWindow.addConnection(modelConnectionNode);
            });
        }
    }

    private void onConnAction(PaintWindow selectedWindow) {

    }

    private void onMiscAction() {
        for (Drawable drawable : this.miscItemBar.itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                ModelNode modelNode = drawable.getDuplicate(workplace, true);
                workplace.getChildren().add(modelNode);
            });
        }
    }

}
