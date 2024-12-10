/**
 * The ZombieWithShield is a zombie that have more life than a basic zombie and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class ZombieWithShield extends Zombie{
    private Shield shield;
    /**
     * Constructor of the ZombieWithShield
     * @param row where the zombie is going to appear.
     */
    public ZombieWithShield(int row,Shield shield) {
        super(row,9);
        this.shield = shield;
        life += shield.getLife();
        value += shield.getValue();
    }
    public Shield getShield() {return shield;}
<<<<<<< HEAD

    @Override
    public int attack() {
        return 0;
    }
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
}
