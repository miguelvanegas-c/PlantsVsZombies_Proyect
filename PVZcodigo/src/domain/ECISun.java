public class ECISun extends Coin{

    /**
     * Creator to sun
     * @param row, row where the sun is going to finish.
     * @param col, col where the sun is going to finish.
     * @param finishRow, row where the sun is going to finish.
     */
    public ECISun(int row,int col,int finishRow) throws PVZException{
        super(row, col,finishRow);
        name = "ecisun";
        value = 50;
    }
}
