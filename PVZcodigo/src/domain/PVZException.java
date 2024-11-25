package domain;

public class PVZException extends Exception {
    public static final String NOT_PLANTS_CHOOSED_TO_PLAY ="No ha sido seleccionada ninguna planta, por lo tanto no puede iniciar el juego"; //Excepcion si se orpime siguiente sin seleccionar plantas.
    public static final String NOT_ZOMBIES_CHOOSED_TO_PLAY ="No ha sido seleccionada ningun zombie, por lo tanto no puede iniciar el juego"; //Excepcion si se orpime jugar sin seleccionar zombies.
    public static final String ERROR_CANT_PLANT = "No se puede planta es esa casilla";
    public static final String ERROR_CELDA_NOT_EMPTY = "Ya se encuentra una planta en esta celda";
    public PVZException(String message) {super(message);}

}
