import java.io.Serializable;

/**
 * The ZombieWithShield is a zombie that have more life than a basic zombie and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class ZombieWithShield extends Zombie implements Serializable {
    private Shield shield;
    /**
     * Constructor of the ZombieWithShield
     * @param row where the zombie is going to appear.
     */
    public ZombieWithShield(int row,Shield shield) {
        super(row,9);
        this.shield = shield;
        value += shield.getValue();
    }
    public Shield getShield() {return shield;}

    @Override
    public void takeDamage(int damage) {
        if(shield.getLife() > 0) {
            life += shield.takeDamage(damage);
        }else{
            super.takeDamage(damage);
        }
    }
}
