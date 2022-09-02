package task2.finder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class JsoupFinder implements Finder {
    /**
     * parse the file as html and provide all links to images inside the file
     * @param file any
     * @return unique links as Strings
     * @throws RuntimeException if cannot parse html
     * @throws RuntimeException if file is null
     */
    public Set<String> findLinks(File file) {
        if (file == null) throw new RuntimeException("file cannot be null value");
        Document doc;
        try {
            doc = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("cannot parse html", e);
        }
        return doc.select("img[src]").stream()
                .peek(img -> System.out.printf(" * found %s: <%s>\n", img.tagName(), img.attr("abs:src")))
                .map(img -> img.attr("abs:src"))
                .collect(Collectors.toSet());
    }
}
