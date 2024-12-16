public class Boom implements Element{
    protected String name;
    private int index;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int value;
    protected int row;
    protected int col;
    protected int life;
    protected int width = 80;
    protected int height = 80;
    protected String extension = "G.png";

    public Boom(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
        this.row = row;
        this.col = col;
        this.life = 1;
        name = "boom";

    }

    @Override
    public String getName() {
        index++;
        if (index == 2){
            life = -1;
        }
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


    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void takeDamage(int damage) {
    }

}
