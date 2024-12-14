public class ECIPlant extends Sunflower{

    public ECIPlant(int row, int col) {
        super(row, col);
        name = "eciPlant";
        value = 75;
        life = 150;
    }
    @Override
    public void makeCoins(PVZ game) throws PVZException {
        if( index == 100) {
            game.addCoin(getRow(), getCol(), getRow(), "eciSun");
            index = 0;
            return;
        }
        index ++;
    }

}
