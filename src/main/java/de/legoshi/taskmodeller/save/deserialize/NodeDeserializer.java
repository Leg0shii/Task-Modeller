package de.legoshi.taskmodeller.save.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CCircle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CPentagon;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CTriangle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.Square;
import de.legoshi.taskmodeller.util.NodesHelper;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class NodeDeserializer extends JsonDeserializer<ModelNode> {

    @Override
    public ModelNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String id = node.get("id").asText();
        String className = node.get("className").asText();
        String color = node.get("color").asText();
        double scaleX = (Double) node.get("scaleX").numberValue();
        double scaleY = (Double) node.get("scaleY").numberValue();
        String name = node.get("name").asText();
        String description = node.get("description").asText();
        String strokeColor = node.get("strokeColor").asText();
        double posX = (Double) node.get("posX").numberValue();
        double posY = (Double) node.get("posY").numberValue();

        ModelNode modelNode;

        switch (className) {
            case "CCircle" -> modelNode = CCircle.generateShape();
            case "CPentagon" -> modelNode = CPentagon.generateShape();
            case "CTriangle" -> modelNode = CTriangle.generateShape();
            case "Square" -> modelNode = Square.generateShape();
            case "AbstractTask" -> modelNode = AbstractTask.generateShape();
            case "SimpleTask" -> modelNode = SimpleTask.generateShape();
            case "GroupingNode" -> modelNode = GroupingNode.generateShape();
            default -> {
                return null;
            }
        }

        modelNode.setId(id);
        modelNode.getPolyShape().setFill(Color.valueOf(color));
        modelNode.setScaleX(scaleX);
        modelNode.setScaleY(scaleY);
        if (modelNode instanceof GroupingNode) {
            modelNode.setBorder(new Border(
                    new BorderStroke(Color.valueOf(strokeColor), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            new BorderWidths(3/modelNode.getScaleY(), 3/modelNode.getScaleX(), 3/modelNode.getScaleY(), 3/modelNode.getScaleX())
                    )
            ));
        } else {
            modelNode.getPolyShape().setStrokeWidth(3/modelNode.getScaleX());
        }
        modelNode.getPolyShape().setStrokeWidth(3 / scaleX);
        modelNode.getLabel().setText(name);
        modelNode.getLabel().setFont(new Font("Arial", 12/ modelNode.getScaleX()));
        modelNode.setDescription(description);
        modelNode.setTranslateX(posX);
        modelNode.setTranslateY(posY);

        return modelNode;
    }

}
