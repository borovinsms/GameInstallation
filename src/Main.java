import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String GAMES_DIRECTORY = "/Games";
    private static final String GAME_NAME = "/testGame";
    private static final String[] GAME_DIRECTORIES = {"/src", "/res", "/savegames", "/temp"};
    private static final String[] SRC_DIRECTORIES = {"/main", "/test"};
    private static final String[] RES_DIRECTORIES = {"/drawables", "/vectors", "/icons"};
    private static final String[] MAIN_FILES = {"/Main.java", "/Utils.java"};
    private static final String TMP_FILE = "/temp.txt";

    private static String createSubDir(String path, String dirName) {
        File f = new File(path + dirName);
        if (f.mkdir()) {
            return "Директория \"" + dirName + "\" была создана\n";
        } else {
            return "Директория \"" + dirName + "\" не была создана\n";
        }
    }

    private static String createSubFile(String path, String fileName) {
        File f = new File(path + fileName);
        try {
            if (f.createNewFile()) {
                return "Файл \"" + fileName + "\" был создан\n";
            } else {
                return "Файл \"" + fileName + "\" не был создан\n";
            }
        } catch (IOException e) {
            return "Ошибка создания файла \"" + fileName + "\"\n";
        }
    }

    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        StringBuilder sb = new StringBuilder();
        sb.append(createSubDir(homeDir, GAMES_DIRECTORY));
        sb.append(createSubDir(homeDir+GAMES_DIRECTORY, GAME_NAME));
        for (String dir: GAME_DIRECTORIES) {
            sb.append(createSubDir(homeDir+GAMES_DIRECTORY+GAME_NAME, dir));
        }
        for (String dir: SRC_DIRECTORIES) {
            sb.append(createSubDir(homeDir+GAMES_DIRECTORY+GAME_NAME+GAME_DIRECTORIES[0], dir));
        }
        for (String dir: RES_DIRECTORIES) {
            sb.append(createSubDir(homeDir+GAMES_DIRECTORY+GAME_NAME+GAME_DIRECTORIES[1], dir));
        }
        for (String file: MAIN_FILES) {
            sb.append(createSubFile(homeDir+GAMES_DIRECTORY+GAME_NAME+GAME_DIRECTORIES[0]+SRC_DIRECTORIES[0], file));
        }
        sb.append(createSubFile(homeDir+GAMES_DIRECTORY+GAME_NAME+GAME_DIRECTORIES[3], TMP_FILE));

        try {
            try (FileWriter fw = new FileWriter(homeDir + GAMES_DIRECTORY + GAME_NAME + GAME_DIRECTORIES[3] + TMP_FILE)) {
                fw.write(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}