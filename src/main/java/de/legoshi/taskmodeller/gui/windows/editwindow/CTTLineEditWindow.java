package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.connection.CTTConnection;
import de.legoshi.taskmodeller.gui.symbol.connection.CTTOperation;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CTTLineEditWindow extends EditWindow<Connection> {

    private final Workplace workplace;

    public CTTLineEditWindow(Workplace workplace, Connection item) {
        super(item, "Edit CTT-Line");

        Label selectName = new Label("Connection Type");
        gridPane.add(selectName, 0, 0);

        ComboBox<CTTOperation> comboBox = new ComboBox<>();
        comboBox.setValue(CTTOperation.StringToOperation(item.getLabel().getText()));
        comboBox.getItems().addAll(CTTOperation.values());
        comboBox.valueProperty().addListener((observableValue, cttOperation, t1) -> item.getLabel().setText(t1.OperationToString()));
        gridPane.add(comboBox, 1,0);

        Label colorName = new Label("Change Color");
        gridPane.add(colorName, 0, 1);

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue((Color) item.getStroke());
        gridPane.add(colorPicker, 1, 1);
        colorPicker.valueProperty().addListener((observableValue, color, t1) -> item.setStroke(t1));

        Label widthName = new Label("Change Line Width");
        gridPane.add(widthName, 0, 2);

        TextField numberField = new TextField(item.getStrokeWidth() + "");
        numberField.textProperty().addListener((observableValue, s, t1) -> {
            try {
                double sWidth = Double.parseDouble(numberField.getText());
                item.setStrokeWidth(sWidth);
            } catch (NumberFormatException e) {
                item.setStrokeWidth(3.0);
            }
        });
        gridPane.add(numberField, 1, 2);

        this.workplace = workplace;
    }

    @Override
    public void onDelete() {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        paintWindow.removeConnection(this.item);
        this.close();
    }
}
