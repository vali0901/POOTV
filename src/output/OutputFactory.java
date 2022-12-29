package output;

public final  class OutputFactory {
    private OutputFactory() { }

    /**
     * Creates a specific Output object based on the given type
     * @param type The type of the output
     * @return The needed output instance
     */
    public static Output createOutput(final String type) {
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
