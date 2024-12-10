public class Sun extends Coin{

    /**
     * Creator to sun
     * @param row, row where the sun is going to finish.
     * @param col, col where the sun is going to finish.
<<<<<<< HEAD
<<<<<<< HEAD
     * @param finishRow, row where the sun is going to finish.
     */
    public Sun(int row,int col,int finishRow) throws PVZException{
        super(row, col,finishRow);
        name = "sun";
        value = 25;
    }

    @Override
    public String getState() {
        return "";
    }
=======
     * @param startCol, col where the sun is going to start.
=======
     * @param finishRow, row where the sun is going to finish.
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
     */
    public Sun(int row,int col,int finishRow) throws PVZException{
        super(row, col,finishRow);
        name = "sun";
        value = 25;
    }
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
}
