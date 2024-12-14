import java.io.Serializable;

public class ECIZombie extends Zombie implements Mover,Shooter{
    private int index;
    public ECIZombie(int row){
        super(row,9);
        name = "eciZombie";
        life = 200;
        value = 250;
        damage = 0;
    }
    public Missile shoot(){
        if(index == 16){
            index = 0;
            return new POOmBa(xPosition,yPosition,row,col);
        }
        index ++;
        return null;
    }
    @Override
    public void move(){
    }

}
