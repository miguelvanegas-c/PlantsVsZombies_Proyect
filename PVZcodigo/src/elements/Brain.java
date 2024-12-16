public class Brain extends Coin{
    public Brain(int row,int col,int finishRow) throws PVZException{
        super(row, col,finishRow);
        name = "brain";
        value = 50;
        width = 30;
        height = 30;
    }

}
