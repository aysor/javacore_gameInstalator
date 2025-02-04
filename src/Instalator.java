import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Instalator {
    public static String gamePath = "C:\\Users\\Aysel\\Desktop\\Aysel-Java\\Tasks\\Games";
    private static StringBuilder logger = new StringBuilder();

    public static void main(String[] args) {
        File gameDir = new File(gamePath);

        File srcDir = new File(gameDir, "src");
        LogDir(srcDir);
        File resDir = new File(gameDir, "res");
        LogDir(resDir);
        File savegamesDir = new File(gameDir, "savegames");
        LogDir(savegamesDir);
        File tempDir = new File(gameDir, "temp");
        LogDir(tempDir);

        File mainDir = new File(srcDir, "main");
        LogDir(mainDir);

        File mainJava = new File(mainDir, "Main.java");
        LogFile(mainJava);
        File utilsJava = new File(mainDir, "Utils.java");
        LogFile(utilsJava);

        File testDir = new File(srcDir, "test");
        LogDir(testDir);

        File drawablesDir = new File(resDir, "drawables");
        LogDir(drawablesDir);
        File vectorsDir = new File(resDir, "vectors");
        LogDir(vectorsDir);
        File iconsDir = new File(resDir, "icons");
        LogDir(iconsDir);

        File temp = new File(tempDir, "temp.txt");
        LogFile(temp);

        SaveLog(temp);
    }

    private static void LogDir(File dir) {
        logger.append(dir.mkdir() ? String.format("Directory '%s' was successfully created.\n", dir.getName())
                : String.format("Failed to create directory '%s'.\n", dir.getName()));
    }

    private static void LogFile(File file) {
        try {
            logger.append(file.createNewFile() ? String.format("File '%s' was successfully created.\n", file.getName())
                    : String.format("Failed to create file '%s'.\n", file.getName()));
        } catch (IOException e) {
            logger.append(String.format("Exception occurred during creating file '%s': %s\n", file.getName(), e.toString()));
        }
    }

    private static void SaveLog(File logFile) {
        try (FileWriter writer = new FileWriter(logFile, false)) {
            writer.write(logger.toString());
            writer.flush();
        } catch (IOException ex) {
            logger.append(String.format("Smth is wrong during writing to file: %s\n", ex.toString()));
        }
    }
}