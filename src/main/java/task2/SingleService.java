package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleService implements Service {
    private final String rootFolder;
    private final Finder finder;
    private final Loader loader;

    public SingleService(Finder finder, Loader loader, String rootFolder) {
        this.finder = finder;
        this.loader = loader;
        this.rootFolder = rootFolder;
    }

    @Override
    public void invoke() throws IOException {
        Files.walk(Paths.get(rootFolder))
                .filter(Files::isRegularFile)
                .flatMap(p -> finder.findLinks(p.toFile()).stream())
                .forEach(loader::saveFileLocally);
    }
}
