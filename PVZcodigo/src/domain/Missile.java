public abstract class Missile implements Attacker,Mover{
    protected String name;
    protected int xPosition;
    protected int yPosition;
    protected int row;
    protected int damage;
    protected int width;
    protected int height;
    protected String extension;

    public int attack(){return damage;}
    public int getRow(){return xPosition;}
    public int getCol(){return yPosition;}
    public void move(){}
    public int getXPosition() {return xPosition;}
    public int getYPosition() {return yPosition;}
    public String getName() {return name;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getExtension() {return extension;}
}
