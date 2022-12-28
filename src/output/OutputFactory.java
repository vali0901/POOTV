package output;

public class OutputFactory {
    public static Output createOutput(String type) {
        switch (type) {
            case "error" -> {
                return new ErrorOutput();
            }
            case "log" -> {
                return new LogOutput();
            }
            case "final" -> {
                return new FinalOutput();
            }
            default -> {
            }
        }

        return null;
    }
}
