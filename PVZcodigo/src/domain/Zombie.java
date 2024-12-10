
/**
 * This is an interface to all the zombies.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
<<<<<<< HEAD
<<<<<<< HEAD
public abstract class Zombie implements Element, Mover, Attacker {

    protected String state;
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
=======
public abstract class Zombie implements Element {
=======
public abstract class Zombie implements Element, Mover, Attacker {
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b

    protected String name;
    protected int life;
    protected int xPosition = 140;
<<<<<<< HEAD
    protected int yPosition = 55;
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
    protected int yPosition = 40;
    protected int value;
    protected int row;
    protected int col;
    protected int width;
    protected int height;
    protected String extension ="G.png";
    protected int damage = 100;
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b

    /**
     * Constructor to zombies.
     * @param row, row of the zombie
     * @param col, col of the zombie
     */
    public Zombie(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
<<<<<<< HEAD
<<<<<<< HEAD
        this.row = row;
        this.col = col;
        name = "zombie";
        state = "normal";
=======
        this.row = row;
        this.col = col;
        name = "zombie";
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
        life = 100;
        value = 100;
        width = 80;
        height = 70;
<<<<<<< HEAD
    }
    public String getName(){return this.name;}
    public String getState(){return this.state;}

=======
    }
    public String getName(){return this.name;}
    public abstract void move();
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
    }
    public String getName(){return this.name;}

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    public int getValue(){return value;}
    public int getRow() {return row;}
    public int getCol() {return col;}
    public int getLife() {return life;}
    public void move(){
        xPosition -= 5;
        if(((xPosition - 140)%70)==0)col-=1;
<<<<<<< HEAD
=======

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    }
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getExtension() {return extension;}
<<<<<<< HEAD
    public void takeDamage(int damage){
        life -= damage;
    }
=======
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b


>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
}
