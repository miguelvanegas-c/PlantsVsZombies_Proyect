package domain;

public abstract class Thing {
    protected int vida = 100;
    protected boolean visible = false;
    protected int xPosition = 140;
    protected int yPosition = 55;

    public Thing(int row, int col){
        xPosition += (col*70);
        yPosition += (row*75);
    }
    public void makeVisible(){
        visible = true;
    }
    public void makeInvisible(){
        visible = false;
    }
}
