package de.legoshi.taskmodeller.save;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import de.legoshi.taskmodeller.gui.windows.dialogwindow.PopUpWindow;
import de.legoshi.taskmodeller.save.deserialize.ProjectDeserializer;
import de.legoshi.taskmodeller.save.simple_object.SimpleWorkplace;

import java.io.File;
import java.io.IOException;

public class DeserializeManager {

    public static void applyWorkplaceChanges(Workplace workplace, File file) {
        SimpleModule javafxModule = new SimpleModule();
        javafxModule.addDeserializer(SimpleWorkplace.class, new ProjectDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javafxModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            SimpleWorkplace simpleWorkplace = mapper.readValue(file, SimpleWorkplace.class);
            simpleWorkplace.applySettingsToWorkplace(workplace);
        } catch (IOException e) {
            new PopUpWindow("Error", "Cant open this file.").show();
            throw new RuntimeException(e);
        }
    }

}
