
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PVZTest {
    private String[] plantasDeJuego;
    private String[] zombiesDeJuego;
    private PVZ pvz;

    @Before
    public void setUp() throws Exception {
        String[] plantasDeJuego = {"peashooter,sunflower"};
        String[] zombiesDeJuego = {"zombieCono,zombie"};
        this.plantasDeJuego = plantasDeJuego;
        this.zombiesDeJuego = zombiesDeJuego;
        pvz = new PVZ(this.plantasDeJuego,this.zombiesDeJuego, true);
    }
    @Test
    public void shouldCreatePVZ() {
        String[] plantasDeJuegoTest = {"peashooter,sunflower"};
        String[] zombiesDeJuegoTest = {"zombieCono,zombie"};
        assertArrayEquals(plantasDeJuegoTest,pvz.getPlantsInGame());
        assertArrayEquals(zombiesDeJuegoTest,pvz.getZombiesInGame());
    }

    @Test
    public void shouldMoveZombies() {
        pvz.addZombie(3, "zombie");
        Zombie zombie = pvz.getZombiesBoard()[3][9].getFirst();
        for (int i = 0; i < 80; i++) {
            pvz.moveZombies();
        }
        assertNotNull(pvz.getZombiesBoard()[3][8].getFirst());
        assertEquals(zombie,pvz.getZombiesBoard()[3][8].getFirst());
    }

    @Test
    public void shoulldAddPlant(){
        try {
            pvz.addPlant(1, 1, "peashooter");
        }catch (PVZException e){
            fail();
        }
        String testPlanta = null;
        String testTwoPlanta = null;
        if(pvz.getPlantsBoard()[1][1] instanceof Peashooter)testPlanta = "peashooter";
        if(pvz.getBoard()[1][1].getFirst() instanceof Peashooter)testTwoPlanta = "peashooter";
        assertEquals(testPlanta,"peashooter");
        assertEquals(testTwoPlanta,"peashooter");

    }


}
