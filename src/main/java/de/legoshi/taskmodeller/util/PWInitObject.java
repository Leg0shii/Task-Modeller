package de.legoshi.taskmodeller.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PWInitObject {

    private String name;
    private String modelType;

    public PWInitObject(String name, String modelType) {
        this.name = name;
        this.modelType = modelType;
    }
}
