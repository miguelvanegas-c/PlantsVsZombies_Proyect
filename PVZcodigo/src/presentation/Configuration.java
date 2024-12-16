
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashSet;
/**
 * This is the configuration screen of a new game for the POOB vs ZOMBIES game interface.
 *
 * @author Miguel Angel Vanegas and Julian Castiblanco.
 * @version 1.0
 */
public class Configuration extends JFrame implements GeneralInterface {
    private JMenuItem open, save, newItem, exit;
    private JPanel mainPanel;
    private JButton back, sunflower, peashooter, wallnut,potatomine,eciplant, play, select, next, zombie, coneZombie, bucketZombie,eciZombie, brainStein;
    private HashSet<String> plantsToPlay = new HashSet<>();
    private HashSet<String> zombiesToPlay = new HashSet<>();
    private String gameMode, possiblePlantToPlay, plantPlayerName, zombiePlayerName, zombieType, plantType, possibleZombieToPlay;
    private JComboBox<String> selectPlant, selectZombie;
    private JTextField plantPlayerNameField, zombiePlayerNameField, startingSunsField, startingBrainsField,gameTimeField,hordesTimeField,hordesNumberField;
    private int startingSuns, startingBrains,gameTime,hordesTime,hordesNumber;

    /**
     * constructor of the Difficulty class.
     *
     * @param gameMode, the chosen game mode.
     */
    public Configuration(String gameMode) {
        super("Game Configuration");
        this.gameMode = gameMode;
        prepareElements();
        prepareActionsToPlantsElection();
        setVisible(true);
    }

    /*
     * prepare elements to the window.
     */

    private void prepareElements() {
        changeSizeToImage("PantallaSeleccion.png");
        createPlantsElectionPanel();
        prepareElementsMenu();
        prepareButtonsToPlantsElection();
        prepareNameElectionToPlants();
        prepareTypeElectionToPlants();
        prepareStartingSunsChoose();
    }


    /*
     * resizes the window to the size of the background image.
     */

