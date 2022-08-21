package de.legoshi.taskmodeller.save.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.save.simple_object.SimpleConnection;
import de.legoshi.taskmodeller.save.simple_object.SimplePaintWindow;
import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.StatusType;

import java.io.IOException;
import java.util.ArrayList;

public class PaintWindowDeserializer extends JsonDeserializer<SimplePaintWindow> {

    @Override
    public SimplePaintWindow deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode modelNode = node.get("modelNodes");
        JsonNode connectionNode = node.get("modelConnections");

        ObjectMapper modelNodeMapper = getObjectMapper(ModelNode.class, new NodeDeserializer());
        ObjectMapper connectionNodeMapper = getObjectMapper(SimpleConnection.class, new ConnectionDeserializer());

        SimplePaintWindow simplePaintWindow = new SimplePaintWindow();
        simplePaintWindow.setModelType(ModelType.valueOf(node.get("modelType").asText()));
        simplePaintWindow.setName(node.get("name").asText());
        simplePaintWindow.setStatusType(StatusType.valueOf(node.get("statusType").asText()));

        ArrayList<ModelNode> modelNodes = modelNodeMapper.readValue(modelNode.asText(), new TypeReference<>() {});
        modelNodes.removeIf(mN -> mN instanceof GroupingNode);
        simplePaintWindow.setModelNodes(modelNodes);

        ArrayList<SimpleConnection> simpleConnections = connectionNodeMapper.readValue(connectionNode.asText(), new TypeReference<>() {});
        simplePaintWindow.setConnections(simpleConnections);

        return simplePaintWindow;
    }

    private ObjectMapper getObjectMapper(Class c, JsonDeserializer deserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(c, deserializer);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
