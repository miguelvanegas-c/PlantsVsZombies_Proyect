/**
 * The basicZombies is a zombie that have a basic life and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class BasicZombie extends Zombie{


    /**
     * Constructor of the basic zombie
     * @param row where the zombie is going to appear.
     */
    public BasicZombie(int row) {
<<<<<<< HEAD
<<<<<<< HEAD
        super(row,9);
        value = 100;
    }


    @Override
    public int attack() {
        return 0;
    }
=======
        super(row,10);
        name = "zombie";
=======
        super(row,9);
        value = 100;

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    }


>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
}