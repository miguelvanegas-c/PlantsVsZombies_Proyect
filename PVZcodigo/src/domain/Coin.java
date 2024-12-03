
/**
 * This is an interfaces to all the coins.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Coin implements Element {
    protected String name;
    protected int value;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int row;
    protected int col;

    /**
     * Abstract creator to coins
     * @param row, row of coin.
     * @param col, col of the coin.
     * @param startCol, col where the sun start to move.
     */
    public Coin(int row,int col, int startRow,int startCol) {
        xPosition = (startCol * 75);
        yPosition = (startRow * 70);
        this.row = row;
        this.col = col;
    }

    @Override
    public int getXPosition() {return xPosition;}
    @Override
    public int getYPosition(){return yPosition;}

    public int getValue(){return this.value;}

    @Override
    public String getName(){return this.name;}

    public void move(int actualRow, int actualCol){
        if (col< actualCol) xPosition -=1;
        else if (col > actualCol) xPosition += 1;
        if(row < actualRow) yPosition -=1;
        else if (row > actualRow)yPosition += 1;
    }


}
