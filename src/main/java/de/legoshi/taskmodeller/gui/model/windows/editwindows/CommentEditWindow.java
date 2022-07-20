package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import de.legoshi.taskmodeller.MainController;
import de.legoshi.taskmodeller.gui.model.symbols.DrawnSymbol;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class CommentEditWindow extends EditWindow<DrawnSymbol> {

    public CommentEditWindow(DrawnSymbol item) {
        super(item, "Bearbeite Kommentar");

        Label label = (Label) item.getChildren().get(1);

        TextArea textArea = new TextArea(label.getText());
        this.gridPane.add(new Text("Kommentar: "), 0, 0);
        this.gridPane.add(textArea, 1, 0);
        textArea.textProperty().addListener((observableValue, s, t1) -> {
            label.setText(t1);
        });

        this.deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());
    }

    private void onDelete() {
        MainController mainController = MainController.getInstance();
         mainController.getCommentGroup().getChildren().remove(this.item);
        this.close();
    }

}
