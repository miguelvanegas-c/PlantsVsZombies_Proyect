
/**
 * This is a peashooter, a plant that shot to the zombies on his row..
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */


public class Peashooter extends Element implements Plant {
    private String name = "peashooter";

    public Peashooter(int row,int col) {
        super(row,col);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getXPosition() {
        return xPosition;
    }

    @Override
    public int getYPosition() {
        return yPosition;
    }
}
