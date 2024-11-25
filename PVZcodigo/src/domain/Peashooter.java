package domain;

import java.io.Serializable;

public class Peashooter extends Thing implements Plant {
    private String name = "peashooter";
    public Peashooter(int row,int col) {
        super(row,col);

    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getXPosition() {
        return xPosition;
    }

    @Override
    public int getYPosition() {
        return yPosition;
    }
}
