package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class GroupingEditWindow extends EditWindow<ModelNode> {

    private final Workplace workplace;

    public GroupingEditWindow(Workplace workplace, ModelNode item) {
        super(item, "Edit generalize");
        this.workplace = workplace;

        Slider sliderX = new Slider(0.4, 15, item.getScaleX());
        this.gridPane.add(new Text("Scale-X:"), 0, 0);
        this.gridPane.add(sliderX, 1, 0);
        sliderX.valueProperty().addListener((observableValue, number, t1) -> onScaleX(item, number.doubleValue(), t1.doubleValue()));

        Slider sliderY = new Slider(0.4, 15, item.getScaleY());
        this.gridPane.add(new Text("Scale-Y:"), 0, 1);
        this.gridPane.add(sliderY, 1, 1);
        sliderY.valueProperty().addListener((observableValue, number, t1) -> onScaleY(item, number.doubleValue(), t1.doubleValue()));

        Button connectBtn = new Button("Verbindung");
        this.gridPane.add(connectBtn, 0, 3);
        connectBtn.setOnMouseClicked(mouseEvent -> onConnect());
    }

    private void onConnect() {
        for (PaintWindow pW : workplace.getAllWindows()) {
            for (ModelNode dS : pW.getDrawnNodes()) {
                dS.setAttemptsConnect(false);
            }
        }
        this.item.setAttemptsConnect(true);
        this.close();
    }

    private void onScaleX(ModelNode item, double doubleValue, double doubleValue1) {
        Shape shape = item.getPolyShape();
        item.setScaleX(item.getScaleX() + (doubleValue1 - doubleValue));
        shape.setStrokeWidth(6/(item.getScaleX()+item.getScaleY()));
    }

    private void onScaleY(ModelNode item, double doubleValue, double doubleValue1) {
        Shape shape = item.getPolyShape();
        item.setScaleY(item.getScaleY() + (doubleValue1 - doubleValue));
        shape.setStrokeWidth(6/(item.getScaleX()+item.getScaleY()));
    }

    @Override
    public void onDelete() {
        for (PaintWindow paintWindow : workplace.getAllWindows()) {
            paintWindow.removeGenNodeFromCanvas(this.item);
        }
        this.close();
    }

}
