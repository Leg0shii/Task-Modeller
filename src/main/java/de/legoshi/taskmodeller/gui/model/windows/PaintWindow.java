package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.gui.model.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.NodeConnection;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PaintWindow extends ScrollPane {

    private final MainController mainController;
    public DrawArea drawArea;

    private final ModelType modelType;

    private ArrayList<NodeConnection> connections = new ArrayList<>();

    public PaintWindow(MainController mainController, ModelType modelType) {
        this.mainController = mainController;
        this.modelType = modelType;
        initialize();
    }

    private void initialize() {
        this.drawArea = new DrawArea();
        this.getStyleClass().add("draw-pane");
        this.setPadding(new Insets(15, 15, 15, 15));
        this.setContent(drawArea);

        this.setOnMouseClicked(mouseEvent -> {
            mainController.setSelectedPaintWindow(this);
            for(PaintWindow paintWindow : mainController.getAllWindows()) {
                paintWindow.getStyleClass().remove("active-pane");
                mainController.reloadItemBarWithModel(this.modelType);
            }
            this.getStyleClass().add("active-pane");
        });
    }
}
