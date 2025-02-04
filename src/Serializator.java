import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Serializator {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(3, 7, 2, 44);
        GameProgress game2 = new GameProgress(60, 211, 43, 1000);
        GameProgress game3 = new GameProgress(30, 311, 63, 10000);

        String game1Path = Instalator.gamePath + "\\savegames\\save1.dat";
        String game2Path = Instalator.gamePath + "\\savegames\\save2.dat";
        String game3Path = Instalator.gamePath + "\\savegames\\save3.dat";

        saveGame(game1Path, game1);
        saveGame(game2Path, game2);
        saveGame(game3Path, game3);

        List<String> objPaths = new ArrayList<>();
        objPaths.add(game1Path);
        objPaths.add(game2Path);
        objPaths.add(game3Path);

        zipFiles(Instalator.gamePath + "\\savegames\\save.zip", objPaths);

        deleteFiles(objPaths);
    }

    private static void saveGame(String path, GameProgress objToSave) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(objToSave);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String zipPath, List<String> objPaths) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipPath))) {

            for (String objPath : objPaths) {
                FileInputStream fis = new FileInputStream(objPath);
                ZipEntry entry = new ZipEntry(objPath.substring(objPath.lastIndexOf("\\") + 1));
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);

                zout.write(buffer);
                fis.close();
            }
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFiles(List<String> objPaths) {
        for (String path : objPaths) {
            File myObj = new File(path);
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file " + myObj.getName());
            }
        }
    }
}
