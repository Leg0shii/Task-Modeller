package de.legoshi.taskmodeller.save.simple_object;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleConnection {

    private String id;
    private String className;
    private String node1ID;
    private String node2ID;
    private Color color;
    private double strokeWidth;
    private String name;

}
