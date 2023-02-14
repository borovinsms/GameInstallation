import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        String[] directories = {
                homeDir + "/Games",
                homeDir + "/Games/src",
                homeDir + "/Games/res",
                homeDir + "/Games/savegames",
                homeDir + "/Games/temp",
                homeDir + "/Games/src/main",
                homeDir + "/Games/src/test",
                homeDir + "/Games/res/drawables",
                homeDir + "/Games/res/vectors",
                homeDir + "/Games/res/icons"
        };
        String[] files = {
                homeDir + "/Games/src/main/Main.java",
                homeDir + "/Games/src/main/Utils.java",
                homeDir + "/Games/temp/temp.txt",
        };
        for (String dir : directories) {
            createSubDir(dir);
        }
        for (String file : files) {
            createSubFile(file);
        }
        try {
            try (FileWriter fw = new FileWriter(homeDir + "/Games/temp/temp.txt")) {
                fw.write(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static StringBuilder sb = new StringBuilder();

    private static void createSubDir(String path) {
        File f = new File(path);
        if (f.mkdir()) {
            sb.append("Директория \"" + path + "\" была создана\n");
        } else {
            sb.append("Директория \"" + path + "\" не была создана\n");
        }
    }

    private static void createSubFile(String path) {
        File f = new File(path);
        try {
            if (f.createNewFile()) {
                sb.append("Файл \"" + path + "\" был создан\n");
            } else {
                sb.append("Файл \"" + path + "\" не был создан\n");
            }
        } catch (IOException e) {
            sb.append("Ошибка создания файла \"" + path + "\"\n");
        }
    }

}
