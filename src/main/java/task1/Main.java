package task1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/file.txt";
        new Statistics().printWordStats(path);
    }
}
