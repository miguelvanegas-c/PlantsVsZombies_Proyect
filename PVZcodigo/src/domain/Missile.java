public abstract class Missile implements Element,Mover{
    protected String name;
    protected int xPosition ;
    protected int yPosition ;
    protected int row;
    protected int col;
    protected int damage;
    protected int width;
    protected int height;
    protected int life;
    protected String extension;

    public Missile(int xInitial,int yInitial,int row, int col){
        this.xPosition = xInitial + 70;
        this.yPosition = yInitial;
        this.row = row;
        this.col = col;
        this.width =20;
        this.height =20;
        this.extension ="G.png";
        life = 1;
    }
    public int attack(){return damage;}
    public int getRow(){return xPosition;}
    public int getCol(){return yPosition;}
    public void move(){}
    public int getXPosition() {return xPosition;}
    public int getYPosition() {return yPosition;}
    public String getName() {return name;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getLife() {return life;}
    public String getExtension() {return extension;}
}
