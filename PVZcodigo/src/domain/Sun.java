public class Sun extends Coin{

    /**
     * Creator to sun
     * @param row, row where the sun is going to finish.
     * @param col, col where the sun is going to finish.
     * @param startCol, col where the sun is going to start.
     */
    public Sun(int row,int col, int startRow,int startCol){
        super(row, col, startRow,startCol);
        name = "sun";
        value = 25;
    }
}
