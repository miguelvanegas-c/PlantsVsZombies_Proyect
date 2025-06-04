import java.io.Serializable;

/**
 * The basicZombies is a zombie that have a basic life and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class BasicZombie extends Zombie implements Serializable {


    /**
     * Constructor of the basic zombie
     * @param row where the zombie is going to appear.
     */
    public BasicZombie(int row) {
        super(row,9);
    }


}