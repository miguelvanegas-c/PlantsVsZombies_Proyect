import java.io.Serializable;

public class ECIZombie extends Zombie implements Mover,Shooter{
    public ECIZombie(int row,int col){
        super(row,col);
        name = "eciZombie";
        life = 200;
        value = 250;
        damage = 0;
    }
}
