package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultiService implements Service {
    private final String rootFolder;
    private final Finder finder;
    private final Loader loader;

    public MultiService(Finder finder, Loader loader, String rootFolder) {
        this.finder = finder;
        this.loader = loader;
        this.rootFolder = rootFolder;
    }

    @Override
    public void invoke() throws IOException {
        Files.walk(Paths.get(rootFolder))
                // fixed thread pool
                // todo
                .parallel()
                .filter(Files::isRegularFile)
                .flatMap(p -> finder.findLinks(p.toFile()).stream())
                .forEach(loader::saveFileLocally);
    }
}
