package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.Slider;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class GeneralisedEditWindow extends EditWindow<WorkplaceNode> {

    private final Workplace workplace;

    public GeneralisedEditWindow(Workplace workplace, WorkplaceNode item) {
        super(item, "Edit generalize");
        this.workplace = workplace;

        Slider sliderX = new Slider(0.4, 5, item.getScaleX());
        this.gridPane.add(new Text("Scale-X:"), 0, 0);
        this.gridPane.add(sliderX, 1, 0);
        sliderX.valueProperty().addListener((observableValue, number, t1) -> onScaleX(item, number.doubleValue(), t1.doubleValue()));

        Slider sliderY = new Slider(0.4, 5, item.getScaleX());
        this.gridPane.add(new Text("Scale-Y:"), 0, 1);
        this.gridPane.add(sliderY, 1, 1);
        sliderY.valueProperty().addListener((observableValue, number, t1) -> onScaleY(item, number.doubleValue(), t1.doubleValue()));
    }

    private void onScaleX(WorkplaceNode item, double doubleValue, double doubleValue1) {
        Shape shape = item.getPolyShape();
        item.setScaleX(item.getScaleX() + (doubleValue1 - doubleValue));
        shape.setStrokeWidth(6/(item.getScaleX()+item.getScaleY()));
    }

    private void onScaleY(WorkplaceNode item, double doubleValue, double doubleValue1) {
        Shape shape = item.getPolyShape();
        item.setScaleY(item.getScaleY() + (doubleValue1 - doubleValue));
        shape.setStrokeWidth(6/(item.getScaleX()+item.getScaleY()));
    }

    @Override
    public void onDelete() {
        workplace.getChildren().remove(this.item);
        this.close();
    }

}
