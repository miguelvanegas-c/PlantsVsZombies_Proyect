import java.io.Serializable;

public class Sun extends Coin implements Serializable {

    /**
     * Creator to sun
     * @param row, row where the sun is going to finish.
     * @param col, col where the sun is going to finish.
     * @param finishRow, row where the sun is going to finish.
     */
    public Sun(int row,int col,int finishRow) throws PVZException{
        super(row, col,finishRow);
        name = "sun";
        value = 25;
    }
}
