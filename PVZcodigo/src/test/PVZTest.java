
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
        pvz = new PVZ(this.plantasDeJuego,this.zombiesDeJuego,100000,100000,200);
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
        PVZ pvzTest = new PVZ(plantasDeJuego,this.zombiesDeJuego,true,10,10,10,2,2);
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
    public void shouldAddZombieWithoutBrains(){
        int startingBrains = pvz.getBrains();
        try {
            pvz.addZombie(3, "zombie");
            pvz.addZombie(3, "zombieCono");
            pvz.addZombie(3,"zombieBalde");
            pvz.addZombie(3,"eciZombie");
            pvz.addZombie(3,"brainstein");
        } catch (PVZException e) {
            fail();
        }
        assertEquals(5, pvz.getBoard()[3][9].size());
        assertTrue(pvz.getBoard()[3][9].get(4) instanceof Brainstein);
        assertTrue(pvz.getBoard()[3][9].get(3) instanceof ECIZombie);
        assertTrue(pvz.getBoard()[3][9].get(2) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(1) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(0) instanceof Zombie);
        assertEquals(startingBrains,pvz.getBrains());
    }
    @Test
    public void shouldAddZombieWithBrains(){
        try {
            pvz.addZombie("zombie",3);
            pvz.addZombie( "zombieCono",3);
            pvz.addZombie("zombieBalde", 3);
            pvz.addZombie("eciZombie", 3);
            pvz.addZombie("brainstein", 3);
        } catch (PVZException e) {
            fail();
        }
        assertEquals(5, pvz.getBoard()[3][9].size());
        assertTrue(pvz.getBoard()[3][9].get(4) instanceof Brainstein);
        assertTrue(pvz.getBoard()[3][9].get(3) instanceof ECIZombie);
        assertTrue(pvz.getBoard()[3][9].get(2) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(1) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(0) instanceof Zombie);

    }

    @Test
    public void shouldNotRemoveZombieBecauseZombiestillhasLife(){
        try {
            pvz.addZombie(3, "eciZombie");
        } catch (PVZException e) {
            fail();
        }
        pvz.deleteZombie(3,9);
        assertFalse(pvz.getBoard()[3][9].isEmpty());
    }

    @Test
    public void shouldNotRemoveZombie(){
        try {
            pvz.addZombie(3, "zombie");
            pvz.addPlant(3,1,"peashooter");
            pvz.addPlant(3,5,"potatoMine");
        } catch (PVZException e) {
            fail();
        }
        for(int i = 0; i < 300; i++){
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
        }
        pvz.deleteZombie(3,5);
        assertTrue(pvz.getBoard()[3][5].size() == 1);
        assertTrue(pvz.getBoard()[3][5].get(0) instanceof PotatoMine);
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
            pvz.addPlant(0, 1, "peashooter");
            pvz.addPlant(1, 1, "sunflower");
            pvz.addPlant(2, 1, "wallnut");
            pvz.addPlant(3,1,"eciPlant");
            pvz.addPlant(4,1,"potatoMine");
        }catch (PVZException e){
            fail();
        }
        assertTrue(pvz.getBoard()[0][1].getFirst() instanceof Peashooter);
        assertTrue(pvz.getBoard()[1][1].getFirst() instanceof Sunflower);
        assertTrue(pvz.getBoard()[2][1].getFirst() instanceof Wallnut);
        assertTrue(pvz.getBoard()[3][1].getFirst() instanceof ECIPlant);
        assertTrue(pvz.getBoard()[4][1].getFirst() instanceof PotatoMine);
        assertTrue(pvz.getPlantsBoard()[0][1] instanceof Peashooter);
        assertTrue(pvz.getPlantsBoard()[1][1] instanceof Sunflower);
        assertTrue(pvz.getPlantsBoard()[2][1] instanceof Wallnut);
        assertTrue(pvz.getPlantsBoard()[3][1] instanceof ECIPlant);
        assertTrue(pvz.getPlantsBoard()[4][1] instanceof PotatoMine);
    }

    @Test
    public void shouldThrowPVZExceptionExistPlantToDelete(){
        try{
            pvz.deletePlant(1,4);
        }catch (PVZException e){
            assertEquals("There isn´t a plant in this position", e.getMessage());
        }
    }

    @Test
    public void shouldDeletePlant(){
        try{
            pvz.addPlant(1,4,"peashooter");
            pvz.deletePlant(1,4);
        }catch (PVZException e){
            fail();
        }
        assertTrue(pvz.getBoard()[1][4].isEmpty());
        assertNull(pvz.getPlantsBoard()[1][4]);
    }

    @Test
    public void shouldThrowPVZExceptionCantPlant(){
        try {
            pvz.addPlant(3, 0,"peashooter");
        } catch (PVZException e) {
            assertEquals("Can't plant on this cell", e.getMessage());
        }
        assertEquals(1,pvz.getBoard()[3][0].size());
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
        assertEquals(1,pvz.getBoard()[0][0].size());
    }

    @Test
    public void shouldTakeCoin(){
        try {
            pvz.addCoin(3,3,3, "sun");
            pvz.addCoin(3,3,3, "brain");
            pvz.addCoin(3,3,3, "eciSun");
            assertEquals(3, pvz.getBoard()[3][3].size());
            assertTrue(pvz.getBoard()[3][3].getFirst() instanceof Sun);
            assertTrue(pvz.getBoard()[3][3].get(1) instanceof Brain);
            assertTrue(pvz.getBoard()[3][3].get(2) instanceof ECISun);
            assertFalse(pvz.isEmptyOfCoins(3,3));
            pvz.takeCoin(3,3);
            pvz.takeCoin(3,3);
            pvz.takeCoin(3,3);
        } catch (PVZException e) {
            fail();
        }
        assertTrue(pvz.getBoard()[3][3].isEmpty());
        assertTrue(pvz.isEmptyOfCoins(3,3));

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
        for (int i = 0; i < 80; i++) {
            pvz.moveBoard();
        }
        assertNotNull(pvz.getBoard()[3][8].getFirst());
        assertEquals(zombie,pvz.getBoard()[3][8].getFirst());
    }

    @Test
    public void shouldEciZombieThrowPOOmBaAndKillPlants(){
        try {
            pvz.addZombie(3, "eciZombie");
            pvz.addPlant(3,4,"sunflower");
            pvz.addPlant(3,5,"wallnut");
            pvz.addPlant(3,6,"potatoMine");
        } catch (PVZException e) {
            fail();
        }
        for(int i = 0; i < 10000; i++){
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
        }
        assertNull(pvz.getPlantsBoard()[3][4]);
        assertNull(pvz.getPlantsBoard()[3][5]);
        assertNull(pvz.getPlantsBoard()[3][6]);
    }

    @Test
    public void shouldLawnMowerKillZombies(){
        try {
            pvz.addZombie(3, "zombie");
            pvz.addZombie(3, "zombieCono");
            pvz.addZombie(3,"zombieBalde");
            pvz.addZombie(3,"eciZombie");
            pvz.addZombie(3,"brainstein");
        } catch (PVZException e) {
            fail();
        }
        assertEquals(5, pvz.getBoard()[3][9].size());
        assertTrue(pvz.getBoard()[3][9].get(4) instanceof Brainstein);
        assertTrue(pvz.getBoard()[3][9].get(3) instanceof ECIZombie);
        assertTrue(pvz.getBoard()[3][9].get(2) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(1) instanceof ZombieWithShield);
        assertTrue(pvz.getBoard()[3][9].get(0) instanceof Zombie);
        for(int i = 0; i < 700; i++){
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
        }
        for(int col = 0; col < 10; col++) {
            assertTrue(pvz.getBoard()[3][col].isEmpty());
        }
        assertFalse(pvz.gameIsFinished());
    }

    @Test
    public void shouldZombiesWinTheGame(){
        try {
            pvz.addZombie(3, "zombie");
        } catch (PVZException e) {
            fail();
        }
        for(int i = 0; i < 700; i++){
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
        }
        for(int col = 0; col < 10; col++) {
            assertTrue(pvz.getBoard()[3][col].isEmpty());
        }
        assertFalse(pvz.gameIsFinished());
        try {
            pvz.addZombie(3, "zombie");
        } catch (PVZException e) {
            fail();
        }
        for(int i = 0; i < 700; i++){
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
            pvz.zombieInLastCell();
            if(pvz.gameIsFinished()){
                assertTrue(pvz.gameIsFinished());
                break;
            }
        }


    }

}
