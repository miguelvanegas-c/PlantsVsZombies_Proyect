import java.io.Serializable;

public class ECIZombie extends Zombie implements Mover,Shooter{
    public ECIZombie(int row){
        super(row,9);
        name = "eciZombie";
        life = 200;
        value = 250;
        damage = 0;
    }
}
