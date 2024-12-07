
/**
 * This is an interfaces to all the coins.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Coin implements Element,Move {
    protected String name;
    protected int value;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int row;
    protected int col;
    protected int finishRow;

    /**
     * Abstract creator to coins
     * @param row, row of coin.
     * @param col, col of the coin.
     */
    public Coin(int row,int col,int finishRow) {
        xPosition += (col * 70);
        yPosition += (row * 75);
        this.finishRow = finishRow;
        this.row = row;
        this.col = col;
    }

    @Override
    public int getXPosition() {return xPosition;}
    @Override
    public int getYPosition(){return yPosition;}

    @Override
    public int getValue(){return this.value;}

    @Override
    public String getName(){return this.name;}

    public void move(){
        if (row < finishRow) {
            yPosition += 15;
            if (((yPosition - 55) % 75) == 0) row += 1;
        }
    }

    public int getRow(){return row;}
    public int getCol(){return col;}



}
