public class POOmBa extends Missile{

    public POOmBa(int xInitial,int yInitial,int row, int col){
        super(xInitial,yInitial,row, col);
        xPosition -= 30 ;
        name = "POOmBa";
        damage = 50;
    }

    @Override
    public void move() {
        xPosition -= 14;
        if(((xPosition - 140)%70)==0){
            col --;
            if (col == 0) {
                col = 1;
                life = -1;
            }
        }
    }
}
