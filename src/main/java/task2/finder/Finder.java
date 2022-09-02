package task2.finder;

import java.io.File;
import java.util.Set;

public interface Finder {
    Set<String> findLinks(File file);
}
