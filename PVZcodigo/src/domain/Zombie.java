
/**
 * This is an interface to all the zombies.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public interface Zombie {

    String getName();

    /**
     * move the zombie.
     */
    void move();
    int getXPosition();
    int getYPosition();
}
