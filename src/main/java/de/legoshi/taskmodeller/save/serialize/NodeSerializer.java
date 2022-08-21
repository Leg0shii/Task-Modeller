package de.legoshi.taskmodeller.save.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class NodeSerializer extends JsonSerializer<ModelNode> {

    @Override
    public void serialize(ModelNode modelNode, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", modelNode.getId());
        gen.writeStringField("className", modelNode.getClass().getSimpleName());
        gen.writeStringField("color", modelNode.getPolyShape().getFill().toString());
        gen.writeNumberField("scaleX", modelNode.getScaleX());
        gen.writeNumberField("scaleY", modelNode.getScaleY());
        if (modelNode instanceof GroupingNode)
            gen.writeStringField("strokeColor", String.valueOf(modelNode.getBorder().getStrokes().get(0).getTopStroke()));
        else gen.writeStringField("strokeColor", "Black");
        gen.writeStringField("name", modelNode.getLabel().getText());
        gen.writeStringField("description", modelNode.getDescription());
        gen.writeNumberField("posX", modelNode.getTranslateX());
        gen.writeNumberField("posY", modelNode.getTranslateY());
        gen.writeEndObject();
    }

}
