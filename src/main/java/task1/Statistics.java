package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {
    public void printWordStats(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.flatMap(line -> Arrays.stream(line.split("\\P{L}+")))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    .forEach((k, v) -> System.out.println(k + " - " + v));
        }
    }
}
