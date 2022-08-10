package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    public static final int HGAP = 100;
    public static final int VGAP = 100;
    public static final int SIZE = 750;

    private ArrayList<PaintWindow> existentWindows;
    private ArrayList<PaintWindow> compositeWindows;
    private ArrayList<PaintWindow> envisionedWindows;

    public ProjectWindow(Workplace workplace) {
        this.workplace = workplace;
        this.setHgap(HGAP);
        this.setVgap(VGAP);

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
        this.minHeight(Math.max(Math.max(existentCount, compositeCount), envisionedCount) * SIZE);
        this.maxHeight(SIZE*3);

        initPaintWindows(existentWindows, Color.color(227.0/255.0, 251.0/255.0, 227.0/255.0), exHBox, 0);
        initPaintWindows(compositeWindows, Color.color(1.0, 250.0/255.0, 205.0/255.0), coHBox, 1);
        initPaintWindows(envisionedWindows, Color.color(1.0, 220.0/255.0, 223.0/255.0), evHBox, 2);
    }

    private void initPaintWindows(ArrayList<PaintWindow> windows, Color color, ArrayList<HBox> hBoxes, int xShift) {
        int i = 0;
        for (HBox hBox : hBoxes) {
            TextField tf = (TextField) hBox.getChildren().get(0);
            ComboBox<String> cB = (ComboBox<String>) hBox.getChildren().get(1);

            StatusType statusType = StatusType.values()[xShift];
            ModelType modelType = ModelType.valueOf(cB.getValue());

            PaintWindow paintWindow = new PaintWindow(this.workplace, xShift, i, statusType, modelType);
            paintWindow.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
            paintWindow.setName(tf.getText());
            paintWindow.setTranslateX(100);
            paintWindow.setTranslateY(100);
            Label label = new Label(tf.getText() + " (" + modelType.name() + ", " + statusType.name() + ")");
            label.setPadding(new Insets(10, 10, 10, 10));
            paintWindow.getChildren().add(label);
            windows.add(paintWindow);
            this.add(paintWindow, xShift, i);
            i++;
        }
    }

}
