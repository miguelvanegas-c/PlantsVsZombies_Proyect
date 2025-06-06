import java.io.Serializable;

/**
 * This is an interface to all the zombies.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Zombie implements Element, Mover,Attacker, Serializable {

    protected String name;
    protected int life;
    protected int xPosition = 140;
    protected int yPosition = 40;
    protected int value;
    protected int row;
    protected int col;
    protected int width;
    protected int height;
    protected String extension ="G.png";
    protected int damage = 100;
    protected boolean inAttack = false;
    protected int index;


    /**
     * Constructor to zombies.
     * @param row, row of the zombie
     * @param col, col of the zombie
     */
    public Zombie(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
        this.row = row;
        this.col = col;
        name = "zombie";
        life = 100;
        value = 100;
        width = 80;
        height = 70;
    }
    public String getName(){return this.name;}

    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getValue(){return value;}
    public int getRow() {return row;}
    public int getCol() {return col;}
    public int getLife() {return life;}
    public void move(){
        if(!inAttack) {
            xPosition -= 1;
            if (((xPosition - 140) % 70) == 0) col -= 1;
            name = "zombie";
        }
        inAttack = false;

    }
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getExtension() {return extension;}
    public void takeDamage(int damage){
        life -= damage;
    }
    public void makeDamage(Element element){
        if (index == 5) {
            if (element instanceof PotatoMine p) {
                if (p.getFlag()) {
                    life = -10;
                }
            }
            element.takeDamage(damage);
            index = 0;
        }
        index ++;
    }
    public void makeAttack(){

        inAttack = true;
        name = "zombieComiendo";

    }


}
