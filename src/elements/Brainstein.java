import java.io.Serializable;

public class Brainstein extends Zombie implements GenerateCoins, Serializable {

    private int index;
    /**
     * Constructor to zombies.
     *
     * @param row
     */
    public Brainstein(int row) {
        super(row, 9);
        name = "brainstein";
        life = 300;
        value = 50;
        width = 50;
        height = 50;
    }

    @Override
    public void makeCoins(PVZ game) throws PVZException {
        if( index == 200) {
            game.addCoin(getRow(), getCol(), getRow(), "brain");
            index = 0;
            return;
        }
        index ++;
    }

    @Override
    public void move(){

    }
}
