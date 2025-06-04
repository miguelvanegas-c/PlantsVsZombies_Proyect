import java.io.Serializable;

public class LawnMower implements Element,Mover,Attacker, Serializable {
    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;
    protected int value;
    protected int row;
    protected int col;
    protected int life;
    protected int width = 50;
    protected int height = 50;
    protected String extension = "G.png";
    protected boolean attacker = false;
    protected int damage;

    public LawnMower(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
        this.row = row;
        this.col = col;
        this.life = 1;
        damage = 10000;
        name = "lawnMower";

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

    public void move(){
        if(attacker) {
            xPosition += 14;
            if (((xPosition - 140) % 70) == 0) {
                col++;
                if (col == 10) {
                    col = 9;
                    life = -1;
                }
            }
        }
    }
    @Override
    public void makeDamage(Element element) {
        attacker = true;
        element.takeDamage(damage);
    }
}
