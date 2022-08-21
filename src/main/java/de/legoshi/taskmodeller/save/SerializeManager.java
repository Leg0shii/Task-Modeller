package de.legoshi.taskmodeller.save;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.symbol.connection.GroupingConnection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.save.serialize.ConnectionSerializer;
import de.legoshi.taskmodeller.save.serialize.NodeSerializer;
import de.legoshi.taskmodeller.save.serialize.PaintWindowSerializer;
import de.legoshi.taskmodeller.save.serialize.WorkPlaceNodeSerializer;

import java.util.ArrayList;

public class SerializeManager {

    public static String serializeModelNodesOfPW(PaintWindow paintWindow) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(ModelNode.class, new NodeSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<ModelNode> modelNodes = paintWindow.getDrawnNodes();
        return stripString(mapper.writeValueAsString(modelNodes));
    }

    public static String serializeConnectionsOfPW(PaintWindow paintWindow) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(Connection.class, new ConnectionSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<Connection> connections = paintWindow.getConnections();
        return stripString(mapper.writeValueAsString(connections));
    }

    public static String serializePW(Workplace workplace) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(PaintWindow.class, new PaintWindowSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<PaintWindow> allWindows = workplace.getAllWindows();
        return stripString(mapper.writeValueAsString(allWindows));
    }

    public static String serializeGroupingNodes(Workplace workplace) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(ModelNode.class, new NodeSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<ModelNode> allWindows = workplace.getGeneralisedList();
        return stripString(mapper.writeValueAsString(allWindows));
    }

    public static String serializeCommentNodes(Workplace workplace) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(WorkplaceNode.class, new WorkPlaceNodeSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<WorkplaceNode> comments = workplace.getCommentList();
        return stripString(mapper.writeValueAsString(comments));
    }

    public static String serializeProjectWindowConnections(Workplace workplace) throws JsonProcessingException {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addSerializer(Connection.class, new ConnectionSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayList<GroupingConnection> groupingConnections = workplace.getGroupingConnections();

        return stripString(mapper.writeValueAsString(groupingConnections));
    }

    private static String stripString(String s) {
        return s.replace("\n", "").replace("\r", ""); // .replaceAll("\\\\\"", "\"")
    }

}
