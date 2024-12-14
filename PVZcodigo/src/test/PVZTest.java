
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PVZTest {
    private String[] plantasDeJuego;
    private String[] zombiesDeJuego;
    private PVZ pvz;

    @Before
    public void setUp() {
        String[] plantasDeJuego = {"peashooter,sunflower"};
        String[] zombiesDeJuego = {"zombieCono,zombie"};
        this.plantasDeJuego = plantasDeJuego;
        this.zombiesDeJuego = zombiesDeJuego;
        pvz = new PVZ(this.plantasDeJuego,this.zombiesDeJuego,10,10,200);
    }
    @Test
    public void shouldCreatePVZToPvsP() {
        String[] plantasDeJuegoTest = {"peashooter,sunflower"};
        String[] zombiesDeJuegoTest = {"zombieCono,zombie"};
        int expectedSuns = pvz.getSuns();
        int expectedBrains = pvz.getBrains();
        assertArrayEquals(plantasDeJuegoTest,pvz.getPlantsInGame());
        assertArrayEquals(zombiesDeJuegoTest,pvz.getZombiesInGame());
        assertNotEquals(0, expectedSuns);
        assertNotEquals(0, expectedBrains);
    }

    @Test
    public void shouldCreatePVZToPvsM() {
        PVZ pvzTest = new PVZ(plantasDeJuego,this.zombiesDeJuego,true,10,10,2,2);
        String[] plantasDeJuegoTest = {"peashooter,sunflower"};
        String[] zombiesDeJuegoTest = {"zombieCono,zombie"};
        int expectedSuns = pvzTest.getSuns();
        int expectedBrains = pvzTest.getBrains();
        assertArrayEquals(plantasDeJuegoTest,pvzTest.getPlantsInGame());
        assertArrayEquals(zombiesDeJuegoTest,pvzTest.getZombiesInGame());
        assertNotEquals(0, expectedSuns);
        assertNotEquals(0, expectedBrains);
    }

    @Test
    public void shouldCreatePVZToMvsM() {
        PVZ pvzTest = new PVZ(plantasDeJuego,this.zombiesDeJuego,true,true,10,10,200,2,2);
        String[] plantasDeJuegoTest = {"peashooter,sunflower"};
        String[] zombiesDeJuegoTest = {"zombieCono,zombie"};
        int expectedSuns = pvzTest.getSuns();
        int expectedBrains = pvzTest.getBrains();
        assertArrayEquals(plantasDeJuegoTest,pvzTest.getPlantsInGame());
        assertArrayEquals(zombiesDeJuegoTest,pvzTest.getZombiesInGame());
        assertNotEquals(0, expectedSuns);
        assertNotEquals(0, expectedBrains);
    }

    @Test
    public void shouldAddZombie(){
        try {
            pvz.addZombie(3, "zombie");
            pvz.addZombie(3, "zombieCono");
            pvz.addZombie(3,"zombieBalde");
        } catch (PVZException e) {
            fail();
        }
        assertEquals(3, pvz.getBoard()[3][9].size());
        assertTrue(pvz.getBoard()[3][9].get(2) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(1) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(0) instanceof Zombie);
    }

    @Test
    public void shouldThrowPVZExceptionStringZombieIsNull(){
        try {
            pvz.addZombie(3, null);
        } catch (PVZException e) {
            assertEquals("You don't select a zombie to add", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][9].size());
    }

    @Test
    public void shouldThrowPVZExceptionZombieNotExist(){
        try {
            pvz.addZombie(3, "x");
        } catch (PVZException e) {
            assertEquals("The zombie that do you wanna add doesn't exist", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][9].size());
    }

    @Test
    public void shouldAddPlant(){
        try {
            pvz.addPlant(1, 1, "peashooter");
            pvz.addPlant(2, 1, "sunflower");
            pvz.addPlant(3, 1, "wallnut");
        }catch (PVZException e){
            fail();
        }
        assertTrue(pvz.getBoard()[1][1].getFirst() instanceof Peashooter);
        assertTrue(pvz.getBoard()[2][1].getFirst() instanceof Sunflower);
        assertTrue(pvz.getBoard()[3][1].getFirst() instanceof Wallnut);
        assertTrue(pvz.getPlantsBoard()[1][1] instanceof Peashooter);
        assertTrue(pvz.getPlantsBoard()[2][1] instanceof Sunflower);
        assertTrue(pvz.getPlantsBoard()[3][1] instanceof Wallnut);
    }

    @Test
    public void shouldThrowPVZExceptionCantPlant(){
        try {
            pvz.addPlant(3, 0,"peashooter");
        } catch (PVZException e) {
            assertEquals("Can't plant on this cell", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][0].size());
        assertNull(pvz.getPlantsBoard()[3][0]);
    }

    @Test
    public void shouldThrowPVZExceptionStringPlantIsNull(){
        try {
            pvz.addPlant(3, 1,null);
        } catch (PVZException e) {
            assertEquals("You don't select a plant to add", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][1].size());
        assertNull(pvz.getPlantsBoard()[3][1]);
    }

    @Test
    public void shouldThrowPVZExceptionStringPlantExist(){
        try {
            pvz.addPlant(3, 1,"x");
        } catch (PVZException e) {
            assertEquals("The plant that do you wanna add doesn't exist", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][1].size());
        assertNull(pvz.getPlantsBoard()[3][1]);
    }

    @Test
    public void shouldAddCoin(){
        try {
            pvz.addCoin(0,3,2 ,"sun");
            pvz.addCoin(0,3,3 ,"brain");
        } catch (PVZException e) {
            fail();
        }
        assertEquals(2, pvz.getBoard()[0][3].size());
        assertTrue(pvz.getBoard()[0][3].get(0) instanceof Sun);
        assertTrue(pvz.getBoard()[0][3].get(1) instanceof Brain);
    }

    @Test
    public void shouldThrowPVZExceptionStringCoinIsNull(){
        try {
            pvz.addCoin(3,3,3, null);
        } catch (PVZException e) {
            assertEquals("You don´t select a coin", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][3].size());
    }

    @Test
    public void shouldThrowPVZExceptionCoinNotExist(){
        try {
            pvz.addCoin(3,2,2, "x");
        } catch (PVZException e) {
            assertEquals("The coin that do you wanna add doesn't exist", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[3][2].size());
    }

    @Test
    public void shouldThrowPVZExceptionCoinInBadPosition(){
        try {
            pvz.addCoin(0,0,2, "sun");
        } catch (PVZException e) {
            assertEquals("In this position can't cerate a coin", e.getMessage());
        }
        assertEquals(0,pvz.getBoard()[0][0].size());
    }


    @Test
    public void shouldMoveZombies() {
        try {
            pvz.addZombie(3, "zombie");
        } catch (PVZException e) {
            fail();
        }
        Element zombie = pvz.getBoard()[3][9].getFirst();
        assertTrue(zombie instanceof Zombie);
        for (int i = 0; i < 20; i++) {
            pvz.moveBoard();
        }
        assertNotNull(pvz.getBoard()[3][8].getFirst());
        assertEquals(zombie,pvz.getBoard()[3][8].getFirst());
    }




}
