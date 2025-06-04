
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the window of the gameBoard.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class PVZInGame extends JFrame implements GeneralInterface, Serializable {
    private JPanel gamePanel;
    private JMenuItem open, save, newItem, exit;
    private String gameMode, selectedPlant, selectedZombie, plantPlayer,zombiePlayer,plantsType,zombieType;
    private String[] plantsToPlay;
    private String[] zombiesToPlay;
    private JButton[] plantsButtons;
    private JButton[] zombiesButtons;
    private JButton[][] cells = new JButton[5][10];
    private PVZ pvz;
    private Timer timer, pvspTimer,coinsTimer;
    private JLabel sunsLabel,brainsLabel,zombiePoints, plantPoints;
    private boolean shovelBoolean = false;
    private JButton shovelButton;
    private boolean plantsPlaying;
    private int gameTime;
    private int roundsZombies;
    private int roundsPlants;

    /**
     * Constructor for PVZInGame PvsM.
     *
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param plantPlayer    The plant player name.
     * @param zombieType     The zombie type.
     * @param startingSuns   The start number of suns.
     * @param gameTime       The time in game.
     * @param hordesTime     The hordes duration time.
     * @param hordesNumber   The number of hordes for game.
     *
     */
    public PVZInGame(String gameMode, HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay,String plantPlayer,String zombieType,int startingBrains, int startingSuns, int gameTime, int hordesTime,int hordesNumber) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        boolean booleanZombieType = (zombieType.equals("ZombiesOriginal"));
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, booleanZombieType,startingSuns,startingBrains,gameTime,hordesNumber,hordesTime);
        this.plantPlayer = plantPlayer;
        this.zombieType = zombieType;
        plantsPlaying = true;
        prepareElements();
        prepareActions();

    }

    /**
     * Constructor for PVZInGame PvsP.
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param plantPlayer    The plant player name.
     * @param zombiePlayer   The zombie player name.
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param startingBrains The start number of brains.
     * @param startingSuns   The start number of suns.
     */
    public PVZInGame( HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay,String plantPlayer,String zombiePlayer,String gameMode,int startingBrains, int startingSuns, int gameTime) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay,startingSuns,startingBrains,gameTime);
        this.plantPlayer = plantPlayer;
        this.zombiePlayer = zombiePlayer;
        plantsPlaying = true;
        this.gameTime = gameTime;
        prepareElements();
        prepareActions();
        pvspTimer = new Timer(120000,e -> makeZombiesPlay());
        pvspTimer.start();
        coinsTimer = new Timer(10000,e -> addCoin());
        coinsTimer.start();

    }

    /**
     * Constructor for PVZInGame MvsM.
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param zombieType     The zombie type.
     * @param plantType      The plant type.
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param gameTime       The time in game.
     * @param hordesTime     The hordes duration time.
     * @param hordesNumber   The number of hordes for game.
     */
    public PVZInGame(String plantType,String zombieType,String gameMode,HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay, int startingBrains, int startingSuns, int gameTime, int hordesTime,int hordesNumber) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        boolean booleanZombieType = (zombieType.equals("ZombiesOriginal"));
        boolean booleanPlantType = (plantType.equals("PlantsIntelligent"));
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, booleanZombieType,booleanPlantType,startingSuns,startingBrains,gameTime,hordesNumber,hordesTime);
        this.plantsType = plantType;
        this.zombieType = zombieType;
        prepareElements();
        prepareActions();

    }



    /*
     * Prepares all the graphical and logical components of the game.
     */
    private void prepareElements() {

        prepareElementsMenu();
        createPanel();
        if(gameMode.equals("PvsP")) {
            prepareButtonsZombies();
            prepareButtonsPlants();
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantPlayer+"</span></html>");
            label.setBounds(20,0,plantPlayer.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombiePlayer+"</span></html>");
            label2.setBounds(920,0,zombiePlayer.length()*20,50);
            gamePanel.add(label2);
        }
        if(gameMode.equals("PvsM")){
            prepareButtonsPlants();
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantPlayer+"</span></html>");
            label.setBounds(20,0,plantPlayer.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombieType+"</span></html>");
            label2.setBounds(920,0,zombieType.length()*20,50);
            gamePanel.add(label2);
        }
        if(gameMode.equals("MvsM")){
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantsType+"</span></html>");
            label.setBounds(20,0,plantsType.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombieType+"</span></html>");
            label2.setBounds(920,0,zombieType.length()*20,50);
            gamePanel.add(label2);
        }
        changeSizeToImage("fondoTablero.png");
        if(!gameMode.equals("MvsM"))prepareButtonsTablero();
        if(!gameMode.equals("PvsP")) {
            timer = new Timer(100, e -> refresh());
        }else{
            timer = new Timer(100, e -> refreshTwo());
        }
        timer.start();
        shovelButton = new BorderButton(" ");
        shovelButton.setBounds(540, 5, 40, 40);
        sunsLabel = new JLabel();
        brainsLabel = new JLabel();
        zombiePoints = new JLabel();
        plantPoints = new JLabel();
        gamePanel.add(plantPoints);
        gamePanel.add(zombiePoints);
        gamePanel.add(shovelButton);
        gamePanel.add(brainsLabel);
        gamePanel.add(sunsLabel);


    }

    /*
     * Creates and configures the game panel where the game board and elements are drawn.
     */
    private void createPanel(){
        // Crear panel con las plantas que se van a jugar
        ImageIcon icon = getImageIcon("fondoTablero.png");
        Image originalImage = icon.getImage();

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                //zone of plants anzombies select.
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                ImageIcon icon = getImageIcon("pala.png");
                Image originalImage = icon.getImage();
                g.drawImage(originalImage, 540, 5, 40, 40, null);
                int count = 50;
                for (String planta : plantsToPlay) {
                    ImageIcon iconPlant = getImageIcon(planta+".png");
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 40, 60, null);
                    count += 70;
                }
                count = 50;
                for (String zombie : zombiesToPlay) {
                    ImageIcon iconZombie = getImageIcon(zombie + ".png");
                    Image originalImageZombie = iconZombie.getImage();
                    g.drawImage(originalImageZombie, 930, count, 60, 60, null);
                    count += 70;
                }
                for(Element element: pvz.getElements()) {
                    if (element instanceof ZombieWithShield) {
                        Shield shield = ((ZombieWithShield) element).getShield();
                        ImageIcon iconZombie = getImageIcon(element.getName() + shield.getName() + element.getExtension());
                        Image originalImageZombie = iconZombie.getImage();
                        g.drawImage(originalImageZombie, element.getXPosition(), element.getYPosition(), element.getWidth(), element.getHeight(), null);
                    } else {
                        ImageIcon iconZombie = getImageIcon(element.getName() + element.getExtension());
                        Image originalImageZombie = iconZombie.getImage();
                        g.drawImage(originalImageZombie, element.getXPosition(), element.getYPosition(), element.getWidth(), element.getHeight(), null);
                    }
                }
                //name or type zone
                g.setColor(new Color(101, 67, 33));
                g.fillRect(10, 5,250, 40);
                g.setColor(new Color(100, 100, 100));
                g.fillRect(910, 5, 250, 40);
                //sun counter
                g.setColor(new Color(139, 69, 19));
                g.fillRect(280, 5, 120, 40);
                ImageIcon iconSun = getImageIcon( "sun.png");
                Image originalImageSun = iconSun.getImage();
                g.drawImage(originalImageSun, 285, 10, 30, 30, null);
                sunsLabel.setText("<html><span style='font-size:20px; letter-spacing:5px;'>"+pvz.getSuns()+"</span></html>");
                sunsLabel.setBounds(320,5,80,40);
                //brain counter
                g.setColor(new Color(150, 150, 150));
                g.fillRect(760, 5, 120, 40);
                ImageIcon iconBrain = getImageIcon( "brain.png");
                Image originalImageBrain = iconBrain.getImage();
                g.drawImage(originalImageBrain, 765, 10, 30, 30, null);
                brainsLabel.setText("<html><span style='font-size:20px; letter-spacing:5px;'>"+pvz.getBrains()+"</span></html>");
                brainsLabel.setBounds(800 ,5,80,40);
                // zombiePoints
                g.setColor(new Color(150, 150, 150));
                g.fillRect(630, 5, 120, 40);
                zombiePoints.setText("<html><span style='font-size:20px; letter-spacing:5px;'>"+pvz.getZombiePoints()+"</span></html>");
                zombiePoints.setBounds(640 ,5,80,40);
                g.setColor(new Color(139, 69, 19));
                g.fillRect(410, 5, 120, 40);
                plantPoints.setText("<html><span style='font-size:20px; letter-spacing:5px;'>"+pvz.getPlantPoints()+"</span></html>");
                plantPoints.setBounds(420,5,80,40);
            }
        };

        gamePanel.setLayout(null); // Usar layout absoluto para colocar botones
        setContentPane(gamePanel);
    }

    /*
     * Adjusts the JFrame size to match the dimensions of an image.
     *
     * @param imageName The name of the image file.
     */
    private void changeSizeToImage(String imageName) {
        ImageIcon icon = getImageIcon(imageName);

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    /*
     * Prepares the menu bar for the game.
     */
    private void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        open = new JMenu("Open");
        save = new JMenuItem("Save");
        newItem = new JMenuItem("New");
        exit = new JMenuItem("Exit");

        menu.add(open);
        menu.add(save);
        menu.add(newItem);
        menu.add(exit);

        menuBar.add(menu);
        setJMenuBar(menuBar);

    }
    /*
     * Prepares the game board (grid of cells) for the game.
     * Each cell is represented by a transparent button.
     */
    private void prepareButtonsTablero() {
        int startCol = 140;
        int startRow =55;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new TransparentButton("");
                cells[row][col].setBounds(startCol,startRow,70,75);
                gamePanel.add(cells[row][col]);
                startCol +=70 ;
            }
            startCol = 140;
            startRow += 75 ;
        }
    }

    /*
     * Prepares the buttons representing plants available for the player.
     * Buttons are aligned vertically on the left side of the game panel.
     */
    private void prepareButtonsPlants() {
        int row = 50;
        int len;
        len = plantsToPlay.length;
        plantsButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            plantsButtons[i] = new BorderButton(" ");
            plantsButtons[i].setBounds(20,row,40,60);
            row += 70;
            gamePanel.add(plantsButtons[i]);
        }
    }

    /*
     * Prepares the buttons representing zombies available for the player.
     * Buttons are aligned vertically on the left side of the game panel.
     */
    private void prepareButtonsZombies() {
        int row = 50;
        int len;
        len = zombiesToPlay.length;
        zombiesButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            zombiesButtons[i] = new BorderButton(" ");
            zombiesButtons[i].setBounds(930,row,60,60);
            row += 70;
            gamePanel.add(zombiesButtons[i]);
        }
    }

    /*
     * Prepares all actions and listeners for game interactions.
     */
    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });
        exit.addActionListener(e -> closeWindowAction());
        newItem.addActionListener(e -> openPrincipalWindow());
        int plantsLen = plantsButtons.length;
        for(int i = 0; i < plantsLen; i++) {
            String plant = plantsToPlay[i];
            if (plantsButtons[i] != null) plantsButtons[i].addActionListener(e ->selectPlant(plant));
        }
        if (gameMode.equals("PvsP")) {
            int zombiesLen = zombiesButtons.length;
            for (int i = 0; i < zombiesLen; i++) {
                String zombie = zombiesToPlay[i];
                if (zombiesButtons[i] != null) zombiesButtons[i].addActionListener(e -> selectZombie(zombie));
            }
        }
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 10; col++) {
                int i = row;
                int j = col;
                if (cells[i][j] != null) cells[row][col].addActionListener(e -> cellAction(i,j));
            }
        }
        shovelButton.addActionListener(e -> canDelete());


    }


    /*
     * Adds a plant to the game board at the specified row and column.
     *
     * @param row The row index where the plant will be added.
     * @param col The column index where the plant will be added.
     */
    private void cellAction(int row, int col) {
        try {
            if(plantsPlaying) {
                if (pvz.isEmptyOfCoins(row, col) && !shovelBoolean) {
                    pvz.addPlant(row, col, selectedPlant);
                    selectedPlant = null;
                } else if (shovelBoolean) {
                    pvz.deletePlant(row, col);
                    shovelBoolean = false;
                } else {

                    takeCoin(row, col);
                }
            }else{
                if (pvz.isEmptyOfCoins(row, col) && !shovelBoolean) {
                    pvz.addZombie(selectedZombie,row);
                    selectedZombie = null;
                } else {
                    takeCoin(row, col);
                }
            }
        } catch (PVZException e) {
            shovelBoolean = false;
            timerMessage(e.getMessage());
        }
    }

    /*
     * Selects a plant to be placed on the game board.
     *
     * @param planta The name of the plant to be selected.
     */
    private void selectPlant(String planta){
        selectedPlant = planta;
    }
    private void selectZombie(String zombie){
        selectedZombie = zombie;
    }

    /*
     * Opens the main menu window and closes the current game window.
     */
    private void openPrincipalWindow() {
        int opcion = JOptionPane.showConfirmDialog(this, "Do you want to come to the start screen?", "Confirm back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            pvz.endGame();
            pvz = null;
            timer.stop();
            PVZGUI pvzguiwindow = new PVZGUI();
            pvzguiwindow.setVisible(true);
            dispose();
        }
    }

    /*
     * Handles the action for closing the window. Prompts the user for confirmation.
     */
    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "Are you sure that tou want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }

    /*
     * Take a coin of the board.
     */
    private void takeCoin(int row, int col) {
        try {
            pvz.takeCoin(row, col);
        } catch (PVZException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Make the shovel can delete.
     */
    private void canDelete() {
        shovelBoolean = true;
    }

    /*
     * Refreshes the game panel by repainting it.
     */
    public void refresh() {
        try {
            pvz.generateSun();
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
            pvz.zombieInLastCell();
            if(pvz.gameIsFinished()) finishGame();
            repaint();
        } catch (PVZException e) {
            timerMessage(e.getMessage());
        }
    }

    /*
     * Refreshes the game panel by repainting it.
     */
    public void refreshTwo() {
        try {
            pvz.generateSun();
            pvz.makeShoot();
            pvz.makeDamage();
            pvz.moveBoard();
            pvz.garbageColector();
            pvz.zombieInLastCell();
            if(pvz.gameIsFinished()) finishRound();
            repaint();
        } catch (PVZException e) {
            timerMessage(e.getMessage());
        }
    }



    /*
     * Show a temporal message in the screen.
     */
    private void timerMessage(String message) {
        JDialog dialog = new JDialog();
        JLabel label = new JLabel(message);
        label.setBounds(20, 20, 70, 70);
        label.setFont(new Font("DialogInput", Font.BOLD, 10));
        dialog.setTitle("ERROR");
        dialog.add(label);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null); // Centrar en la pantalla
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        new Timer(1500, s -> dialog.dispose()).start();
    }


    private void finishGame() {


        // Obtener puntos
        int zombiePoints = pvz.getZombiePoints();
        int plantPoints = pvz.getPlantPoints();
        JLabel label;
        JLabel labelWinner;
        if (!gameMode.equals("PvsP")) {
            // Determinar el ganador
            String ganador = "";
            if (gameMode.equals("PvsM")) {
                ganador = zombiePoints > plantPoints ? zombieType : plantPlayer;
            } else if (gameMode.equals("MvsM")) {
                ganador = zombiePoints > plantPoints ? zombieType : plantsType;
            }

            // Crear etiquetas
            labelWinner = new JLabel("<html><span style='font-size:30px; color:white;'>¡Ganador!</span></html>");
            label = new JLabel("<html><span style='font-size:30px; letter-spacing:20px; color:white;'>" + ganador + "</span></html>");


        }else{
            if( roundsPlants == 2){
                labelWinner = new JLabel("<html><span style='font-size:30px; color:white;'>¡Ganador!</span></html>");
                label = new JLabel("<html><span style='font-size:30px; letter-spacing:20px; color:white;'>" + plantPlayer + "</span></html>");
            }else{
                labelWinner = new JLabel("<html><span style='font-size:30px; color:white;'>¡Ganador!</span></html>");
                label = new JLabel("<html><span style='font-size:30px; letter-spacing:20px; color:white;'>" + zombiePlayer + "</span></html>");
            }
        }
        // Establecer posiciones
        labelWinner.setBounds(gamePanel.getWidth() / 4, gamePanel.getHeight() / 2 - 100, 400, 100);
        label.setBounds(gamePanel.getWidth() / 4, gamePanel.getHeight() / 2, 400, 100);

        // Personalizar estilos
        labelWinner.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        gamePanel.add(labelWinner);
        gamePanel.add(label);

        // Finalizar el juego
        if (gameMode.equals("PvsM"))pvz.endGame();
        if (gameMode.equals("MvsM"))pvz.endGameTwo();
        if (gameMode.equals("PvsP")){
            pvspTimer.stop();
            coinsTimer.stop();
            pvz.endGameThree();
        }
        timer.stop();

    }

    public static PVZInGame open(File file) {
       PVZInGame game = null;
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            game = (PVZInGame) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return game;
    }



    // PvsP control.

    private void makeZombiesPlay(){
        plantsPlaying = false;
        pvspTimer.stop();
        pvspTimer = new Timer((gameTime*1000)/2, e -> finishRound());
        pvspTimer.start();
    }
    private void addCoin() {
        int limit = (plantsPlaying)?0:4;
        String coin = (plantsPlaying)?"sun":"brain";
        Random rand = new Random();
        int col = rand.nextInt(5) + 1 + limit;
        int row = rand.nextInt(5) ;
        try{
            pvz.addCoin(0,col,row,coin);
        }catch(PVZException e){
            e.printStackTrace();
        }
    }
    private void finishRound() {
        pvspTimer.stop();
        pvz.resetPvz();
        pvspTimer = new Timer(120000,e -> makeZombiesPlay());
        pvspTimer.start();
        int zombiePoints = pvz.getZombiePoints();
        int plantPoints = pvz.getPlantPoints();
        if(zombiePoints > plantPoints) {
            roundsZombies += 1;
        }else{
            roundsPlants += 1;
        }
        if (roundsZombies == 1 && roundsPlants == 1) {
            drew();
            finishGame();
        }
        if (roundsZombies == 2 || roundsPlants == 2) {
            finishGame();
        }
        plantsPlaying = true;
    }

    private void drew() {

    }

}
