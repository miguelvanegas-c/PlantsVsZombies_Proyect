
/**
 * This is an interface to all the zombies.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Zombie implements Element,Move {

    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int value;
    protected int row;
    protected int col;

    /**
     * Constructor to zombies.
     * @param row, row of the zombie
     * @param col, col of the zombie
     */
    public Zombie(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
        this.row = row;
        this.col = col;
    }
    public String getName(){return this.name;}

    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getValue(){return value;}
    public int getRow() {return row;}
    public int getCol() {return col;}
    @Override
    public void move(){

    }


}
