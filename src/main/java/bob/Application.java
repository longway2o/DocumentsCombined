package bob;

import bob.model.FileCombine;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        FileCombine fileCombine = new FileCombine();
        String filePath = System.getProperty("user.dir") + File.separator + "src/main/resources";
        fileCombine.setListsDir(filePath);
        fileCombine.setOutDir(filePath);
        fileCombine.setResourceDir(filePath + File.separator + "documents");
        fileCombine.CombineFiles();
    }
}
