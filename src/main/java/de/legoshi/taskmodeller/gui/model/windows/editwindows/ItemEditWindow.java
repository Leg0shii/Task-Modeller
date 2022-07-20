package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemEditWindow extends EditWindow<DrawnSymbol> {

    public ItemEditWindow(DrawnSymbol item) {
        super(item, "Bearbeite Objekt");

        Rectangle shape = (Rectangle) item.getChildren().get(0);
        Label label = (Label) item.getChildren().get(1);

        TextField textField = new TextField(label.getText());
        this.gridPane.add(new Text("Name: "), 0, 0);
        this.gridPane.add(textField, 1, 0);
        textField.textProperty().addListener((observableValue, s, t1) -> label.setText(t1));

        TextArea textArea = new TextArea(item.getDescription());
        this.gridPane.add(new Text("Description: "), 0, 1);
        this.gridPane.add(textArea, 1, 1);
        textArea.textProperty().addListener((observableValue, s, t1) -> item.setDescription(t1));


        Slider slider = new Slider(0.4, 5, item.getScaleX());
        this.gridPane.add(new Text("Skalieren:"), 0, 2);
        this.gridPane.add(slider, 1, 2);
        slider.valueProperty().addListener((observableValue, number, t1) -> onScale(item, number.doubleValue(), t1.doubleValue()));

        Button connectBtn = new Button("Verbindung");
        this.gridPane.add(connectBtn, 0, 3);
        connectBtn.setOnMouseClicked(mouseEvent -> onConnect());

        ColorPicker colorPicker = new ColorPicker((Color) shape.getFill());
        this.gridPane.add(colorPicker, 1, 3);
        colorPicker.valueProperty().addListener((observableValue, color, t1) -> shape.setFill(t1));

        this.deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());
    }

    private void onConnect() {
        MainController mainController = MainController.getInstance();
        for (DrawnSymbol dS : mainController.getProject().getSelectedPaintWindow().getDrawnNodes()) {
            dS.setAttemptsConnect(false);
        }
        this.item.setAttemptsConnect(true);
        this.close();
    }

    private void onScale(DrawnSymbol drawnSymbol, double number, double t1) {
        Rectangle polygon = (Rectangle) drawnSymbol.getChildren().get(0);
        Label label = (Label) drawnSymbol.getChildren().get(1);

        drawnSymbol.setScaleX(drawnSymbol.getScaleX() + (t1 - number));
        drawnSymbol.setScaleY(drawnSymbol.getScaleY() + (t1 - number));
        label.setFont(new Font("Arial", 12/drawnSymbol.getScaleX()));

        polygon.setStrokeWidth(3/drawnSymbol.getScaleX());
    }

    private void onDelete() {
        MainController mainController = MainController.getInstance();
        mainController.getProject().getChildren().remove(this.item);
        this.close();
    }

}
