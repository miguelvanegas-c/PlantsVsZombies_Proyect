import java.io.Serializable;

public class POOmBa extends Missile implements Serializable {

    public POOmBa(int xInitial,int yInitial,int row, int col){
        super(xInitial,yInitial,row, col);
        xPosition -= 30 ;
        name = "POOmBa";
        damage = 50;
    }

    @Override
    public void move() {
        xPosition -= 7;
        if(((xPosition - 140)%70)==0){
            col --;
            if (col == 0) {
                col = 1;
                life = -1;
            }
        }
    }
}
