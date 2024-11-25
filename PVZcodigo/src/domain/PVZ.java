package domain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class PVZ{
    public final int filas = 5;
    public final int columnas = 10;
    private List<Thing>[][] tablero = new ArrayList[filas][columnas];
    private boolean tipoDeZombie;
    private boolean tipoDePlanta;
    private String[] zombiesDeJuego;
    private String[] plantasDeJuego;
    private List<Zombie>[][] zombiesTablero = new ArrayList[filas][columnas];
    private Plant[][] plantasTablero = new Plant[filas][columnas];
    private Moneda[][] monedas = new Moneda[filas][columnas];

    public PVZ(String[] plantasDeJuego, String[] zombiesDeJuego, boolean tipoDeZombie) {
        this.plantasDeJuego = plantasDeJuego;
        this.zombiesDeJuego = zombiesDeJuego;
        this.tipoDeZombie = tipoDeZombie;
        inicializarZombiesTablero();

    }

    public List<Thing>[][] getTablero(){
        return tablero;
    }
    public boolean getTipoDeZombie(){
        return tipoDeZombie;
    }
    public boolean getTipoDePlanta(){
        return tipoDePlanta;
    }
    public String[] getZombiesDeJuego(){
        return zombiesDeJuego;
    }
    public String[] getPlantasDeJuego(){
        return plantasDeJuego;
    }
    public List<Zombie>[][] getZombiesTablero(){
        return zombiesTablero;
    }
    public Plant[][] getPlantasTablero(){
        return plantasTablero;
    }

    private void inicializarZombiesTablero(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                zombiesTablero[i][j] = new ArrayList<>();
            }
        }
    }


    public void moveZombies() {
        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                int len = zombiesTablero[row][col].size();
                for (int i = 0; i < len; i++) {
                    Zombie zombie = zombiesTablero[row][col].get(i);
                    zombie.move();
                    int x = zombie.getXPosition();
                    if(((x-140)%70)==0){
                        zombiesTablero[row][col].remove(zombie);
                        zombiesTablero[row][col-1].add(zombie);
                        if(col-1 == 0)zombiesTablero[row][col - 1].remove(zombie);
                    }
                }
            }
        }
    }


    public void addZombie(int row, String zombie){
        Zombie newZombie = null;
        if(zombie.equals("zombie")){
            newZombie = new BasicZombie(row);
            zombiesTablero[row][9].add(newZombie);
            Thing newZombieThing = (Thing) newZombie;
            if (tablero[row][9] == null) tablero[row][9] = new ArrayList<>();
            tablero[row][9].add(newZombieThing);

        }
    }


    public void addPlant(int row, int col, String planta)  throws PVZException{
        valideCanPlant(row,col);
        valideCeldaVacia(row,col);
        Plant plantaNueva = null;
        if (planta.equals("peashooter")) plantaNueva = new Peashooter(row, col);
        plantasTablero[row][col] = plantaNueva;
        Thing plantaNuevaThing = (Thing) plantaNueva;
        if (tablero[row][col] == null) {tablero[row][col] = new ArrayList<>();}
        tablero[row][col].add(plantaNuevaThing);
    }

    public void valideCanPlant(int row, int col) throws PVZException{
        if(col == 0 || col == columnas-1 ) throw new PVZException(PVZException.ERROR_CANT_PLANT);
    }
    public void valideCeldaVacia(int row, int col) throws PVZException{
        if(plantasTablero[row][col] != null) throw new PVZException(PVZException.ERROR_CELDA_NOT_EMPTY);
    }

    public void empiezaElJuego(){
        Timer timerGneracionDeZombies = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generacionDeZombies();
            }
        });
        timerGneracionDeZombies.start();
        Timer timerOrdaDeZombies = new Timer(60000, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for(int i = 0; i < 10; i++) generacionDeZombies();
            }
        });
        timerOrdaDeZombies.start();
        Timer timerMove = new Timer(400, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                moveZombies();
            }
        });
        timerMove.start();

    }

    private void generacionDeZombies() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(5) ;
        addZombie(numeroAleatorio, "zombie");
    }


}
