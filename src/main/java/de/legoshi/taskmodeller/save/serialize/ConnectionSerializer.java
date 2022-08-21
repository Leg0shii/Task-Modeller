package de.legoshi.taskmodeller.save.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.legoshi.taskmodeller.gui.symbol.connection.Connection;

import java.io.IOException;

public class ConnectionSerializer extends JsonSerializer<Connection> {

    @Override
    public void serialize(Connection connection, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", connection.getId());
        gen.writeStringField("name", connection.getLabel().getText());
        gen.writeNumberField("strokeWidth", connection.getStrokeWidth());
        gen.writeStringField("className", connection.getClass().getSimpleName());
        gen.writeStringField("color", connection.getStroke().toString());
        gen.writeStringField("node1ID", connection.getNode1().getId());
        gen.writeStringField("node2ID", connection.getNode2().getId());
        gen.writeEndObject();
    }

}
