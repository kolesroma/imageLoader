package task2.service;

import task2.finder.Finder;
import task2.loader.Loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultiThreadPictureService implements LoadPictureService {
    private final String rootFolder;
    private final Finder finder;
    private final Loader loader;

    public MultiThreadPictureService(Finder finder, Loader loader, String rootFolder) {
        this.finder = finder;
        this.loader = loader;
        this.rootFolder = rootFolder;
    }

    @Override
    public void processFileAndLoadPics() throws IOException {
        Files.walk(Paths.get(rootFolder))
                // fixed thread pool
                // todo
                .parallel()
                .filter(Files::isRegularFile)
                .flatMap(p -> finder.findLinks(p.toFile()).stream())
                .forEach(loader::saveFileLocally);
    }
}
