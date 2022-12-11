import app.App;
import app.actions.ActionMaker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() { }

    /**
     * Entry of the POOTV app
     * @param args Two Strings representing the input and output file names
     * @throws IOException If at least one of the files does not exist
     */
    public static void main(final String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        App.getApp().start(inputData);
        ActionMaker.action(inputData, output);

        objectMapper.writerWithDefaultPrettyPrinter();
        objectMapper.writeValue(new File(args[1]), output);
    }
}
