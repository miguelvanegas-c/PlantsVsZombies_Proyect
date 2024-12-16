public class PlantsStrategic extends PlantIntelligent{

    public PlantsStrategic(PVZ pvz) {
        super(pvz);
    }
    @Override
    protected void addPlant() {
        setActualColAndRow();
        try {
            while(actualCol != 0){
                if(actualCol == wallnutIndex && pvz.getSuns() >= 500){
                    mapPlants.remove(wallnutIndex);
                    mapPlants.put(wallnutIndex,"peashooter");
                    mapPlants.remove(wallnutIndex + 1);
                    mapPlants.put(wallnutIndex + 1,"wallnut");
                    mapPlants.put(wallnutIndex + 2,"potatoMine");

                }else {
                    pvz.addPlant(actualRow, actualCol, mapPlants.get(actualCol));
                }
                setActualColAndRow();
            }

        } catch (PVZException e) {
            //No lo tratamos
        }
    }


    @Override
    public void finishGame() {

    }
}
