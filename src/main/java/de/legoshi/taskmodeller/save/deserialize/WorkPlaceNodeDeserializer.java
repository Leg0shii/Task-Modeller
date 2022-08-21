package de.legoshi.taskmodeller.save.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import javafx.scene.paint.Color;

import java.io.IOException;

public class WorkPlaceNodeDeserializer extends JsonDeserializer<WorkplaceNode> {

    @Override
    public WorkplaceNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String id = node.get("id").asText();
        String className = node.get("className").asText();
        String color = node.get("color").asText();
        String name = node.get("name").asText();
        double posX = (Double) node.get("posX").numberValue();
        double posY = (Double) node.get("posY").numberValue();

        WorkplaceNode workplaceNode;

        if ("TextSymbol".equals(className)) workplaceNode = TextSymbol.generateShape();
        else return null;

        workplaceNode.setId(id);
        workplaceNode.getPolyShape().setFill(Color.valueOf(color));
        workplaceNode.getLabel().setText(name);
        workplaceNode.setTranslateX(posX);
        workplaceNode.setTranslateY(posY);

        return workplaceNode;
    }
}
