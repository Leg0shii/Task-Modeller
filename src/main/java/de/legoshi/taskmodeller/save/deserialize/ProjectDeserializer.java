package de.legoshi.taskmodeller.save.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.save.simple_object.SimpleConnection;
import de.legoshi.taskmodeller.save.simple_object.SimplePaintWindow;
import de.legoshi.taskmodeller.save.simple_object.SimpleWorkplace;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectDeserializer extends JsonDeserializer<SimpleWorkplace> {

    @Override
    public SimpleWorkplace deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode groupingNode = node.get("groupingNodes");
        JsonNode workplaceConnectionNode = node.get("workplaceConnections");
        JsonNode commentNode = node.get("commentNodes");
        JsonNode paintWindowNode = node.get("paintWindows");

        ObjectMapper modelNodeMapper = getObjectMapper(ModelNode.class, new NodeDeserializer());
        ObjectMapper connectionNodeMapper = getObjectMapper(SimpleConnection.class, new ConnectionDeserializer());
        ObjectMapper commentNodeMapper = getObjectMapper(WorkplaceNode.class, new WorkPlaceNodeDeserializer());
        ObjectMapper paintWindowMapper = getObjectMapper(SimplePaintWindow.class, new PaintWindowDeserializer());

        SimpleWorkplace simpleWorkplace = new SimpleWorkplace();

        simpleWorkplace.setName(node.get("projectName").asText());
        simpleWorkplace.setSavePath(node.get("savePath").asText());
        simpleWorkplace.setExistingWindowCount(node.get("existingWindowCount").asInt());
        simpleWorkplace.setCompositeWindowCount(node.get("compositeWindowCount").asInt());
        simpleWorkplace.setEnvisionedWindowCount(node.get("envisionedWindowCount").asInt());

        ArrayList<ModelNode> groupNodeList = modelNodeMapper.readValue(groupingNode.asText(), new TypeReference<>() {});
        simpleWorkplace.setGroupingNodes(groupNodeList);

        ArrayList<SimpleConnection> groupConnectionList = connectionNodeMapper.readValue(workplaceConnectionNode.asText(), new TypeReference<>() {});
        simpleWorkplace.setGroupingConnections(groupConnectionList);

        ArrayList<WorkplaceNode> commentNodeList = commentNodeMapper.readValue(commentNode.asText(), new TypeReference<>() {});
        simpleWorkplace.setComments(commentNodeList);

        ArrayList<SimplePaintWindow> paintWindowList = paintWindowMapper.readValue(paintWindowNode.asText(), new TypeReference<>() {});
        simpleWorkplace.setPaintWindows(paintWindowList);

        return simpleWorkplace;
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
