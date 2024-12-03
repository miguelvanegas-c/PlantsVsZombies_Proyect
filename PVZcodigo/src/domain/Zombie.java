
/**
 * This is an interface to all the zombies.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Zombie implements Element {

    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;

    /**
     * Constructor to zombies.
     * @param row, row of the zombie
     * @param col, col of the zombie
     */
    public Zombie(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
    }
    public String getName(){return this.name;}
    public abstract void move();
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }


}
