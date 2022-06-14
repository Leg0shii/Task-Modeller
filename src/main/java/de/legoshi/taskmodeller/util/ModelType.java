package de.legoshi.taskmodeller.util;

public enum ModelType {

    FREE,
    CTT;

    public ModelType stringToType(String s) {
        return switch (s) {
            case "free" -> ModelType.FREE;
            default -> ModelType.CTT;
        };
    }

}
