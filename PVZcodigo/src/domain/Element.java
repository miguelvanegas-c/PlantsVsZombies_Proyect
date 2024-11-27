
/**
 * This is abstract class with behaviours that share all the ele.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Element {
    protected int vida = 100;
    protected boolean visible = false;
    protected int xPosition = 140;
    protected int yPosition = 55;

    /**
     * Constructor of the class Element.
     * @param row, row where the element is going to appear.
     * @param col, col where the element is going to appear.
     */
    public Element(int row, int col){
        xPosition += (col*70);
        yPosition += (row*75);
    }

    public void getDamage(int damage){}

    public void makeVisible(){
        visible = true;
    }

    public void makeInvisible(){
        visible = false;
    }
}
