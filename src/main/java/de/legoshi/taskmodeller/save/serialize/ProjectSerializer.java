package de.legoshi.taskmodeller.save.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.save.SerializeManager;

import java.io.IOException;

public class ProjectSerializer extends JsonSerializer<Workplace>  {

    @Override
    public void serialize(Workplace workplace, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("projectName", workplace.getWorkplaceName());
        gen.writeStringField("savePath", workplace.getSavePath());
        gen.writeNumberField("existingWindowCount", workplace.getProjectWindow().getExistentCount());
        gen.writeNumberField("compositeWindowCount", workplace.getProjectWindow().getCompositeCount());
        gen.writeNumberField("envisionedWindowCount", workplace.getProjectWindow().getEnvisionedCount());
        gen.writeStringField("groupingNodes", SerializeManager.serializeGroupingNodes(workplace));
        gen.writeStringField("commentNodes", SerializeManager.serializeCommentNodes(workplace));
        gen.writeStringField("workplaceConnections", SerializeManager.serializeProjectWindowConnections(workplace));
        gen.writeStringField("paintWindows", SerializeManager.serializePW(workplace));
        gen.writeEndObject();
    }

}
