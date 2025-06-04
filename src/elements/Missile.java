import java.io.Serializable;

public abstract class Missile implements Element,Mover,Attacker, Serializable {
    protected String name;
    protected int xPosition ;
    protected int yPosition ;
    protected int row;
    protected int col;
    protected int damage;
    protected int width;
    protected int height;
    protected int life = 1;
    protected String extension;

    public Missile(int xInitial,int yInitial,int row, int col){
        this.xPosition = xInitial + 30;
        this.yPosition = yInitial;
        this.row = row;
        this.col = col;
        this.width = 30;
        this.height = 30;
        this.extension ="G.png";

    }
    public int getRow(){return row;}
    public int getCol(){return col;}
    public abstract void move();
    public int getXPosition() {return xPosition;}
    public int getYPosition() {return yPosition;}
    public String getName() {return name;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getLife() {return life;}
    public void makeDamage(Element element){
        element.takeDamage(damage);
        life = -1;
    }
    public void takeDamage(int damage){}
    public String getExtension() {return extension;}
}
