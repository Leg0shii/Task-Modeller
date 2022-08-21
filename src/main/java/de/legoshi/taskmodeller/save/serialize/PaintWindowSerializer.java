package de.legoshi.taskmodeller.save.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.save.SerializeManager;

import java.io.IOException;

public class PaintWindowSerializer extends JsonSerializer<PaintWindow> {

    @Override
    public void serialize(PaintWindow paintWindow, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", paintWindow.getId());
        gen.writeStringField("name", paintWindow.getName());
        gen.writeStringField("statusType", paintWindow.getStatusType().toString());
        gen.writeStringField("modelType", paintWindow.getModelType().toString());
        gen.writeStringField("modelNodes", SerializeManager.serializeModelNodesOfPW(paintWindow));
        gen.writeStringField("modelConnections", SerializeManager.serializeConnectionsOfPW(paintWindow));
        gen.writeEndObject();
    }

}
