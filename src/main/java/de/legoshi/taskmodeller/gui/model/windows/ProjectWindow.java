package de.legoshi.taskmodeller.gui.model.windows;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ProjectWindow extends GridPane {

    private int existentCount = 1;
    private int compositeCount = 1;
    private int envisionedCount = 1;

    private ArrayList<PaintWindow> existentWindows;
    private ArrayList<PaintWindow> compositeWindows;
    private ArrayList<PaintWindow> envisionedWindows;

    private PaintWindow selectedPaintWindow;

    public ProjectWindow() {
        this.setHgap(100);
        this.setVgap(100);

        this.existentWindows = new ArrayList<>();
        this.compositeWindows = new ArrayList<>();
        this.envisionedWindows = new ArrayList<>();
    }

    public ArrayList<PaintWindow> getAllWindows() {
        ArrayList<PaintWindow> allWindows = new ArrayList<>();
        allWindows.addAll(existentWindows);
        allWindows.addAll(compositeWindows);
        allWindows.addAll(envisionedWindows);
        return allWindows;
    }

    public void generatePaintWindows() {
        this.minHeight(Math.max(Math.max(existentCount, compositeCount), envisionedCount) * 750);
        this.maxHeight(750*3);

        initPaintWindows(existentWindows, existentCount, 0);
        initPaintWindows(compositeWindows, compositeCount, 1);
        initPaintWindows(envisionedWindows, envisionedCount, 2);
    }

    private void initPaintWindows(ArrayList<PaintWindow> windows, int count, int xShift) {
        for (int i = 0; i < count; i++) {
            PaintWindow paintWindow = new PaintWindow(MainController.getMainController(), ModelType.FREE);
            windows.add(paintWindow);
            this.add(paintWindow, xShift, i);
        }
    }


}
