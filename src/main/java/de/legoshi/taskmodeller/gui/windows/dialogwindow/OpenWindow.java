package de.legoshi.taskmodeller.gui.windows.dialogwindow;

import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.stage.FileChooser;

import java.io.File;

public class OpenWindow extends DialogWindow {

    public OpenWindow(Workplace workplace) {
        this.setTitle("Open Window");
        File file = new FileChooser().showOpenDialog(this);
        if (file == null) return;
        workplace.reloadWorkspaceFromFile(file);
        this.close();
    }

}
