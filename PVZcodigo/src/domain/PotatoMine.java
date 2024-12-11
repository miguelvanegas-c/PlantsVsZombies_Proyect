public class PotatoMine extends Plant {
    private int damage =1000;
    private boolean[] flag = {false,false,false,false,false,false,false,false,false,false,false,false,false,true};
    private int index = 0;
    public PotatoMine(int row, int col) {
        super(row, col);
        name = "potatoMine";
        value = 25;
        life = 100;
    }

    public void makeDamage(Zombie zombie) {
        if(flag[index]) {
            zombie.takeDamage(damage);
        }else {
            index++;
        }
    }

}
