/**
 * The basicZombies is a zombie that have a basic life and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class BasicZombie extends Element implements Zombie{
    private final String name = "zombie";

    /**
     * Constructor of the basic zombie
     * @param row where the zombie is going to appear.
     */
    public BasicZombie(int row) {
        super(row,10);
    }

    /**
     * move the zombie.
     */
    @Override
    public void move() {
        xPosition -= 1;
    }

    @Override
    public int getXPosition(){
        return xPosition;
    }

    @Override
    public int getYPosition(){
        return yPosition;
    }

    @Override
    public String getName(){
        return name;
    }
}