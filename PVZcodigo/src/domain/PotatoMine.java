public class PotatoMine extends Plant implements Attacker{

    public PotatoMine(int row, int col) {
        super(row, col);
        name = "potatoMine";
        value = 25;
        life = 100;
    }
<<<<<<< HEAD

    @Override
    public int attack() {
        return 1000;
    }
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
}
