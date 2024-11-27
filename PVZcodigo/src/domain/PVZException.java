

public class PVZException extends Exception {
    public static final String NOT_PLANTS_CHOOSED_TO_PLAY ="No plant has been selected, so you cannot start the game."; //Exception push next without select plants.
    public static final String NOT_ZOMBIES_CHOOSED_TO_PLAY ="No zombie has been selected, therefore no zombie can start the game."; //Exception push next without select zombie.
    public static final String ERROR_CANT_PLANT = "Can't plant on this cell";
    public static final String ERROR_CELDA_NOT_EMPTY = "One plant is already in this cell";
    public PVZException(String message) {super(message);}

}
