package task2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoaderFromLink implements Loader {
    private final String rootSavingFolder;
    private final Cryptor cryptor;

    public LoaderFromLink(Cryptor cryptor) {
        this.cryptor = cryptor;
        this.rootSavingFolder = "src/main/resources/output";
    }

    public LoaderFromLink(Cryptor cryptor, String rootSavingFolder) {
        this.cryptor = cryptor;
        this.rootSavingFolder = rootSavingFolder;
    }

    /**
     * take a full https link and provide a link path with no protocol
     * @param linkHttp https://www.library.kpi.ua/themes/images/map.png
     * @return /themes/images/map.png
     * @throws RuntimeException if cannot parse URL
     */
    public String cutProtocol(String linkHttp) {
        try {
            return new URL(linkHttp).getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("cannot parse URL", e);
        }
    }

    /**
     * save file locally by link;
     * also save a decrypted md5 txt file in same dir;
     * path dir = root + cut link
     * @param link cut to path
     * @throws RuntimeException if cannot parse URL or cannot create directories locally
     */
    public void saveFileLocally(String link) {
        Path targetFilePath = Paths.get(rootSavingFolder + cutProtocol(link));
        try (InputStream in = new URL(link).openStream()) {
            Files.createDirectories(targetFilePath.getParent());
            byte[] bytes = in.readAllBytes();
            Files.write(targetFilePath, bytes);
            Files.write(Paths.get(targetFilePath + ".txt"), new CryptorMD5().crypt(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
