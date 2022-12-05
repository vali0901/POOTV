import app.App;
import app.actions.ActionMaker;
import app.pages.Page;
import app.pages.PageFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.Input;

import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode output = objectMapper.createArrayNode();

        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        App.getApp().start(inputData);

        ActionMaker.action(inputData, output);

        objectMapper.writerWithDefaultPrettyPrinter();
        objectMapper.writeValue(new File(args[1]), output);
    }
}
