package de.legoshi.taskmodeller;

import de.legoshi.taskmodeller.gui.model.itembar.CTTItemBar;
import de.legoshi.taskmodeller.gui.model.itembar.StandardItemBar;
import de.legoshi.taskmodeller.gui.model.symbols.Drawable;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import de.legoshi.taskmodeller.gui.model.windows.ProjectWindow;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.spreadsheet.Grid;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @Getter public static MainController mainController;

    @FXML public VBox mainFrame;

    @FXML public MenuBar menuBar;
    @FXML public ScrollPane contentPane;
    @FXML public HBox toolBar;

    public ProjectWindow project;

    private StandardItemBar standardItemBar;
    private CTTItemBar cttItemBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        this.standardItemBar = new StandardItemBar();
        this.cttItemBar = new CTTItemBar();

        this.contentPane.setHmax(500);
        this.contentPane.setVmax(500);

        this.project = new ProjectWindow();
        this.contentPane.setContent(project);
    }

    public void reloadItemBarWithModel(ModelType modelType) {
        ArrayList<Drawable> itemBar;
        if (modelType.equals(ModelType.CTT)) itemBar = cttItemBar.itemBar;
        else itemBar = standardItemBar.itemBar;

        toolBar.getChildren().clear();
        for (Drawable drawable : itemBar) {
            drawable.getPolyShape().setOnMouseClicked(event -> {
                DrawnSymbol drawnSymbol = drawable.getDuplicate();
                project.getSelectedPaintWindow().addNode(drawnSymbol);
                event.consume();
            });
            toolBar.getChildren().add(drawable.getPolyShape());
        }
    }

    public void zoomIn() {
        scaleProjectView(1.2);
    }

    public void zoomOut() {
        scaleProjectView(0.8);
    }

    private void scaleProjectView(double scalingFactor) {
        GridPane drawPane = this.project;
        drawPane.setScaleX(drawPane.getScaleX()*scalingFactor);
        drawPane.setScaleY(drawPane.getScaleY()*scalingFactor);
        /* drawPane.setPrefHeight(drawPane.getPrefHeight()*scalingFactor);
        drawPane.setPrefWidth(drawPane.getPrefWidth()*scalingFactor);
        for(Node n :  drawPane.getChildren()) {
            n.setTranslateX(n.getTranslateX()*scalingFactor);
            n.setTranslateY(n.getTranslateY()*scalingFactor);
            n.setScaleX(n.getScaleX()*scalingFactor);
            n.setScaleY(n.getScaleY()*scalingFactor);
        } */
    }

    public static MainController getInstance() {
        return mainController;
    }

    public void createProject() {
        Stage dialogStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        Label existingL = new Label("Existing Model Count:");
        TextField existingTF = new TextField();
        gridPane.add(existingL, 0, 0);
        gridPane.add(existingTF, 1, 0);

        Label compositeL = new Label("Composite Model Count:");
        TextField compositeTF = new TextField();
        gridPane.add(compositeL, 0, 1);
        gridPane.add(compositeTF, 1, 1);

        Label envisionedL = new Label("Envisioned Model Count:");
        TextField envisionedTF = new TextField();
        gridPane.add(envisionedL, 0, 2);
        gridPane.add(envisionedTF, 1, 2);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> dialogStage.close());

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            int existentCount;
            int compositeCount;
            int envisionedCount;

            try {
                existentCount = Integer.parseInt(existingTF.getText());
                compositeCount = Integer.parseInt(compositeTF.getText());
                envisionedCount = Integer.parseInt(envisionedTF.getText());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                System.out.println("New Project ERROR");
                dialogStage.close();
                return;
            }

            this.project.setExistentCount(existentCount);
            this.project.setCompositeCount(compositeCount);
            this.project.setEnvisionedCount(envisionedCount);

            setModelTypeWindow();
            dialogStage.close();
        });

        gridPane.add(cancelBtn, 0, 3, 2, 1);
        gridPane.add(continueBtn, 1, 3, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    private void setModelTypeWindow() {
        Stage dialogStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        Label existingModelL = new Label("Existing Model");
        Label compositeModelL = new Label("Composite Model");
        Label envisionedModelL = new Label("Envisioned Model");

        VBox exModel = new VBox();
        exModel.getChildren().add(existingModelL);
        exModel.getChildren().addAll(generateDisplayThing(project.getExistentCount()));

        VBox coModel = new VBox();
        coModel.getChildren().add(compositeModelL);
        coModel.getChildren().addAll(generateDisplayThing(project.getCompositeCount()));

        VBox evModel = new VBox();
        evModel.getChildren().add(envisionedModelL);
        evModel.getChildren().addAll(generateDisplayThing(project.getEnvisionedCount()));

        gridPane.add(exModel, 0, 0);
        gridPane.add(coModel, 1, 0);
        gridPane.add(evModel, 2, 0);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> dialogStage.close());

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            generateSave();
            dialogStage.close();
        });

        gridPane.add(cancelBtn, 0, 3);
        gridPane.add(continueBtn, 1, 3);

        Scene scene = new Scene(gridPane, 700, 300);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    private void generateSave() {
        Stage dialogStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        TextField name = new TextField();
        TextField location = new TextField();

        gridPane.add(name, 0, 0);
        gridPane.add(location, 0, 1);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(mouseEvent -> dialogStage.close());

        Button continueBtn = new Button("Finish");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            project.generatePaintWindows();
            // create save file
            dialogStage.close();
        });

        gridPane.add(cancelBtn, 0, 2);
        gridPane.add(continueBtn, 1, 2);

        Scene scene = new Scene(gridPane, 700, 300);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    private ArrayList<HBox> generateDisplayThing(int count) {
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            HBox hBox = new HBox();
            TextField textField = new TextField("Task Model " + (i+1));
            ComboBox<String> comboBox = new ComboBox<>();
            ObservableList<String> options = FXCollections.observableArrayList("Free", "CTT");
            comboBox.setItems(options);

            hBox.getChildren().add(textField);
            hBox.getChildren().add(comboBox);
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

}
