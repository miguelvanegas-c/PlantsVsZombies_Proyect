package domain;

public class BasicZombie extends Thing implements Zombie{
    private String name = "zombie";

    public BasicZombie(int row) {
        super(row,10);
    }

    @Override
    public void move() {
        xPosition -= 7;
    }

    @Override
    public void atacar() {
        // Implementación básica del ataque
    }

    @Override
    public int getXPosition(){
        return xPosition;
    }

    @Override
    public int getYPosition(){
        return yPosition;
    }
}