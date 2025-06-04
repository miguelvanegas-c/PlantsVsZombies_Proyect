import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ZombieOriginal implements Machine, Serializable {
    private PVZ pvz;
    private Timer generateZombie;
    private List<String> zombiesCanUse;


    public ZombieOriginal(PVZ pvz) {
        this.pvz = pvz;
        zombiesCanUse = Arrays.asList(pvz.getZombiesInGame());
        zombiesCanUse.remove("brainstein");
        timerZombies();
    }

    private void timerZombies(){
        generateZombie = new Timer(20000,e -> addZombie());
        generateZombie.start();
    }
    @Override
    public void finishGame() {
        generateZombie.stop();
        pvz = null;
    }
    private void addZombie() {
        try {
            Random rand = new Random();
            int randomNum = rand.nextInt(zombiesCanUse.size());
            String zombieName = zombiesCanUse.get(randomNum);
            int randomRow = rand.nextInt(5);
            pvz.addZombie(randomRow, zombieName);
        }catch (PVZException e) {
            System.out.println(e.getMessage());
        }
    }
}
