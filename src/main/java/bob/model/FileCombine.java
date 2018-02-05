package bob.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCombine {
    private static final String LISTS_NAME = "list.txt";
    private static final String COMBINED_FILE_NAME = "combined.md";
    private List<String> lists;
    private String listsDir;
    private String resourceDir;
    private String outDir;

    public FileCombine() {
        lists = new ArrayList<>();
        listsDir = "";
        resourceDir = "";
        outDir = "";
    }

    private boolean GetFileLists() {
        try {
            FileReader reader = new FileReader(getListsDir() + File.separator + LISTS_NAME);
            BufferedReader br = new BufferedReader(reader);
            String str;
            while ((str = br.readLine()) != null) {
                lists.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean CombineFiles() {
        if (!GetFileLists()) {
            System.out.println("get file lists fail.");
            return false;
        }

        File outputFile = new File(getOutDir() + File.separator + COMBINED_FILE_NAME);
        FileOutputStream out;

        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("create output file fail.");
                return false;
            }
        }

        try {
            outputFile.createNewFile();
            out = new FileOutputStream(outputFile);

            for (String fileName : getLists()) {
                File resourceFile = new File(getResourceDir() + File.separator + fileName);
                if (!resourceFile.exists() || !resourceFile.isFile()) {
                    return false;
                }

                FileInputStream in = new FileInputStream(resourceFile);
                int temp;
                while (-1 != (temp = in.read())) {
                    out.write(temp);
                }
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("combine file fail.");
            return false;
        }

        System.out.println("combine file success.");
        return true;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public String getListsDir() {
        return listsDir;
    }

    public void setListsDir(String listsDir) {
        this.listsDir = listsDir;
    }

    public String getResourceDir() {
        return resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public String getOutDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }
}
