public class Brainstein extends Zombie{
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
    }
}
