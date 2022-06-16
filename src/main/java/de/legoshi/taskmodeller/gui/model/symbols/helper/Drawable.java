package de.legoshi.taskmodeller.gui.model.symbols.helper;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.Stack;
import java.util.UUID;

public abstract class Drawable implements IShape<StackPane> {

    private final UUID id;

    @Getter
    private final StackPane stackPane;
    @Getter
    private final Polygon shape;


    private double lastX = 0;
    private double lastY = 0;
    private static final int marginLeftX = 5; //10;
    private static final int marginTopY = 5; //10;
    private static final int marginRightX = 55; //110;
    private static final int marginBottomY = 55; //240;

    public Drawable(UUID randomUUID, Polygon shape) {
        this.id = randomUUID;
        this.shape = shape;
        this.stackPane = new StackPane();
        this.stackPane.getChildren().add(shape);
    }

    private void onMouseClick(MouseEvent event, StackPane stPane) {
        if (event.isSecondaryButtonDown()) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Bearbeite Objekt");

            Polygon polygon = (Polygon) stPane.getChildren().get(0);
            Label label = (Label) stPane.getChildren().get(1);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10, 10, 10, 10));
            gridPane.setHgap(5);
            gridPane.setVgap(5);

            TextField textField = new TextField();
            gridPane.add(new Text("Name: "), 0, 0);
            gridPane.add(textField, 1, 0);

            textField.textProperty().addListener((observableValue, s, t1) -> {
                if (!(label.getBoundsInLocal().getWidth() < stPane.getBoundsInLocal().getWidth())) {
                    label.setText(t1);
                    return;
                }
                label.setText(t1);
            });

            Slider xSlider = new Slider(0.01, 5, stPane.getScaleX());
            gridPane.add(new Text("Scale:"), 0, 1);
            gridPane.add(xSlider, 1, 1);
            xSlider.valueProperty().addListener((observableValue, number, t1) -> {
                stPane.setScaleX(stPane.getScaleX() * 1 + ((double) t1 - (double) number));
                stPane.setScaleY(stPane.getScaleY() * 1 + ((double) t1 - (double) number));

                label.setScaleX(1/stPane.getScaleX());
                label.setScaleY(1/stPane.getScaleY());
            });

            ColorPicker colorPicker = new ColorPicker((Color) polygon.getFill());
            gridPane.add(colorPicker, 2, 0, 3, 3);
            colorPicker.valueProperty().addListener((observableValue, color, t1) -> polygon.setFill(t1));

            Button closeBtn = new Button("Done");
            gridPane.add(closeBtn, 1, 10, 10, 1);
            closeBtn.setOnMouseClicked(mouseEvent -> dialog.close());

            Scene dialogScene = new Scene(gridPane, 400, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        } else {
            this.lastX = event.getSceneX();
            this.lastY = event.getSceneY();
            event.consume();
        }
    }

    private void onMouseDrag(MouseEvent event, StackPane stPane) {
        double polyTranslateX = stPane.getTranslateX();
        double objectScaleFactorWidth = stPane.getScaleX() * stPane.getLayoutBounds().getWidth() - stPane.getLayoutBounds().getWidth();
        double sceneWidth = stPane.getParent().getLayoutBounds().getWidth();

        if (polyTranslateX < (marginLeftX + objectScaleFactorWidth / 2))
            stPane.setTranslateX(marginLeftX + objectScaleFactorWidth / 2);
        else if (polyTranslateX > (sceneWidth - marginRightX - (objectScaleFactorWidth / 2)))
            stPane.setTranslateX((sceneWidth - marginRightX - (objectScaleFactorWidth / 2)));
        else {
            stPane.setTranslateX(polyTranslateX + event.getSceneX() - lastX);
            this.lastX = event.getSceneX();
        }

        double polyTranslateY = stPane.getTranslateY();
        double objectScaleFactorHeight = stPane.getScaleY() * stPane.getLayoutBounds().getHeight() - stPane.getLayoutBounds().getHeight();
        double sceneHeight = stPane.getParent().getLayoutBounds().getHeight();

        if (polyTranslateY < (marginTopY + objectScaleFactorHeight / 2))
            stPane.setTranslateY(marginTopY + objectScaleFactorHeight / 2);
        else if (polyTranslateY > (sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)))
            stPane.setTranslateY((sceneHeight - marginBottomY - (objectScaleFactorHeight / 2)));
        else {
            stPane.setTranslateY(polyTranslateY + event.getSceneY() - lastY);
            this.lastY = event.getSceneY();
        }
    }

    public StackPane getDuplicate() {
        StackPane stPane = new StackPane();
        Polygon newPoly = new Polygon();

        newPoly.getPoints().addAll(shape.getPoints());
        newPoly.setFill(shape.getFill());
        newPoly.setStroke(shape.getStroke());
        newPoly.setStrokeWidth(shape.getStrokeWidth());

        Label label = new Label(UUID.randomUUID().toString().substring(0,3));

        stPane.getChildren().add(newPoly);
        stPane.getChildren().add(label);

        newPoly.setOnMousePressed(event -> onMouseClick(event, stPane));
        newPoly.setOnMouseDragged(event -> onMouseDrag(event, stPane));
        return stPane;
    }

    private void textResize(Polygon polygon, Text text) {
        if (text.getLayoutX() > polygon.getLayoutX()) {
            text.resize(polygon.getLayoutX(), polygon.getLayoutY());
        }
    }

}
