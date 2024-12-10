
/**
 * This is an interfaces to all the plants.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Plant implements Element {
    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;
<<<<<<< HEAD
    protected int value;
    protected int row;
    protected int col;
    protected int life;
    protected int width = 50;
    protected int height = 50;
    protected String extension = "G.png";
=======
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910

    /**
     * Create a plant in the row and col that are given.
     * @param row, row of the plant.
     * @param col, col of the plant.
     */
    public Plant(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
<<<<<<< HEAD
        this.row = row;
        this.col = col;
=======
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
    }
    public String getName(){return this.name;}
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
<<<<<<< HEAD
    public int getRow() {return row;}
    public int getCol() {return col;}
    public int getValue() {return value;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getExtension() {return extension;}
    public int getLife() {return life;}
=======
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910


}
