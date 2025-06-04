import java.io.Serializable;

/**
 * The Shield is a shield that some zombies can have.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class Shield implements Serializable {
    protected String name;
    protected int life;
    protected int value;

    /**
     * Constructor for the class shield.
     */

    public Shield() {

    }
    public int getValue() {return value;}
    public String getName() {return name;}
    public int getLife() {return life;}
    public int takeDamage(int damage) {
        life -= damage;
        if (life <= 0) {
            name = "";
            return life;
        }
        return 0;
    }
}
