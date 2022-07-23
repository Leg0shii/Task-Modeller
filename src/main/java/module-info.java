module de.legoshi.taskmodeller {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;

    opens de.legoshi.taskmodeller to javafx.fxml;
    exports de.legoshi.taskmodeller.util;
    opens de.legoshi.taskmodeller.util to javafx.fxml;
    exports de.legoshi.taskmodeller.gui.windows;
    exports de.legoshi.taskmodeller.gui.symbol;
    exports de.legoshi.taskmodeller;
    exports de.legoshi.taskmodeller.gui.itembar;
    exports de.legoshi.taskmodeller.gui.windows.editwindow;
    exports de.legoshi.taskmodeller.gui.symbol.connection;
    exports de.legoshi.taskmodeller.gui.windows.newwindow;
}