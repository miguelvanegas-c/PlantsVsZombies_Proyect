
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
    protected int yPosition = 65;
    protected int row;
    protected int col;
    protected int finishRow;
    protected int width = 50;
    protected int height = 50;
    protected String extension = "G.png";

    /**
     * Abstract creator to coins
     * @param row, row of coin.
     * @param col, col of the coin.
     */
    public Coin(int row,int col,int finishRow) throws PVZException{
        if(col == 0) throw new PVZException(PVZException.COIN_BAD_POSITION);
        xPosition += (col * 70);
        yPosition += (row * 74);
        this.finishRow = finishRow;
        this.row = row;
        this.col = col;
    }


    public int getXPosition() {return xPosition;}

    public int getYPosition(){return yPosition;}


    public int getValue(){return this.value;}


    public String getName(){return this.name;}


    public void move(){
        if (row < finishRow) {
            yPosition += 37;
            if (((yPosition - 65) % 74) == 0) row += 1;
        }
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public String getExtension(){return extension;}



}
