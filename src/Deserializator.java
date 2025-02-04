import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Deserializator {

    public static void main(String[] args) {
        String restoredPath = Instalator.gamePath + "\\savegames";
        String zipPath = Instalator.gamePath + "\\savegames\\save.zip";

        openZip(zipPath, restoredPath);
        GameProgress game = openProgress(restoredPath + "\\save1.dat");
        System.out.println(game);
    }

    private static void openZip(String zipPath, String saveToPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String fileName;
            while ((entry = zin.getNextEntry()) != null) {
                fileName = String.format("%s\\%s", saveToPath, entry.getName());

                FileOutputStream fout = new FileOutputStream(fileName);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static GameProgress openProgress(String fromPath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(fromPath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return  gameProgress;
    }
}
