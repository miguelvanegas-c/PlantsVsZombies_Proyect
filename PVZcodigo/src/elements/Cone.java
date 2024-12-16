import java.io.Serializable;

/**
 * The Cone is a shield that some zombies can have.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class Cone extends Shield implements Serializable {

    /**
     * Constructor of the class Cone.
     */
    public Cone() {
        super();
        name = "Cone";
        life = 280;
        value = 50;
    }
}