    private void changeSizeToImage(String imageName) {
        ImageIcon icon = getImageIcon(imageName);

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null); // Centrar el JFrame en la pantalla
        setResizable(false);
    }

    /*
     * Creates the panel for the choice of plants for the start of a game.
     */
    private void createPlantsElectionPanel() {
        ImageIcon icon = getImageIcon("pantallaSeleccion.png");
        Image originalImage = icon.getImage();
        icon = getImageIcon("peashooter1.png");
        Image peashooterImage = icon.getImage();
        icon = getImageIcon("sunflower1.png");
        Image sunflowerImage = icon.getImage();
        icon = getImageIcon("wallnut1.png");
        Image wallnutImage = icon.getImage();
        icon = getImageIcon("potatoMine1.png");
        Image potatoMineImage = icon.getImage();
        icon = getImageIcon("eciPlant1.png");
        Image eciPlantImage = icon.getImage();
        icon = getImageIcon("sun.png");
        Image sunImage = icon.getImage();
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);

                //Posibles plantas a agregar.
                g.drawImage(peashooterImage, 150, 155, 100, 100, null);
                g.drawImage(sunflowerImage, 250, 155, 100, 100, null);
                g.drawImage(wallnutImage, 350, 155, 100, 100, null);
                g.drawImage(potatoMineImage, 150, 255, 100, 100, null);
                g.drawImage(eciPlantImage, 250, 255, 100, 100, null);
                g.drawImage(sunImage, 800, 50, 30, 30, null);

                //Informacion de la planta seleccionada.
                if (possiblePlantToPlay != null) {
                    ImageIcon icon = getImageIcon(possiblePlantToPlay + "I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }

                g.setColor(new Color(139, 69, 19));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(101, 67, 33));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for (String planta : plantsToPlay) {
                    ImageIcon icon = getImageIcon(planta + "1.png");
                    Image image = icon.getImage();
                    g.drawImage(image, x, 530, 100, 100, null);
                    x += 100;

                }

            }
        };
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
    }

    /*
     * Prepares the menu items for the open, save, new and exit items.
     */

    private void prepareElementsMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        newItem = new JMenuItem("New");
        exit = new JMenuItem("Exit");

        menuBar.add(menu);
        menu.add(open);
        menu.add(save);
        menu.add(newItem);
        menu.add(exit);

        setJMenuBar(menuBar);
    }

    /*
     * Prepare the elements for the choice of type of plant machine.
     */

    private void prepareTypeElectionToPlants() {
        String[] opcionesPlantas = {"PlantsIntelligent", "PlantsStrategic"};
        selectPlant = new JComboBox<>(opcionesPlantas);
        selectPlant.setBounds(100, 50, 200, 40);
        if (gameMode.equals("MvsM")) {
            mainPanel.add(selectPlant);
        }
    }

    /*
     * Prepare the elements for the choice of player name for the plants.
     */

    private void prepareNameElectionToPlants() {
        JLabel nombre = new JLabel("Player Name:");
        nombre.setBounds(120, 50, 150, 30);

        plantPlayerNameField = new JTextField();
        plantPlayerNameField.setBounds(200, 50, 200, 30);
        if (!gameMode.equals("MvsM")) {
            mainPanel.add(plantPlayerNameField);
            mainPanel.add(nombre);
        }
    }

    /*
     * Prepare elements to choose the starting suns.
     */
    private void prepareStartingSunsChoose() {
        JLabel label = new JLabel("Starting Suns:");
        label.setBounds(670, 50, 150, 30);
        startingSunsField = new JTextField();
        startingSunsField.setBounds(755, 50, 40, 30);
        mainPanel.add(startingSunsField);
        mainPanel.add(label);

    }

    /*
     * prepares the elements for the choice of plants.
     */

    private void prepareButtonsToPlantsElection() {
        //Botones encima de cada planta para poder elegirlas
        back = new EspecialButton("Back");
        back.setBounds(1100, 610, 150, 30);


        next = new EspecialButton("Next");
        next.setBounds(1100, 550, 150, 30);


        peashooter = new BorderButton("     ");
        wallnut = new BorderButton("      ");
        sunflower = new BorderButton("       ");
        potatomine = new BorderButton("       ");
        eciplant = new BorderButton("       ");
        select = new EspecialButton("Select");
        peashooter.setBounds(150, 155, 100, 100);
        sunflower.setBounds(250, 155, 100, 100);
        wallnut.setBounds(350, 155, 100, 100);
        potatomine.setBounds(150,255,100,100);
        eciplant.setBounds(250,255,100,100);
        select.setBounds(845, 460, 140, 30);

        mainPanel.add(peashooter);
        mainPanel.add(sunflower);
        mainPanel.add(wallnut);
        mainPanel.add(potatomine);
        mainPanel.add(eciplant);
        mainPanel.add(back);
        mainPanel.add(next);

    }

    /*
     * prepares the actions for the plant selection buttons.
     */

    private void prepareActionsToPlantsElection() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        back.addActionListener(e -> openPrincipalWindow());
        exit.addActionListener(e -> closeWindowAction());
        newItem.addActionListener(e -> openPrincipalWindow());
        peashooter.addActionListener(e -> choosePlant("peashooter"));
        sunflower.addActionListener(e -> choosePlant("sunflower"));
        wallnut.addActionListener(e -> choosePlant("wallnut"));
        potatomine.addActionListener(e -> choosePlant("potatoMine"));
        eciplant.addActionListener(e -> choosePlant("eciPlant"));

        next.addActionListener(e -> nextPanel());
        select.addActionListener(e -> selectPlant());
        selectPlant.addActionListener(e -> selectPlantType());
        plantPlayerNameField.addActionListener(e -> selectPlantName());
        startingSunsField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectStartingSuns();
                } catch (PVZException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /*
     * Opens the initial window of the POOB vs ZOMBIES game.
     */

    private void openPrincipalWindow() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres volver a la pantalla de inicio?", "Confirmar retroceder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            PVZGUI pvzGUIWindow = new PVZGUI();
            pvzGUIWindow.setVisible(true);

            dispose();
        }
    }

    /*
     * Pressing a plant selects it to play.
     */

    private void choosePlant(String plant) {
        possiblePlantToPlay = plant;
        mainPanel.add(select);
        mainPanel.repaint();
    }

    /*
     *Select the plant that has been chosen before
     */
    private void selectPlant() {
        plantsToPlay.add(possiblePlantToPlay);
        mainPanel.repaint();
    }

    /*
     *Go to the next panel.
     */
    private void nextPanel() {
        try {
            if (gameMode.equals("MvsM")) validePlantsType();
            else {
                validePlantsName();
            }
            valideStartingSuns();
            validePlantsToPlay();
            createZombiesElectionPanel();
            prepareButtonsToZombiesElection();
            prepareTypeElectionToZombies();
            prepareNameElectionToZombies();
            prepareStartingBrainsChoose();
            prepareGameTimeElection();
            prepareActionsToZombiesElection();
            setVisible(true);
        } catch (PVZException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /*
     * Validate plant type exist.
     * @throws PVZException if plant type doesn't exist.
     */
    private void validePlantsType() throws PVZException {
        if (plantType == null) throw new PVZException(PVZException.ERROR_NOT_PLANT_TYPE);
    }

    /*
     * Validate plant name exist.
     * @throws PVZException if plant name doesn't exist.
     */

    private void validePlantsName() throws PVZException {
        if (plantPlayerName == null) throw new PVZException(PVZException.ERROR_NOT_PLANT_NAME);
    }

    /*
     * Validate starting suns is >= 0.
     * @Throws PVZException if there isn't a starting suns.
     */

    private void valideStartingSuns() throws PVZException {
        if (startingSuns <= 0) throw new PVZException(PVZException.ERROR_NOT_STARTING_SUNS);
    }

    /*
     * Select the type of plant machine
     */

    private void selectPlantType() {
        plantType = (String) selectPlant.getSelectedItem();
    }

    /*
     * Select the name of plant machine.
     */

    private void selectPlantName() {
        plantPlayerName = plantPlayerNameField.getText();
    }

    /*
     * Select the number of suns.
     * @throws PVZException if the value isn't an integer.
     */

    private void selectStartingSuns() throws PVZException {
        try {
            startingSuns = Integer.parseInt(startingSunsField.getText());
        } catch (NumberFormatException e) {
            throw new PVZException(PVZException.NOT_NUMBER);
        }
    }

    /*
     * Create the panel to zombies election.
     */

    private void createZombiesElectionPanel() {
        ImageIcon icon = getImageIcon("pantallaSeleccionZombies.png");
        Image originalImage = icon.getImage();
        icon = getImageIcon("zombie.gif");
        Image zombieImage = icon.getImage();
        icon = getImageIcon("caraCubeta.gif");
        Image zombieBaldeImage = icon.getImage();
        icon = getImageIcon("caraCono.gif");
        Image zombieConoImage = icon.getImage();
        icon = getImageIcon("eciZombie1.png");
        Image eciZombieImage = icon.getImage();
        icon = getImageIcon("brainstein1.png");
        Image brainStein = icon.getImage();
        icon = getImageIcon("brain.png");
        Image brainImage = icon.getImage();
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);

                //Posibles zombies a agregar.
                g.drawImage(zombieImage, 150, 155, 100, 100, null);
                g.drawImage(zombieConoImage, 250, 155, 100, 100, null);
                g.drawImage(zombieBaldeImage, 350, 155, 100, 100, null);
                g.drawImage(eciZombieImage, 150, 255, 100, 100, null);
                g.drawImage(brainStein, 250, 255, 100, 100, null);
                g.drawImage(brainImage, 800, 50, 30, 30, null);

                //Informacion de la planta seleccionada.
                if (possibleZombieToPlay != null) {
                    ImageIcon icon = getImageIcon(possibleZombieToPlay + "I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }


                g.setColor(new Color(0, 0, 0));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(64, 64, 64));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for (String zombie : zombiesToPlay) {
                    ImageIcon icon = getImageIcon(zombie + ".png");
                    Image image = icon.getImage();
                    g.drawImage(image, x, 530, 100, 100, null);
                    x += 100;

                }

            }
        };
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
    }

    /*
     * Prepare starting brains election.
     */

    private void prepareStartingBrainsChoose() {
        JLabel label = new JLabel("Starting Brains:");
        label.setBounds(670, 50, 150, 30);
        startingBrainsField = new JTextField();
        startingBrainsField.setBounds(755, 50, 40, 30);
        mainPanel.add(startingBrainsField);
        mainPanel.add(label);
    }

    /*
     * Prepare time election to the game.
     */

    private void prepareGameTimeElection(){
        //Game Time
        JLabel labelGameTime = new JLabel("Game Time in seconds:");
        labelGameTime.setBounds(1060,10,150,30);
        gameTimeField = new JTextField();
        gameTimeField.setBounds(1200, 10, 60, 30);

        //Hordes time duration
        if(!gameMode.equals("PvsP")) {
            JLabel labelHordesTime = new JLabel("Hordes duration time in seconds:");
            labelHordesTime.setBounds(1005, 90, 190, 30); //1005, 50, 190, 30
            hordesNumberField = new JTextField();
            hordesNumberField.setBounds(1200, 50, 60, 30);
            //Hordes number for game
            JLabel labelHordesNumber = new JLabel("Number of zombies:");
            labelHordesNumber.setBounds(1005, 50, 190, 30); //1050, 90, 150, 30
            hordesTimeField = new JTextField();
            hordesTimeField.setBounds(1200, 90, 60, 30);

            mainPanel.add(hordesTimeField);
            mainPanel.add(labelHordesNumber);
            mainPanel.add(labelHordesTime);
            mainPanel.add(hordesNumberField);
        }

        mainPanel.add(gameTimeField);
        mainPanel.add(labelGameTime);
    }

    /*
     * Prepare buttons to zombies election.
     */

    private void prepareButtonsToZombiesElection() {
        zombie = createGifButton("zombie.gif", 150, 155, 100, 100);
        coneZombie = createGifButton("caraCono.gif", 250, 155, 100, 100);
        bucketZombie = createGifButton("caraCubeta.gif", 350, 155, 100, 100);
        eciZombie = new BorderButton(" ");
        eciZombie.setBounds(150,255,100,100);
        brainStein = new BorderButton(" ");
        brainStein.setBounds(250, 255,100,100);
        select = new EspecialButton("Select");
        select.setBounds(860, 460, 140, 30);

        play = new EspecialButton("Jugar");
        play.setBounds(1100, 550, 150, 30);

        mainPanel.add(zombie);
        mainPanel.add(coneZombie);
        mainPanel.add(bucketZombie);
        mainPanel.add(eciZombie);
        mainPanel.add(brainStein);
        mainPanel.add(play);
        mainPanel.add(back);
    }

    /*
     * prepare action to zombies election.
     */

    private void prepareActionsToZombiesElection() {
        zombie.addActionListener(e -> chooseZombie("zombie"));
        coneZombie.addActionListener(e -> chooseZombie("zombieCono"));
        bucketZombie.addActionListener(e -> chooseZombie("zombieBalde"));
        eciZombie.addActionListener(e -> chooseZombie("eciZombie"));
        brainStein.addActionListener(e -> chooseZombie("brainstein"));
        select.addActionListener(e -> selectZombie());
        play.addActionListener(e -> openPVZInGameWindow());
        selectZombie.addActionListener(e -> selectZombieType());
        zombiePlayerNameField.addActionListener(e -> selectZombieName());
        startingBrainsField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectStartingBrains();
                } catch (PVZException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gameTimeField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectGameTime();
                } catch (PVZException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        if(!gameMode.equals("PvsP")) {
            hordesTimeField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        selectHordesTime();
                    } catch (PVZException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            hordesNumberField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        selectHordesNumber();
                    } catch (PVZException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }

    /*
     * Allows to choose a zombie to be selected.
     * @param zombie, name of the zombie that has been chosen.
     */

    private void chooseZombie(String zombie) {
        possibleZombieToPlay = zombie;
        mainPanel.add(select);
        mainPanel.repaint();
    }

    /*
     * Select the zombie that is selected.
     */

    private void selectZombie() {
        zombiesToPlay.add(possibleZombieToPlay);
        mainPanel.repaint();
    }

    /*
     * Pressing play opens a PVZInGame.
     */

    private void openPVZInGameWindow() {
        try {
            if (gameMode.equals("PvsP")) valideZombiesName();
            else valideZombiesType();
            valideZombiesToPlay();
            valideStartingBrains();
            valideGameTime();
            if(!gameMode.equals("PvsP")) {
                valideHordesTime();
                valideHordesNumber();
            }
            PVZInGame pvzInGameWindow = null;
            if(gameMode.equals("PvsM")) pvzInGameWindow = new PVZInGame(gameMode, plantsToPlay, zombiesToPlay, plantPlayerName, zombieType,startingBrains,startingSuns,gameTime, hordesTime,hordesNumber);
            else if (gameMode.equals("PvsP")) pvzInGameWindow = new PVZInGame(plantsToPlay, zombiesToPlay, plantPlayerName, zombiePlayerName, gameMode,startingBrains,startingSuns,gameTime);
            else pvzInGameWindow = new PVZInGame( plantType, zombieType, gameMode, plantsToPlay, zombiesToPlay,startingBrains,startingSuns,gameTime, hordesTime,hordesNumber);
            pvzInGameWindow.setVisible(true);
            dispose();
        } catch (PVZException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    /*
     * configura el comportamiento al cerrar la ventana.
     */

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /*
     * Prepare the elements for the choice of zombie machine type.
     */

    private void prepareTypeElectionToZombies() {
        String[] opcionesZombies = {"ZombiesOriginal", "ZombiesStrategic"};
        selectZombie = new JComboBox<>(opcionesZombies);
        selectZombie.setBounds(100, 50, 200, 40);
        if (gameMode.equals("MvsM") || gameMode.equals("PvsM")) {
            mainPanel.add(selectZombie);
        }
    }

    /*
     * Prepare the elements for the choice of player name for the zombies.
     */

    private void prepareNameElectionToZombies() {
        JLabel nombre = new JLabel("Zombie Player:");
        nombre.setBounds(70, 50, 150, 30);

        zombiePlayerNameField = new JTextField();
        zombiePlayerNameField.setBounds(200, 50, 200, 30);
        if (gameMode.equals("PvsP")) {
            mainPanel.add(zombiePlayerNameField);
            mainPanel.add(nombre);
        }
    }

    /*
     * Select zombie type of machine.
     */

    private void selectZombieType() {
        zombieType = (String) selectZombie.getSelectedItem();
    }

    /*
     * Select name to zombie player.
     */
    private void selectZombieName() {
        zombiePlayerName = zombiePlayerNameField.getText();
    }

    /*
     * Select the number of zombies to start.
     * @throws PVZException if there isn't number.
     */

    private void selectStartingBrains() throws PVZException {
        try{
            startingBrains = Integer.parseInt(startingBrainsField.getText());
        }catch (NumberFormatException e){
            throw new PVZException(PVZException.NOT_NUMBER);
        }
    }

    /*
     * Select the game time.
     * @throws PVZException if there isn't a number
     */
    private void selectGameTime() throws PVZException {
        try{
            gameTime = Integer.parseInt(gameTimeField.getText());
        }catch (NumberFormatException e){
            throw new PVZException(PVZException.NOT_NUMBER);
        }
    }

    /*
     * Select the hordes duration time.
     * @throws PVZException if there isn't a number
     */
    private void selectHordesTime() throws PVZException {
        try{
            hordesTime = Integer.parseInt(hordesTimeField.getText());
        }catch (NumberFormatException e){
            throw new PVZException(PVZException.NOT_NUMBER);
        }
    }

    /*
     * Select the hordes number for game.
     * @throws PVZException if there isn't a number
     */
    private void selectHordesNumber() throws PVZException {
        try{
            hordesNumber = Integer.parseInt(hordesNumberField.getText());
        }catch (NumberFormatException e){
            throw new PVZException(PVZException.NOT_NUMBER);
        }
    }

    private void valideStartingBrains() throws PVZException {
        if(startingBrains <= 0) throw new PVZException(PVZException.ERROR_NOT_STARTING_BRAINS);
    }

    /*
     * Validate zombie type exist.
     * @throw PVZException if zombie type doesn't exist.
     */

    private void valideZombiesType() throws PVZException{
        if (zombieType == null) throw new PVZException(PVZException.ERROR_NOT_ZOMBIE_TYPE);
    }
    /*
     * Validate zombie name exist.
     * @throw PVZException if zombie name doesn't exist.
     */

    private void valideZombiesName() throws PVZException{
        if (zombiePlayerName == null) throw new PVZException(PVZException.ERROR_NOT_ZOMBIE_NAME);
    }

    /*
     * Validate if plants were chosen to play.
     * @throws PVZException if plants was chosen.
     */

    private void validePlantsToPlay() throws PVZException {
        if(plantsToPlay.isEmpty()) {throw new PVZException(PVZException.NOT_PLANTS_CHOOSED_TO_PLAY);}
    }

    /*
     * Validate if zombies were chosen to play.
     * @throws PVZException if zombies was chosen.
     */

    private void valideZombiesToPlay() throws PVZException {
        if(zombiesToPlay.isEmpty()) {throw new PVZException(PVZException.NOT_ZOMBIES_CHOOSED_TO_PLAY);}
    }

    /*
     * Validate the game time is correct.
     * @throws PVZException, if game time is lower than 40 seconds.
     */
    private void valideGameTime() throws PVZException {
        if(gameTime<180 || gameTime >= 360)throw new PVZException(PVZException.ERROR_LOW_GAME_TIME);
    }

    /*
     * Validate the hordes number is correct.
     * @throws PVZException, if the hordes number <= 0.
     */
    private void valideHordesNumber() throws PVZException {
        System.out.println(hordesNumber);
        if(hordesNumber <= 0 || hordesNumber>10)throw new PVZException(PVZException.ERROR_INCORRECT_HORDES_NUMBER);
    }

    /*
     * Validate the hordes time is correct.
     * @throws PVZException, if the hordes time < 10.
     */
    private void valideHordesTime() throws PVZException {
        if(hordesTime > 10 || hordesTime <= 0)throw new PVZException(PVZException.ERROR_LOW_HORDES_TIME);
    }

    /*
     * Creates a button with an animated GIF.
     * @param gifFileName The name of the GIF file.
     * @param x The X position of the button.
     * @param y The Y position of the button.
     * @param width The width of the button.
     * @param height The height of the button.
     * @return A button configured with the animated GIF.
     */

    private JButton createGifButton(String gifFileName, int x, int y, int width, int height) {
        String baseDir = System.getProperty("user.dir");
        String gifPath = baseDir + File.separator + "gifs" + File.separator + gifFileName;

        ImageIcon gifIcon = new ImageIcon(gifPath);
        Image scaledImage = gifIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon scaledGifIcon = new ImageIcon(scaledImage);
        JButton gifButton = new JButton(scaledGifIcon);
        gifButton.setBounds(x, y, width, height);
        gifButton.setContentAreaFilled(false);

        return gifButton;
    }

}