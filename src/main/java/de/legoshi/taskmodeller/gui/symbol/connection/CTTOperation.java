package de.legoshi.taskmodeller.gui.symbol.connection;

public enum CTTOperation {

    CHOICE,
    ORDER_INDEPENDENCE,
    INTERLEAVING,
    SYNCHRONIZATION,
    PARALLEL,
    DISABLING,
    SUSPEND_RESUME,
    SEQUENTIAL_ENABLING,
    SEQUENTIAL_ENABLING_INFO;

    public String OperationToString() {
        return switch (this) {
            case CHOICE -> "[]";
            case ORDER_INDEPENDENCE -> "|=|";
            case INTERLEAVING -> "|||";
            case SYNCHRONIZATION -> "|[ ]|";
            case PARALLEL -> "||";
            case DISABLING -> "[>";
            case SUSPEND_RESUME -> "|>";
            case SEQUENTIAL_ENABLING -> ">>";
            default -> "[]>>";
        };
    }

    public static CTTOperation StringToOperation(String s) {
        return switch (s) {
            case "[]" -> CHOICE;
            case "|=|" -> ORDER_INDEPENDENCE;
            case "|||" -> INTERLEAVING;
            case "|[ ]|" -> SYNCHRONIZATION;
            case "||" -> PARALLEL;
            case "[>" -> DISABLING;
            case "|>" -> SUSPEND_RESUME;
            case ">>" -> SEQUENTIAL_ENABLING;
            case "[]>>" -> SEQUENTIAL_ENABLING_INFO;
            default -> null;
        };
    }

}
