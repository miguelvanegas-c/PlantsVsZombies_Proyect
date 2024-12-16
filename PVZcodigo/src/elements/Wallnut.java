import java.io.Serializable;

public class Wallnut extends Plant implements Serializable {


    /**
     * Create a wallnut in the row and col that are given.
     * @param row, row of the wallnut.
     * @param col, col of the wallnut.
     */
    public Wallnut(int row, int col){
        super(row, col);
        name = "wallnut";
        value = 50;
        life = 4000;
    }


}
