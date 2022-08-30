package task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootPath = "src/main/resources/folder";
        Service singleService = new SingleService(new JsoupFinder(), new LoaderFromLink(new CryptorMD5()), rootPath);
        singleService.invoke();
    }
}