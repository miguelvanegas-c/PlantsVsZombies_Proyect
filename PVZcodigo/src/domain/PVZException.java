package domain;

public class PVZException extends Exception {
    public static final String NOT_PLANTS_CHOOSED_TO_PLAY ="No ha sido seleccionada ninguna planta, por lo tanto no puede iniciar el juego"; //Excepcion si se orpime jugar sin seleccionar plantas.


    public PVZException(String message) {super(message);}
}
