package src.main.java;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonAsString {

    protected String readFileAsString(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
