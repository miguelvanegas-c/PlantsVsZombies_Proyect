
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
    public void shouldCreatePVZToPvsM() {
        String[] plantasDeJuegoTest = {"peashooter,sunflower"};
        String[] zombiesDeJuegoTest = {"zombieCono,zombie"};
        assertArrayEquals(plantasDeJuegoTest,pvz.getPlantasDeJuego());
        assertArrayEquals(zombiesDeJuegoTest,pvz.getZombiesDeJuego());
    }

    @Test
    public void shouldMoveZombies() {

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
        if(pvz.getPlantasTablero()[1][1] instanceof Peashooter)testPlanta = "peashooter";
        if(pvz.getTablero()[1][1].getFirst() instanceof Peashooter)testTwoPlanta = "peashooter";
        assertEquals(testPlanta,"peashooter");
        assertEquals(testTwoPlanta,"peashooter");

    }


}
