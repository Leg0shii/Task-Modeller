package de.legoshi.taskmodeller.save.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.legoshi.taskmodeller.gui.symbol.WorkplaceNode;

import java.io.IOException;

public class WorkPlaceNodeSerializer extends JsonSerializer<WorkplaceNode> {

    @Override
    public void serialize(WorkplaceNode workplaceNode, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", workplaceNode.getId());
        gen.writeStringField("className", workplaceNode.getClass().getSimpleName());
        gen.writeStringField("color", workplaceNode.getPolyShape().getFill().toString());
        gen.writeStringField("name", workplaceNode.getLabel().getText());
        gen.writeNumberField("posX", workplaceNode.getTranslateX());
        gen.writeNumberField("posY", workplaceNode.getTranslateY());
        gen.writeEndObject();
    }

}
