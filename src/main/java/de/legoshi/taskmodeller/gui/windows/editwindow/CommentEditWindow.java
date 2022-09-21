package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class CommentEditWindow extends EditWindow<WorkplaceNode> {

    private final Workplace workplace;

    public CommentEditWindow(Workplace workplace, WorkplaceNode item) {
        super(item, "Edit Comment");
        this.workplace = workplace;

        Label label = item.getLabel();

        TextArea textArea = new TextArea(label.getText());
        this.gridPane.add(new Text("Comment: "), 0, 0);
        this.gridPane.add(textArea, 1, 0);
        textArea.textProperty().addListener((observableValue, s, t1) -> label.setText(t1));
    }

    @Override
    public void onDelete() {
        workplace.getCommentList().remove(this.item);
        workplace.getChildren().remove(this.item);
        this.close();
    }

}
