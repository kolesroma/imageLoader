package task2;

import task2.cryptor.CryptorMD5;
import task2.finder.JsoupFinder;
import task2.loader.LoaderFromLink;
import task2.service.LoadPictureService;
import task2.service.SingleThreadPictureService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootPath = "src/main/resources/folder";
        LoadPictureService singleThreadPictureService = new SingleThreadPictureService(new JsoupFinder(),
                new LoaderFromLink(new CryptorMD5()),
                rootPath);
        singleThreadPictureService.processFileAndLoadPics();
    }
}