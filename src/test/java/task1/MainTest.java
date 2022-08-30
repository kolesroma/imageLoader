package task1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void printWordStatsTest() {
        String path = "src/main/resources/simpleFile.txt";
        try {
            new Statistics().printWordStats(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            fail();
        }
        assertEquals("word1 - 2\nword2 - 1\nword3 - 1",
                outContent.toString().strip());
    }
}
