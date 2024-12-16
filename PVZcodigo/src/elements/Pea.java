public class Pea extends Missile{

    public Pea(int xInitial,int yInitial,int row, int col){
        super(xInitial,yInitial,row, col);
        name = "pea";
        damage = 20;
    }

    @Override
    public void move(){
        xPosition += 7;
        if(((xPosition - 170)%70)==0){
            col ++;
            if (col == 10) {
                col = 9;
                life = -1;
            }
        }
    }



}
