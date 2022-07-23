package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ProjectWindow extends GridPane {

    private final Workplace workplace;

    private int existentCount = 1;
    private int compositeCount = 1;
    private int envisionedCount = 1;

    private ArrayList<PaintWindow> existentWindows;
    private ArrayList<PaintWindow> compositeWindows;
    private ArrayList<PaintWindow> envisionedWindows;

    public ProjectWindow(Workplace workplace) {
        this.workplace = workplace;
        this.setHgap(100);
        this.setVgap(100);

        existentWindows = new ArrayList<>();
        compositeWindows = new ArrayList<>();
        envisionedWindows = new ArrayList<>();

    }

    public ArrayList<PaintWindow> getAllWindows() {
        ArrayList<PaintWindow> allWindows = new ArrayList<>();
        allWindows.addAll(existentWindows);
        allWindows.addAll(compositeWindows);
        allWindows.addAll(envisionedWindows);
        return allWindows;
    }

    public void generatePaintWindows(ArrayList<HBox> exHBox, ArrayList<HBox> coHBox, ArrayList<HBox> evHBox) {
        this.minHeight(Math.max(Math.max(existentCount, compositeCount), envisionedCount) * 750);
        this.maxHeight(750*3);

        initPaintWindows(existentWindows, exHBox, 0);
        initPaintWindows(compositeWindows, coHBox, 1);
        initPaintWindows(envisionedWindows, evHBox, 2);
    }

    private void initPaintWindows(ArrayList<PaintWindow> windows, ArrayList<HBox> hBoxes, int xShift) {
        int i = 0;
        for (HBox hBox : hBoxes) {
            TextField tf = (TextField) hBox.getChildren().get(0);
            ComboBox<String> cB = (ComboBox<String>) hBox.getChildren().get(1);

            StatusType statusType = StatusType.values()[xShift];
            ModelType modelType = ModelType.valueOf(cB.getValue());

            PaintWindow paintWindow = new PaintWindow(this.workplace, statusType, modelType);
            paintWindow.setName(tf.getText());
            paintWindow.getChildren().add(new Label(tf.getText() + " (" + modelType.name() + ", " + statusType.name() + ")"));
            windows.add(paintWindow);
            this.add(paintWindow, xShift, i);
            i++;
        }
    }

}
