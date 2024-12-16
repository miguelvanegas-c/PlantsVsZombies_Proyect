import javax.swing.*;
import java.io.Serializable;

public class MvsM extends GameMode implements Serializable {

    private Timer suns;

    public MvsM(boolean zombieType,int gameTime,int hordesNumber, int hordesTime,PVZ pvz){
        super(zombieType, gameTime, hordesNumber, hordesTime, pvz);
        suns = new Timer(10000, e -> setSuns());
        suns.start();
    }
    private void setSuns(){
        pvz.setSuns(25);
    }


    @Override
    public void finishGame() {
        timePerHordes.stop();
        if(hordeDuration.isRunning()) hordeDuration.stop();
        suns.stop();
        pvz = null;
    }
}
