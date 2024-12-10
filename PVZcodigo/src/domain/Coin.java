
/**
 * This is an interfaces to all the coins.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
<<<<<<< HEAD
public abstract class Coin implements Element, Mover {
    protected String name;
    protected int value;
    protected int xPosition = 140;
    protected int yPosition = 70;
    protected int row;
    protected int col;
    protected int finishRow;
    protected int width = 50;
    protected int height = 50;
    protected String extension = "G.png";
=======
public abstract class Coin implements Element {
    protected String name;
    protected int value;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int row;
    protected int col;
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910

    /**
     * Abstract creator to coins
     * @param row, row of coin.
     * @param col, col of the coin.
<<<<<<< HEAD
     */
    public Coin(int row,int col,int finishRow) throws PVZException{
        if(col == 0) throw new PVZException(PVZException.COIN_BAD_POSITION);
        xPosition += (col * 70);
        yPosition += (row * 74);
        this.finishRow = finishRow;
=======
     * @param startCol, col where the sun start to move.
     */
    public Coin(int row,int col, int startRow,int startCol) {
        xPosition = (startCol * 75);
        yPosition = (startRow * 70);
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
        this.row = row;
        this.col = col;
    }

<<<<<<< HEAD

    public int getXPosition() {return xPosition;}

    public int getYPosition(){return yPosition;}


    public int getValue(){return this.value;}


    public String getName(){return this.name;}


    public void move(){
        if (row < finishRow) {
            yPosition += 75;
            if (((yPosition - 70) % 75) == 0) row += 1;
        }
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public String getExtension(){return extension;}


=======
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

>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910

}
