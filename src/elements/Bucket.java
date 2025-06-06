import java.io.Serializable;

/**
 * The Bucket is a shield that some zombies can have.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class Bucket extends Shield implements Serializable {

    /**
     * Constructor of the class Bucket.
     */
    public Bucket() {
        super();
        name = "bucket";
        life = 700;
        value = 100;
    }
}
