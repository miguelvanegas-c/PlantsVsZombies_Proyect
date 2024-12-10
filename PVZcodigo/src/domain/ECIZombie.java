import java.io.Serializable;

public class ECIZombie extends Zombie implements Mover,Shooter{
    public ECIZombie(int row,int col){
        super(row,col);
        name = "eciZombie";
        life = 200;
        value = 250;
        damage = 0;
    }
<<<<<<< HEAD

    @Override
    public int attack() {
        return 0;
    }
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
}
