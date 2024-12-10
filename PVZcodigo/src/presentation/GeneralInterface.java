import javax.swing.*;
/**
 * A general interface to PVZGUI, PVZInGame Y Difficulty. .
 *
 * @author Miguel Angel Vanegas and Julian Castiblanco.
 * @version 1.0
 */
public interface GeneralInterface {

    /*
     * Finds the location of an audio file.
     * @param fileName, name of the file.
     * @return returns the file path.
     */
    default String getClip(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String musicPath = baseDir + "\\Sounds" + "\\" + fileName;

        return musicPath;
    }

    /*
     * Finds the ImageIcon of an image file.
     * @param fileName, name of the file.
     * @return returns the ImageIcon of the file.
     */
    default ImageIcon getImageIcon(String fileName) {
<<<<<<< HEAD
<<<<<<< HEAD
        String imagePath = "images/" + fileName;
=======
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
        String imagePath = "images/" + fileName;
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b

        return new ImageIcon(imagePath);
    }

}