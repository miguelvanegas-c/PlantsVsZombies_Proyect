
import javax.swing.*;
import java.awt.*;
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
public class Difficulty extends JFrame implements GeneralInterface{
    private JMenuItem open, save, newItem, exit;
    private JPanel mainPanel;
    private JButton  back, sunflower, peashooter, wallnut, play, select, next, zombie, coneZombie, bucketZombie ;
    private HashSet<String> plantsToPlay = new HashSet<>();
    private HashSet<String> zombiesToPlay = new HashSet<>();
    private String gameMode,possiblePlantToPlay, plantPlayerName, zombiePlayerName, zombieType, plantType, possibleZombieToPlay;
    private JComboBox<String> selectPlant,selectZombie;
    private JTextField plantPlayerNameField, zombiePlayerNameField;

    /**
     * constructor of the Difficulty class.
     * @param gameMode, the chosen game mode.
     */
    public Difficulty(String gameMode) {
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
    private void createPlantsElectionPanel(){
        ImageIcon icon = getImageIcon("pantallaSeleccion.png");
        Image originalImage = icon.getImage();
        icon = getImageIcon("peashooter1.png");
        Image peashooterImage = icon.getImage();
        icon = getImageIcon("sunflower1.png");
        Image sunflowerImage = icon.getImage();
        icon = getImageIcon("wallnut1.png");
        Image wallnutImage = icon.getImage();
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);

                //Posibles plantas a agregar.
                g.drawImage(peashooterImage, 150, 155, 100, 100, null);
                g.drawImage(sunflowerImage, 250, 155, 100, 100, null);
                g.drawImage(wallnutImage, 350, 155, 100, 100, null);

                //Informacion de la planta seleccionada.
                if(possiblePlantToPlay != null){
                    ImageIcon icon = getImageIcon(possiblePlantToPlay+"I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }

                //Zona de plantas ya seleccionadas.
                g.setColor(new Color(139, 69, 19));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(101, 67, 33));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for(String planta : plantsToPlay){
                    ImageIcon icon = getImageIcon(planta+"1.png");
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
        String[] opcionesPlantas = {"PlantsIntellIgent","PlantsStrategic"};
        selectPlant = new JComboBox<>(opcionesPlantas);
        selectPlant.setBounds(100,50,200,40);
        if(gameMode.equals("MvsM")){
            mainPanel.add(selectPlant);
        }
    }

    /*
     * Prepare the elements for the choice of player name for the plants.
     */

    private void prepareNameElectionToPlants(){
        JLabel nombre = new JLabel("Nombre del jugador:");
        nombre.setBounds(70,50,150,30);

        plantPlayerNameField = new JTextField();
        plantPlayerNameField.setBounds(200, 50, 200, 30);
        if(!gameMode.equals("MvsM")){
            mainPanel.add(plantPlayerNameField);
            mainPanel.add(nombre);
        }
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
        select = new EspecialButton("Select");
        peashooter.setBounds(150, 155, 100, 100);
        sunflower.setBounds(250, 155, 100, 100);
        wallnut.setBounds(350, 155, 100,100);
        select.setBounds(845,460,140,30);

        mainPanel.add(peashooter);
        mainPanel.add(sunflower);
        mainPanel.add(wallnut);
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
        next.addActionListener(e -> nextPanel());
        select.addActionListener(e -> selectPlant());
        selectPlant.addActionListener(e -> selectPlantType());
        plantPlayerNameField.addActionListener(e -> selectPlantName());
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
        try{
            validePlantsToPlay();
            createZombiesElectionPanel();
            prepareButtonsToZombiesElection();
            prepareTypeElectionToZombies();
            prepareNameElectionToZombies();
            prepareActionsToZombiesElection();
            setVisible(true);
        }catch(PVZException e){
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);

                //Posibles zombies a agregar.
                g.drawImage(zombieImage, 150, 155, 100, 100, null);
                g.drawImage(zombieConoImage, 250, 155, 100, 100, null);
                g.drawImage(zombieBaldeImage, 350, 155, 100, 100, null);

                //Informacion de la planta seleccionada.
                if(possibleZombieToPlay != null){
                    ImageIcon icon = getImageIcon(possibleZombieToPlay+"I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }

                //Zona de plantas ya seleccionadas.
                g.setColor(new Color(0, 0, 0));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(64, 64, 64));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for(String zombie : zombiesToPlay){
                    ImageIcon icon = getImageIcon(zombie+".png");
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
     * prepare buttons to zombies election.
     */
    private void prepareButtonsToZombiesElection() {
        zombie = createGifButton("zombie.gif", 150, 155, 100, 100);
        coneZombie= createGifButton("caraCono.gif", 250, 155, 100, 100);
        bucketZombie = createGifButton("caraCubeta.gif", 350, 155, 100, 100);
        select = new EspecialButton("Select");
        select.setBounds(860, 460, 140, 30);

        play = new EspecialButton("Jugar");
        play.setBounds(1100, 550, 150, 30);

        mainPanel.add(zombie);
        mainPanel.add(coneZombie);
        mainPanel.add(bucketZombie);
        mainPanel.add(select);
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
        select.addActionListener(e -> selectZombie());
        play.addActionListener(e -> openPVZInGameWindow());
        selectZombie.addActionListener(e -> selectZombieType());
        zombiePlayerNameField.addActionListener(e -> selectZombieName());

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
    private void openPVZInGameWindow(){
        try {
            valideZombiesToPlay();
            PVZInGame pvzInGameWindow = new PVZInGame(gameMode, plantsToPlay, zombiesToPlay);
            pvzInGameWindow.setVisible(true);
            dispose();
        }catch(Exception e){
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
        String[] opcionesZombies = {"ZombiesIntellIgent","ZombiesStrategic"};
        selectZombie = new JComboBox<>(opcionesZombies);
        selectZombie.setBounds(100,50,200,40);
        if(gameMode.equals("MvsM") || gameMode.equals("PvsM")){
            mainPanel.add(selectZombie);
        }
    }

    /*
     * Prepare the elements for the choice of player name for the zombies.
     */
    private void prepareNameElectionToZombies(){
        JLabel nombre = new JLabel("Nombre del jugador:");
        nombre.setBounds(70,50,150,30);

        zombiePlayerNameField = new JTextField();
        zombiePlayerNameField.setBounds(200, 50, 200, 30);
        if(gameMode.equals("PvsP")){
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
     * valid if plants were chosen to play.
     */
    private void validePlantsToPlay() throws PVZException {
        if(plantsToPlay.isEmpty()) {throw new PVZException(PVZException.NOT_PLANTS_CHOOSED_TO_PLAY);}
    }

    /*
     * valid if zombies were chosen to play.
     */
    private void valideZombiesToPlay() throws PVZException {
        if(zombiesToPlay.isEmpty()) {throw new PVZException(PVZException.NOT_ZOMBIES_CHOOSED_TO_PLAY);}
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