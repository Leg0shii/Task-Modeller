package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.model.windows.NewProjectCountWindow;
import de.legoshi.taskmodeller.gui.model.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.model.windows.Workplace;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    public VBox mainFrame;

    public MenuBar menuBar;

    public ScrollPane contentPane;
    private Workplace workplace;

    public HBox toolBar;

    public RadioButton standardRBTN;
    public RadioButton connectionRBTN;
    public RadioButton miscRBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.workplace = new Workplace(this.toolBar);
        this.contentPane.setContent(new Group(workplace));

        contentPane.addEventFilter(ScrollEvent.ANY, (scrollEvent -> {
            if (!scrollEvent.isShiftDown()) return;

            double zoomFactor = 1.05;
            double deltaX = scrollEvent.getDeltaX();
            if (deltaX == 0) deltaX = scrollEvent.getDeltaY();
            if (deltaX < 0) zoomFactor = 2.0 - zoomFactor;

            this.scaleProjectView(zoomFactor);
            scrollEvent.consume();
        }));
    }

    public void zoomIn() {
        scaleProjectView(1.2);
    }

    public void zoomOut() {
        scaleProjectView(0.8);
    }

    private void scaleProjectView(double scalingFactor) {
        workplace.setScaleX(workplace.getScaleX() * scalingFactor);
        workplace.setScaleY(workplace.getScaleY() * scalingFactor);
    }

    public void createProject() {
        new NewProjectCountWindow(workplace.getProjectWindow()).show();
    }

    @FXML
    private void onRadioButtonChange() {
        PaintWindow selectedWindow = workplace.getSelectedPaintWindow();
        ModelType modelType = ModelType.MISC;
        if (standardRBTN.isSelected()) {
            if (selectedWindow == null) return;
            modelType = selectedWindow.getModelType();
        } else if (connectionRBTN.isSelected()) modelType = ModelType.CONN;
        workplace.getItemBarManager().reloadItemBarWithModel(selectedWindow, modelType);
    }

}
