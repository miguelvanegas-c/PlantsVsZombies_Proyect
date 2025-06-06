

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This is the principal class of the application, it will have the behaviors and functionalities of the whole game.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class PVZ implements Serializable {
    public static final int rows = 5;
    public static final int columns = 10;
    private List<Element>[][] board = new ArrayList[rows][columns];
    private String[] zombiesInGame, plantsInGame;
    private Plant[][] plantsBoard = new Plant[rows][columns];
    private int suns,brains, zombiePoints, plantPoints;
    private List<Mover> moves = new ArrayList();
    private List<Attacker> attackers = new ArrayList();
    private List<Shooter> shooters = new ArrayList();
    private List<Element> elements = new ArrayList();
    private List<GenerateCoins> generateCoins = new ArrayList();
    private boolean gameFinished = false;
    private boolean zombieType;
    private boolean plantType;
    private GameMode gameMode;
    private String game;
    private Machine zombieMachine;
    private Machine plantMachine;
    private Timer gameTimer;



    /**
     * Constructor for the PVZ class MVSM game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
     * @param plantType     Boolean indicating whether plant actions are active.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     * @param hordesNumber  number of hordes to the game
     * @param hordesTime    hordes duration time.
     */
    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,boolean plantType,int startingSuns, int startingBrains , int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        this.game = "MvsM";
        this.zombieType = zombieType;
        this.plantType = plantType;
        startingBoards();
        generateLawnMower();
        startingGame(zombieType,plantType,gameTime,hordesNumber,hordesTime);
        gameTimer = new Timer(gameTime*1000, e -> endGameTwo());
        gameTimer.start();
    }

    /**
     * Constructor for the PVZ class PVSM game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param zombieType    Boolean indicating whether zombie actions are active.
     * @param startingSuns  starting suns to the game.
     * @param gameTime      game time to the game.
     * @param hordesNumber  number of hordes to the game
     * @param hordesTime    hordes duration time.
     */

    public PVZ(String[] plantsInGame, String[] zombiesInGame, boolean zombieType,int startingSuns,int startingBrains, int gameTime , int hordesNumber, int hordesTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        this.game = "PvsM";
        this.zombieType = zombieType;
        startingBoards();
        generateLawnMower();
        startingGame(zombieType,gameTime,hordesNumber,hordesTime);
        gameTimer = new Timer(gameTime*1000, e -> endGame());
        gameTimer.start();
    }

    /**
     * Constructor for the PVZ class PVSP game mode.
     *
     * @param plantsInGame  Array of plants available in the game.
     * @param zombiesInGame Array of zombies available in the game.
     * @param startingSuns  starting suns to the game.
     * @param startingBrains starting brains to the game.
     * @param gameTime      game time to the game.
     */

    public PVZ(String[] plantsInGame, String[] zombiesInGame,int startingSuns, int startingBrains , int gameTime) {
        this.plantsInGame = plantsInGame;
        this.zombiesInGame = zombiesInGame;
        this.suns = startingSuns;
        this.brains = startingBrains;
        this.game = "PvsP";
        startingBoards();
        generateLawnMower();
        startingGame(gameTime);
    }

    /*
     * start game to the PvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( boolean zombieType, int gameTime, int hordesNumber, int hordesTime) {
        gameMode = new PvsM(zombieType,gameTime, hordesNumber, hordesTime, this);
        zombieMachine = (zombieType)? new ZombieOriginal(this):new ZombieStrategic(this);
    }

    /*
     * start game to the MvsM gameMode.
     * @param pvz, the game.
     * @param zombieType, the zombie machine to the game.
     * @param plantType, the plant machine to the game.
     * @param gameTime, the gameTime of the game.
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( boolean zombieType, boolean plantType,int gameTime, int hordesNumber, int hordesTime) {
        gameMode = new MvsM(zombieType,gameTime, hordesNumber, hordesTime,this);
        zombieMachine = (zombieType)? new ZombieOriginal(this):new ZombieStrategic(this);
        plantMachine = (plantType) ? new PlantIntelligent(this):new PlantsStrategic(this);

    }

    /*
     * start game to the PvsP gameMode.
     * @param pvz, the game.
     * @param gameTime, the gameTime of the game
     * @param hordesNumber, number of hordes.
     * @param hordesTime, hordes duration time.
     */
    private void startingGame( int gameTime) {

    }

    //Getters
    public List<Element>[][] getBoard(){
        return board;
    }
    public String[] getZombiesInGame(){
        return zombiesInGame;
    }
    public String[] getPlantsInGame(){
        return plantsInGame;
    }
    public Plant[][] getPlantsBoard(){
        return plantsBoard;
    }
    public List<Mover> getMoves(){
        return moves;
    }
    public int getSuns(){return suns;}
    public int getBrains(){return brains;}
    public List<Element> getElements(){return elements;}
    public int getZombiePoints(){return zombiePoints;}
    public int getPlantPoints(){return plantPoints;}
    public void setBrains(int brains){this.brains+= brains;}
    public void setSuns(int suns){this.suns += suns;}
    public String getGameMode() {return game;}

    /*
     * Initializes the zombie board, setting up empty lists for each cell.
     */

    private void startingBoards(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new ArrayList<>();
                plantsBoard[i][j]= null;
            }
        }
    }

    /**
     * Delete zombie of the board.
     * @param row, row of the zombie.
     * @param col, column of the zombie.
     */
    public void deleteZombie(int row, int col) {
        for(Element element : board[row][col]){
            if(element instanceof Zombie zombie){
                if(zombie.getLife() <= 0) {
                    attackers.remove(zombie);
                    moves.remove(element);
                    board[row][col].remove(zombie);
                    elements.remove(element);
                    if (zombie instanceof Shooter) shooters.remove(zombie);
                    zombiePoints -= zombie.getValue();
                }
                break;
            }
        }
    }

    /**
     * Add a zombie to the board at a specified row and the last column.
     *
     * @param row    The row index where the zombie will be added.
     * @param zombie The type of zombie to be added.
     * @throws PVZException if the zombie is null or doesn't exist.
     */
    public void addZombie(int row, String zombie) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        board[row][9].add(newZombie);
        moves.add(newZombie);
        attackers.add(newZombie);
        if(newZombie instanceof Shooter shooter) {
            shooters.add(shooter);
        }
        if(newZombie instanceof GenerateCoins generateCoin) {
            generateCoins.add(generateCoin);
        }
        elements.add(newZombie);
        zombiePoints += newZombie.getValue();
    }


    /**
     * Add a zombie to the board at a specified row and the last column, with value.
     * @param zombie The type of zombie to be added.
     * @param row    The row index where the zombie will be added.
     * @throws PVZException if the zombie is null or doesn't exist.
     */
    public void addZombie(String zombie, int row) throws PVZException {
        Zombie newZombie = searchZombie(zombie,row);
        valideZombieExist(newZombie);
        if(newZombie.getValue() <= brains) {
            board[row][9].add(newZombie);
            moves.add(newZombie);
            attackers.add(newZombie);
            if(newZombie instanceof Shooter shooter) {
                shooters.add(shooter);
            }
            if(newZombie instanceof GenerateCoins generateCoin) {
                generateCoins.add(generateCoin);
            }
            elements.add(newZombie);
            zombiePoints += newZombie.getValue();
            brains -= newZombie.getValue();
        }else{
            throw new PVZException(PVZException.ERROR_NOT_ENOUGH_BRAINS);
        }
    }

    /*
     * Search zombie that want to add.
     * @param zombie, the string of the zombie.
     * @return Zombie, the Zombie.
     * @throws PVZException if the string is null;
     */
    private Zombie searchZombie(String zombie, int row) throws PVZException {
        valideZombieNotNull(zombie);
        return switch (zombie) {
            case "zombie" -> new BasicZombie(row);
            case "zombieCono" -> {
                Shield cone = new Cone();
                yield new ZombieWithShield(row, cone);
            }
            case "zombieBalde" -> {
                Shield bucket = new Bucket();
                yield new ZombieWithShield(row, bucket);
            }
            case "brainstein" -> new Brainstein(row);
            case "eciZombie" -> new ECIZombie(row);
            default -> null;
        };

    }

    /*
     * Validate that the string of the zombie isn't null.
     * @param zombie, string to validate.
     * @throws PVZException if the string is null.
     */
    private void valideZombieNotNull(String zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_SELECTED);
    }

    /*
     * Validate that the Zombie exist.
     * @param zombie, the zombie to validate.
     * @throws PVZException if the Zombie is null.
     */
    private void valideZombieExist(Zombie zombie) throws PVZException {
        if (zombie == null) throw new PVZException(PVZException.ERROR_ZOMBIE_NOT_EXIST);
    }

    /**
     * Delete plant of the board.
     * @param row, row of the plant.
     * @param col, col of the board.
     * @throws PVZException, if there isn´t a plant to delete.
     */
    public void deletePlant(int row, int col) throws PVZException {
        validePlantExistToDelete(row,col);
        Plant plant = plantsBoard[row][col];
        board[row][col].remove(plant);
        plantsBoard[row][col] = null;
        if(plant instanceof Attacker) attackers.remove(plant);
        if(plant instanceof Shooter)shooters.remove(plant);
        if(plant instanceof GenerateCoins) generateCoins.remove(plant);
        elements.remove(plant);
        plantPoints -= (plant.getValue()*1.5);
    }

    /*
     * Validate if exist plant to delete.
     * @param row of the plant to delete.
     * @param col of the plant to delete
     * @throws PVZException if there isn´t a plant in this position;
     */
    private void validePlantExistToDelete(int row, int col) throws PVZException {
        if(plantsBoard[row][col] == null) throw new PVZException(PVZException.NOT_PLANT_TO_DELETE);
    }


    /**
     * Add a plant to the board at a specified position.
     *
     * @param row    The row index for the plant.
     * @param col    The column index for the plant.
     * @param plant The type of plant to be added.
     * @throws PVZException if planting is not allowed or the cell is not empty.
     */
    public  void addPlant(int row, int col, String plant)  throws PVZException{
        valideCanPlant(row,col);
        valideEmptyCell(row,col);
        Plant newPlant = searchPlant(plant,row,col);
        validePlantExist(newPlant);
        if(newPlant.getValue()<= suns) {
            elements.add(newPlant);
            plantsBoard[row][col] = newPlant;
            board[row][col].add(newPlant);
            suns -= newPlant.getValue();
            if(newPlant instanceof Attacker attacker) attackers.add(attacker);
            if(newPlant instanceof Shooter shooter ) shooters.add(shooter);
            if (newPlant instanceof GenerateCoins generateCoin ) generateCoins.add(generateCoin);
            plantPoints += (newPlant.getValue()*1.5);
        }else{
            throw new PVZException(PVZException.ERROR_NOT_ENOUGH_SUNS);
        }
    }



    /*
     * Search the Plant that is shown with the string.
     * @param plant, the string of the plant.
     * @param row, the row of the plant.
     * @param col, the col of the plant.
     * @return Plant, the Plant of the string.
     * @throws PVZException if the plant is null.
     */
    private Plant searchPlant(String plant,int row,int col) throws PVZException{
        validePlantNotNull(plant);
        return switch (plant) {
            case "peashooter" -> new Peashooter(row, col);
            case "sunflower" -> new Sunflower(row, col);
            case "wallnut" -> new Wallnut(row, col);
            case "potatoMine" -> new PotatoMine(row, col);
            case "eciPlant" -> new ECIPlant(row, col);
            default -> null;
        };
    }


    /*
     * Validates whether a plant can be placed in the specified cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @throws PVZException if planting is not allowed in the specified cell.
     */
    private void valideCanPlant(int row, int col) throws PVZException{
        if(col == 0 || col == columns-1 ) throw new PVZException(PVZException.ERROR_CANT_PLANT);
    }

    /*
     * Validates whether a cell is empty before planting.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @throws PVZException if the cell is not empty.
     */
    private void valideEmptyCell(int row, int col) throws PVZException{
        if(plantsBoard[row][col] != null) throw new PVZException(PVZException.ERROR_CELDA_NOT_EMPTY);
    }

    /*
     * Validates plant exist.
     * @param plant, the plant.
     * @throws PVZException if the plant is null.
     *
     */

    private void validePlantExist(Plant plant) throws PVZException{
        if (plant == null) throw new PVZException(PVZException.ERROR_PLANT_NOT_EXIST);
    }
    /*
     * Validates that a plant was selected.
     * @param plant, String of the plant.
     * @throws PVZException if the string is null.
     */
    private void validePlantNotNull(String plant) throws PVZException{
        if (plant == null) throw new PVZException(PVZException.ERROR_PLANT_NOT_SELECTED);
    }
    /**
     * Add a coin to the board in a specific
     *
     */
    public void addCoin(int row, int col,int finishRow, String coin) throws PVZException{
        valideCoinNotNull(coin);
        Coin newCoin = searchCoin(coin,row,col,finishRow);
        valideCoinExist(newCoin);
        board[row][col].add(newCoin);
        moves.add(newCoin);
        elements.add(newCoin);
    }

    /*
     * Search a coin with the String.
     * @param String, string of the coin.
     * @return Coin, the coin of the string.
     */
    private Coin searchCoin(String coin, int row, int col,int finishRow) throws PVZException{

        return switch (coin) {
            case "sun" -> new Sun(row, col, finishRow);
            case "brain" -> new Brain(row, col, finishRow);
            case "eciSun" -> new ECISun(row, col, finishRow);
            default -> null;
        };

    }

    /*
     * Validate coin is not null.
     * @param coin, String of the coin.
     * @throws PVZException if String is null
     *
     */
    private void valideCoinNotNull(String coin) throws PVZException{
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_NULL);
    }

    /*
     * Validate coin exist.
     * @param coin, the Coin.
     * @throws PVZException if Coin doesn't exist.
     */
    private void valideCoinExist(Coin coin) throws PVZException{
        if (coin == null) throw new PVZException(PVZException.ERROR_COIN_EXIST);
    }

    /**
     * move the elements that can be moved in the board.
     */
    public void moveBoard(){
        for(Mover move : moves){
            Element moveElement = (Element) move;
            board[move.getRow()][move.getCol()].remove(moveElement);
            move.move();
            board[move.getRow()][move.getCol()].add(moveElement);
        }
    }

    /**
     * Take a coin of the board.
     * @param row, row of the coin.
     * @param col, col of the row.
     * @throws PVZException if not exist a coin in this cell.
     */
    public void takeCoin(int row, int col) throws PVZException{
        Coin coin = coinInCell(row,col);
        if(coin != null) {
            if (coin instanceof Brain) {
                brains += coin.getValue();
            } else {
                suns += coin.getValue();
            }
            board[row][col].remove(coin);
            moves.remove(coin);
            elements.remove(coin);
        }
    }

    /**
     * Look if a position don't have a coin.
     * @param row of the coin.
     * @param col of the coin.
     * @return if exist a coin false, if not true.
     */
    public boolean isEmptyOfCoins(int row, int col){
        for(Element element : board[row][col]) if (element instanceof Coin ) return false;
        return true;
    }

    /*
     * Take the coin of the cell.
     * @param row, row of the coin.
     * @param col, col of the coin.
     */
    private Coin coinInCell(int row, int col){
        for(Element element : board[row][col]){
            if (element instanceof Coin coin){
                return coin;
            }
        }
        return null;
    }

    /**
     * Adds a missile to the specified cell on the board and updates relevant collections.
     * @param row the row of the cell
     * @param col the column of the cell
     * @param missile the missile to be added
     */
    private void addMissile(int row, int col, Missile missile) {
        if (missile == null) return;
        board[row][col].add(missile);
        moves.add(missile);
        attackers.add(missile);
        elements.add(missile);
    }

    /**
     * Removes a missile from the specified cell on the board and updates relevant collections.
     * @param row the row of the cell
     * @param col the column of the cell
     * @param missile the missile to be removed
     */
    private void deleteMissile(int row, int col, Missile missile) {
        board[row][col].remove(missile);
        moves.remove(missile);
        attackers.remove(missile);
        elements.remove(missile);
    }

    /**
     * Processes the damage logic for all attackers in the game.
     */
    public void makeDamage() {
        for (int i = 0; i < attackers.size(); i++) {
            Attacker attacker = attackers.get(i);
            if (i < attackers.size()) {
                Zombie zombie = zombieInCell(attacker.getRow(), attacker.getCol());
                Plant plant = plantInCell(attacker.getRow(), attacker.getCol());

                switch (attacker) {
                    case Pea p -> {
                        if (zombie != null) {
                            p.makeDamage(zombie);
                            deleteMissile(p.getRow(), p.getCol(), p);
                        }
                    }
                    case POOmBa p -> {
                        if (plant != null) {
                            p.makeDamage(plant);
                            deleteMissile(p.getRow(), p.getCol(), p);
                        }
                    }
                    case Zombie z -> {
                        if (plant != null) {
                            z.makeDamage(plant);
                            z.makeAttack();
                        }
                    }
                    case PotatoMine potatoMine -> {
                        potatoMine.makeDamage(zombie);
                    }
                    case LawnMower lawnMower -> {
                        List<Zombie> zombieList = allTheZombieInCell(attacker.getRow(), attacker.getCol());
                        for (Zombie z : zombieList) {
                            lawnMower.makeDamage(z);
                        }
                    }
                    default -> {}
                }
            }
        }
    }

    /**
     * Retrieves the plant in the specified cell.
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the plant in the cell or null if none exists
     */
    private Plant plantInCell(int row, int col) {
        return plantsBoard[row][col] != null ? plantsBoard[row][col] : null;
    }

    /**
     * Retrieves the zombie in the specified cell.
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the zombie in the cell or null if none exists
     */
    private Zombie zombieInCell(int row, int col) {
        for (Element element : board[row][col]) {
            if (element instanceof Zombie zombie) {
                return zombie;
            }
        }
        return null;
    }

    /**
     * Removes elements with zero or negative life from the game.
     */
    public void garbageColector() {
        int len = elements.size();
        for (int i = 0; i < len; i++) {
            if (i < elements.size()) {
                Element element = elements.get(i);
                if (element.getLife() <= 0) {
                    deleteElement(element, element.getRow(), element.getCol());
                }
            }
        }
    }

    /**
     * Deletes an element from the game.
     * @param element the element to delete
     * @param row the row of the element
     * @param col the column of the element
     */
    private void deleteElement(Element element, int row, int col) {
        try {
            switch (element) {
                case Plant p -> deletePlant(row, col);
                case Zombie z -> deleteZombie(row, col);
                case Pea pe -> deleteMissile(row, col, pe);
                case LawnMower lm -> deleteLawnMower(row, col, lm);
                default -> {}
            }
        } catch (PVZException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if there is a zombie in the specified row.
     * @param row the row to check
     * @return true if there is a zombie, false otherwise
     */
    private boolean zombieInRow(int row) {
        for (List<Element> elements : board[row]) {
            for (Element element : elements) {
                if (element instanceof Zombie) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a plant in the specified row.
     * @param row the row to check
     * @return true if there is a plant, false otherwise
     */
    private boolean plantInRow(int row) {
        for (Plant p : plantsBoard[row]) {
            if (p != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Triggers the shooting logic for all shooters in the game.
     */
    public void makeShoot() {
        for (Shooter shooter : shooters) {
            boolean peashooterShoot = zombieInRow(shooter.getRow());
            boolean eciZombieShoot = plantInRow(shooter.getRow());
            Missile missile;
            if (shooter instanceof Plant) missile = shooter.shoot(peashooterShoot);
            else missile = shooter.shoot(eciZombieShoot);
            addMissile(shooter.getRow(), shooter.getCol(), missile);
        }
    }

    /**
     * Generates coins for all coin generators in the game.
     * @throws PVZException if an error occurs during coin generation
     */
    public void generateSun() throws PVZException {
        for (GenerateCoins generateCoins : generateCoins) {
            generateCoins.makeCoins(this);
        }
    }

    /**
     * Adds a lawn mower to the specified row.
     * @param row the row to add the lawn mower
     */
    private void addLawnMower(int row) {
        LawnMower lawnMower = new LawnMower(row, 0);
        elements.add(lawnMower);
        board[row][0].add(lawnMower);
        attackers.add(lawnMower);
        moves.add(lawnMower);
    }

    /**
     * Removes a lawn mower from the specified cell.
     * @param row the row of the cell
     * @param col the column of the cell
     * @param lawnMower the lawn mower to remove
     */
    private void deleteLawnMower(int row, int col, LawnMower lawnMower) {
        elements.remove(lawnMower);
        board[row][col].remove(lawnMower);
        attackers.remove(lawnMower);
        moves.remove(lawnMower);
    }

    /**
     * Generates lawn mowers for all rows in the game.
     */
    private void generateLawnMower() {
        for (int row = 0; row < rows; row++) {
            addLawnMower(row);
        }
    }

    /**
     * Retrieves all zombies in the specified cell.
     * @param row the row of the cell
     * @param col the column of the cell
     * @return a list of zombies in the cell
     */
    private List<Zombie> allTheZombieInCell(int row, int col) {
        List<Zombie> zombieList = new ArrayList<>();
        for (Element element : board[row][col]) {
            if (element instanceof Zombie zombie) {
                zombieList.add(zombie);
            }
        }
        return zombieList;
    }

    /**
     * Ends the game, stopping timers and updating game states.
     */
    public void endGame() {
        gameTimer.stop();
        gameMode.finishGame();
        zombieMachine.finishGame();
        gameFinished = true;
        zombiePoints += brains;
        plantPoints += suns;
    }

    /**
     * Ends the game with specific logic for a second mode.
     */
    public void endGameTwo() {
        plantMachine.finishGame();
        endGame();
    }
    public void endGameThree() {
        gameFinished = true;
    }

    /**
     * Checks if the game has finished.
     * @return true if the game is finished, false otherwise
     */
    public boolean gameIsFinished() {
        return gameFinished;
    }

    /**
     * Checks if a zombie has reached the last cell, and updates game state if so.
     */
    public void zombieInLastCell() {
        boolean flag = false;
        for (int row = 0; row < rows; row++) {
            int len = board[row][0].size();
            for (int j = 0; j < len; j++) {
                if (j < board[row][0].size()) {
                    Element element = board[row][0].get(j);
                    if (element instanceof LawnMower lm) {
                        flag = true;
                    }
                    if (element instanceof Zombie zombie && !flag) {
                        gameFinished = true;
                        zombiePoints = 1000000000;
                        plantPoints = 0;
                    }
                }
            }
            flag = false;
        }
    }

    public void resetPvz() {
        attackers = new ArrayList();
        shooters = new ArrayList();
        elements = new ArrayList();
        generateCoins = new ArrayList();
        startingBoards();
        generateLawnMower();
    }


}

