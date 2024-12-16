import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantIntelligent implements Machine{
    protected PVZ pvz;
    protected List<String> plantsCanUse;
    protected Timer generatePlants;
    protected int actualCol;
    protected int actualRow;
    protected Map<Integer,String> mapPlants = new HashMap<Integer,String>();
    protected int wallnutIndex;

    public PlantIntelligent(PVZ pvz) {
        this.pvz = pvz;
        plantsCanUse = Arrays.asList(pvz.getPlantsInGame());
        int index = 1;
        if (plantsCanUse.contains("eciPlant")) {
            mapPlants.put(index,"eciPlant");
            index ++;
        }
        if (plantsCanUse.contains("sunflower")) {
            mapPlants.put(index,"sunflower");
            index ++;
        }
        if (plantsCanUse.contains("peashooter")) {
            mapPlants.put(index,"peashooter");
            index ++;
        }
        if (plantsCanUse.contains("wallnut")) {
            mapPlants.put(index,"wallnut");
            index ++;
            wallnutIndex = index;
        }
        if (plantsCanUse.contains("potatoMine")) {
            mapPlants.put(index,"potatoMine");
            index ++;
        }

        generatePlants();
    }
    protected void generatePlants() {
        generatePlants = new Timer(10000,e -> actionPlant());
        generatePlants.start();
    }

    protected void addPlant() {
        setActualColAndRow();
        try {
            while(actualCol != 0){
                pvz.addPlant(actualRow,actualCol,mapPlants.get(actualCol));
                setActualColAndRow();
            }

        } catch (PVZException e) {
            //No lo tratamos
        }
    }
    private void actionPlant(){
        takeCoins();
        addPlant();

    }

    private void takeCoins() {
        try {
            for(int col = 1; col <= 2 ; col++) {
                for (int row = 0; row < PVZ.rows; row++) {
                    pvz.takeCoin(row, col);
                }
            }
        } catch (PVZException e) {
            //
        }
    }


    protected void setActualColAndRow() {
        Plant[][] board = pvz.getPlantsBoard();
        for(int col = 1; col < mapPlants.size() + 1; col++) {
            for(int row = 0; row < PVZ.rows; row++) {
                if( board[row][col] == null){
                    actualCol = col;
                    actualRow = row;
                    return;
                }
            }
        }
        actualCol = 0;
        actualRow = 0;
    }


    @Override
    public void finishGame() {
        generatePlants.stop();
    }
}
