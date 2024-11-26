

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * This is the window of the gameBoard.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class PVZInGame extends JFrame implements GeneralInterface {
    private JPanel gamePanel;
    private JMenuItem open, save, newItem, exit;
    private String gameMode, selectedPlant;
    private String[] plantsToPlay;
    private String[] zombiesToPlay;
    private JButton[] plantsButtons;
    private JButton[] zombiesButtons;
    private JButton[][] cells = new JButton[5][11];
    private PVZ pvz;


    /**
     * Constructor for PVZInGame.
     *
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     */
    public PVZInGame(String gameMode, HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, true);
        prepareElements();
        prepareActions();
        refresh();

    }
    /*
     * Starts the game logic.
     */
    private void startGame(){
        pvz.startingGame();
    }
    /*
     * Prepares all the graphical and logical components of the game.
     */
    private void prepareElements() {
        prepareElementsMenu();
        createPanel();
        if(gameMode.equals("PvsP")) prepareButtonsZombies();
        if(gameMode.equals("PvsP") || gameMode.equals("PvsM")) prepareButtonsPlantas();
        changeSizeToImage("fondoTablero.png");
        if(!gameMode.equals("MvsM"))prepareButtonsTablero();


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
                //Toca añadir el dibujo de los zombies ya sea para MvsM o PvsP.
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                int count = 20;
                for (String planta : plantsToPlay) {
                    ImageIcon iconPlant = getImageIcon(planta+".png");
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 50, 70, null);
                    count += 80;
                }
                count = 20;
                for (String zombie : zombiesToPlay) {
                    ImageIcon iconZombie = getImageIcon(zombie + ".png");
                    Image originalImageZombie = iconZombie.getImage();
                    g.drawImage(originalImageZombie, 930, count, 70, 70, null);
                    count += 80;
                }
                for(Plant[] listaPlanta: pvz.getPlantsBoard()){
                    for(Plant plant : listaPlanta){
                        if(plant != null) {
                            ImageIcon imageIcon = getImageIcon(plant.getName() + "G.png");
                            Image image = imageIcon.getImage();
                            g.drawImage(image, plant.getXPosition(), plant.getYPosition(), 60, 65, null);
                        }
                    }
                }
                for(List<Zombie>[] matrizZombies: pvz.getZombiesBoard()) {
                    for (List<Zombie> listaDeZombie : matrizZombies) {
                        for (Zombie zombie : listaDeZombie) {
                            if (zombie != null) {
                                ImageIcon iconZombie = getImageIcon("zombieG.png");
                                Image originalImageZombie = iconZombie.getImage();
                                g.drawImage(originalImageZombie, zombie.getXPosition(), zombie.getYPosition(), 70, 70, null);

                            }
                        }
                    }
                }
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
    private void prepareButtonsPlantas() {
        int row = 20;
        int len;
        len = plantsToPlay.length;
        plantsButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            plantsButtons[i] = new BorderButton(plantsToPlay[i]);
            plantsButtons[i].setBounds(20,row,50,70);
            row += 80;
            gamePanel.add(plantsButtons[i]);
        }
    }

    /*
     * Prepares the buttons representing zombies available for the player.
     * Buttons are aligned vertically on the left side of the game panel.
     */
    private void prepareButtonsZombies() {
        int count = 0;
        int row = 20;
        int len;
        len = zombiesToPlay.length;
        zombiesButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            zombiesButtons[i] = new BorderButton(zombiesToPlay[i]);
            zombiesButtons[i].setBounds(20,row,70,70);
            row += 80;
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
        open.addActionListener(e -> closeWindowAction());
        newItem.addActionListener(e -> openPrincipalWindow());


        int plantsLen = plantsButtons.length;
        for(int i = 0; i < plantsLen; i++) {
            String plant = plantsToPlay[i];
            if (plantsButtons[i] != null) plantsButtons[i].addActionListener(e ->selectPlant(plant));
        }
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 10; col++) {
                int i = row;
                int j = col;
                if (cells[i][j] != null) cells[row][col].addActionListener(e -> addPlant(i,j));
            }
        }

        Timer repaintTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        repaintTimer.start();

        Timer startGameTimer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                startGame();
                ((Timer) e.getSource()).stop();
            }
        });
        startGameTimer.start();

    }
    /*
     * Adds a plant to the game board at the specified row and column.
     *
     * @param row The row index where the plant will be added.
     * @param col The column index where the plant will be added.
     */
    private void addPlant(int row, int col) {
        try {
            pvz.addPlant(row, col, selectedPlant);
            gamePanel.repaint();
        } catch (PVZException e) {
            JDialog dialog = new JDialog();
            JLabel label = new JLabel(e.getMessage());
            label.setBounds(20, 20, 70, 70);
            dialog.setTitle("ERROR");
            dialog.add(label);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null); // Centrar en la pantalla
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            new Timer(1000, s -> dialog.dispose()).start();
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

    /*
     * Opens the main menu window and closes the current game window.
     */
    private void openPrincipalWindow() {
        int opcion = JOptionPane.showConfirmDialog(this, "Do you want to come to the start screen?", "Confirm back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
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
     * Refreshes the game panel by repainting it.
     */
    private void refresh(){
        gamePanel.repaint();
    }

}
