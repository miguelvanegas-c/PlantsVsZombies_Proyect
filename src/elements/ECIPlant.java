import java.io.Serializable;

public class ECIPlant extends Sunflower implements Serializable {

    public ECIPlant(int row, int col) {
        super(row, col);
        name = "eciPlant";
        value = 75;
        life = 150;
    }
    @Override
    public void makeCoins(PVZ game) throws PVZException {
        if( index == 200) {
            game.addCoin(getRow(), getCol(), getRow(), "eciSun");
            index = 0;
            return;
        }
        index ++;
    }

}
