package domain;

public interface Zombie {

    default void move(){
    }

    default void atacar(){
    }

    default int getXPosition(){
        return 0;
    }

    default int getYPosition(){
        return 0;
    }
}
