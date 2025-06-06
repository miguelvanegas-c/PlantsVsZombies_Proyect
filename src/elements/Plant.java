import java.io.Serializable;

/**
 * This is an interfaces to all the plants.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Plant implements Element, Serializable {
    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int value;
    protected int row;
    protected int col;
    protected int life;
    protected int width = 50;
    protected int height = 50;
    protected String extension = "G.png";

    /**
     * Create a plant in the row and col that are given.
     * @param row, row of the plant.
     * @param col, col of the plant.
     */
    public Plant(int row, int col) {
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
    public int getRow() {return row;}
    public int getCol() {return col;}
    public int getValue() {return value;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getExtension() {return extension;}
    public int getLife() {return life;}
    public void takeDamage(int damage) {
        life -= damage;
    }

}
