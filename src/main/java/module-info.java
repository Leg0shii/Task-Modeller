module de.legoshi.taskmodeller {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;

    opens de.legoshi.taskmodeller to javafx.fxml;
    exports de.legoshi.taskmodeller.gui.start;
    opens de.legoshi.taskmodeller.gui.start to javafx.fxml;
    exports de.legoshi.taskmodeller.util;
    opens de.legoshi.taskmodeller.util to javafx.fxml;
    exports de.legoshi.taskmodeller.gui.model;
}