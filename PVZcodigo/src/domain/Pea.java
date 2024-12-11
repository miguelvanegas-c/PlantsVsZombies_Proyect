public class Pea extends Missile{
    public Pea(int xInitial,int yInitial,int row, int col){
        super(xInitial,yInitial,row, col);
        name = "pea";
        damage = 20;
    }

    @Override
    public void move(){
        xPosition += 5;
        if(((xPosition - 140)%70)==0){
            col ++;
        }
    }

    public void makeDamage(Zombie zombie){
        zombie.takeDamage(damage);
        life = 0;
    }
}
