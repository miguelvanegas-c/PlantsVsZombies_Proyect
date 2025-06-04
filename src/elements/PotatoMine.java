import java.io.Serializable;

public class PotatoMine extends Plant implements Attacker, Serializable {
    private int index = 0;

    public PotatoMine(int row, int col) {
        super(row, col);
        name = "potatoMineDesactive";
        value = 25;
        life = 100;
        width = 20;
        height = 20;
        xPosition += 25;
        yPosition += 35;

    }

    public void makeDamage(Element element) {
        if (index >= 71) {
            return;
        }
        if (index == 70) {
            name ="potatoMine";
            width = 50;
            height = 50;
            xPosition -= 25;
            yPosition -= 35;
        }
        this.index ++;
    }

    public boolean getFlag() {
        if (index >= 70) {
            return true;
        }
        return false;
    }

    public void makeBoom(){
        life = -173;
    }


    @Override
    public void takeDamage(int damage){
        if (getFlag()) {
            makeBoom();
        } else {
            super.takeDamage(damage);
        }
    }

}
