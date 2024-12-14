public class Sunflower extends  Plant implements GenerateCoins{
    protected int index;
    public Sunflower(int row, int col) {
        super(row,col);
        name = "sunflower";
        life = 300;
        value = 50;
    }

    @Override
    public void makeCoins(PVZ game) throws PVZException {
        if( index == 100) {
            game.addCoin(getRow(), getCol(), getRow(), "sun");
            index = 0;
            return;
        }
        index ++;
    }

}
