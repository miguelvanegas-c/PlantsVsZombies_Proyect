

public class PVZException extends Exception {
    public static final String NOT_PLANTS_CHOOSED_TO_PLAY ="No plant has been selected, so you cannot start the game."; //Exception push next without select plants.
    public static final String NOT_ZOMBIES_CHOOSED_TO_PLAY ="No zombie has been selected, therefore no zombie can start the game."; //Exception push next without select zombie.
    public static final String ERROR_CANT_PLANT = "Can't plant on this cell";
    public static final String ERROR_CELDA_NOT_EMPTY = "One plant is already in this cell";
    public static final String ERROR_PLANT_NOT_EXIST = "The plant that do you wanna add doesn't exist";
    public static final String ERROR_PLANT_NOT_SELECTED = "You don't select a plant to add";
    public static final String ERROR_NOT_PLANT_NAME = "You don't choose a name to plant player";
    public static final String ERROR_NOT_PLANT_TYPE = "You don't choose a type to plant player";
    public static final String ERROR_NOT_ZOMBIE_NAME = "You don't choose a name to zombie player";
    public static final String ERROR_NOT_ZOMBIE_TYPE = "You don't choose a type to zombie player";
    public static final String ERROR_COIN_NULL = "You don´t select a coin";
    public static final String ERROR_COIN_EXIST = "The coin that do you wanna add doesn't exist";
    public static final String NOT_NUMBER = "The value isn't a number";
    public static final String ERROR_NOT_STARTING_SUNS = "The suns is less or equal that 0";
    public static final String ERROR_NOT_STARTING_BRAINS = "The brains is less or equal that 0";
    public static final String ERROR_LOW_GAME_TIME = "The game time is less than 40 that is the minimum game time, or more than 360 that is the maximum game time";
    public static final String ERROR_INCORRECT_HORDES_NUMBER = "The hordes number is less or equal than 0 or is bigger than 10";
    public static final String ERROR_LOW_HORDES_TIME = "The Hordes time is more than 10 that is the maximum hordes time, or less than 0";
    public static final String COIN_BAD_POSITION = "In this position can't cerate a coin";
    public static final String ERROR_ZOMBIE_NOT_SELECTED = "You don't select a zombie to add";
    public static final String ERROR_ZOMBIE_NOT_EXIST = "The zombie that do you wanna add doesn't exist";
    public static final String ERROR_NOT_ENOUGH_SUNS ="The suns aren't enough";
    public static final String ERROR_NOT_ENOUGH_BRAINS ="The brains aren't enough";
    public static final String NOT_PLANT_TO_DELETE = "There isn´t a plant in this position";

    public PVZException(String message) {super(message);}

}
