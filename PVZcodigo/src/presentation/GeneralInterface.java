package presentation;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public interface GeneralInterface{


    /*
     * Busca la ubicacion de un archivo de audio.
     * @param fileName, nombre del archivo.
     * @return retorna la direccion del archivo.
     */
    default String getClip(String fileName){
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String musicPath = baseDir + "\\Sounds" + "\\" + fileName;

        return musicPath;
    }

    /*
     * Busca el ImageIcon de un archivo de imagen.
     * @param fileName, nombre del archivo.
     * @return retorna el ImageIcon del archivo.
     */
    default ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }


}