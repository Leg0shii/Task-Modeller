package de.legoshi.taskmodeller.gui.model;

import de.legoshi.taskmodeller.gui.model.symbols.helper.Drawable;
import de.legoshi.taskmodeller.util.ModelType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.UUID;

public class MainWindow {

    @FXML public Label selectLID;
    private UUID id;
    private ArrayList<Drawable> drawableArrayList;

    public ModelType modelType;


}
