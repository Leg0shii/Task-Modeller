package de.legoshi.taskmodeller.save.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import de.legoshi.taskmodeller.save.simple_object.SimpleConnection;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ConnectionDeserializer extends JsonDeserializer<SimpleConnection> {
    @Override
    public SimpleConnection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String id = node.get("id").asText();
        String node1ID = node.get("node1ID").asText();
        String node2ID = node.get("node2ID").asText();
        String className = node.get("className").asText();
        String color = node.get("color").asText();
        String name = node.get("name").asText();
        double strokeWidth = node.get("strokeWidth").asDouble();

        SimpleConnection simpleConnection = new SimpleConnection();
        simpleConnection.setId(id);
        simpleConnection.setNode1ID(node1ID);
        simpleConnection.setNode2ID(node2ID);
        simpleConnection.setClassName(className);
        simpleConnection.setColor(Color.valueOf(color));
        simpleConnection.setName(name);
        simpleConnection.setStrokeWidth(strokeWidth);

        return simpleConnection;
    }
}
