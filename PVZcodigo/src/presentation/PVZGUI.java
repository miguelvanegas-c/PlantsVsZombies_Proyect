
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * This is the main screen of the interface for the game POOB vs ZOMBIES.
 *
 * @author Miguel Angel Vanegas and Julian Castiblanco.
 * @version 1.0
 */

public class PVZGUI extends JFrame implements GeneralInterface {
    private JMenuItem open, save, newItem, exitMenuItem;
    private JPanel background; // Changed from JLabel to JPanel
    private JButton PvsP, MvsM, PvsM, controls, exit;
    private static Clip clip;
    private static boolean isMusicPlaying = false;

    /**
     * Constructor for the PVZGUI class.
     */
    public PVZGUI() {
        super("POOB vs Zombies");
        prepareElements();
        prepareActions();

        if (!isMusicPlaying) {
            playMusic(getClip("Plants-vs-Zombies-Main-Theme.wav"));
            isMusicPlaying = true;
        } else {
            restartMusic();
        }

        setVisible(true);
    }

    /**
     * Prepares all the interface elements.
     */
    private void prepareElements() {
        resizeToBackgroundImage();
        createBackground();
        prepareMenuElements();
        prepareButtons();
    }

    /*
     * Creates the background for PVZGUI.
     */
    private void createBackground() {
        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = getImageIcon("fondoConNombre.png");
                Image backgroundImage = icon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        background.setLayout(null); // Allows absolute positioning of components
        setContentPane(background);
    }

    /*
     * Sets the size of PVZGUI to match the background image.
     */
    private void resizeToBackgroundImage() {
        ImageIcon icon = getImageIcon("fondoConNombre.png");

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null); // Centers the JFrame on the screen
        setResizable(false);
    }

    /*
     * Prepares the menu elements for opening, saving, creating new files, and exiting.
     */
    private void prepareMenuElements() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        newItem = new JMenuItem("New");
        exitMenuItem = new JMenuItem("Exit");

        menuBar.add(menu);
        menu.add(open);
        menu.add(save);
        menu.add(newItem);
        menu.add(exitMenuItem);

        setJMenuBar(menuBar);
    }

    /*
     * Prepares the buttons for the interface.
     */
    private void prepareButtons() {
        PvsP = new EspecialButton("PvsP");
        MvsM = new EspecialButton("MvsM");
        PvsM = new EspecialButton("PvsM");
        controls = new TransparentButton("       ");
        exit = new TransparentButton("    ");

        // Configuring absolute positions for buttons
        PvsP.setBounds(550, 70, 120, 60);
        MvsM.setBounds(550, 170, 120, 60);
        PvsM.setBounds(550, 270, 120, 60);
        controls.setBounds(625, 440, 88, 30);
        exit.setBounds(790, 450, 60, 30);

        // Adding buttons to the background
        background.add(PvsP);
        background.add(MvsM);
        background.add(PvsM);
        background.add(controls);
        background.add(exit);
    }

    /*
     * Prepares actions for the buttons and menu items.
     */
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        open.addActionListener(e -> closeWindowAction());
        PvsP.addActionListener(e -> openDifficultyWindow("PvsP"));
        MvsM.addActionListener(e -> openDifficultyWindow("MvsM"));
        PvsM.addActionListener(e -> openDifficultyWindow("PvsM"));
        controls.addActionListener(e -> JOptionPane.showMessageDialog(PVZGUI.this, "Controls"));
        exit.addActionListener(e -> closeWindowAction());
    }

    /*
     * Opens another window for configuring a new game.
     * @param gameMode, selected game mode.
     */
    private void openDifficultyWindow(String gameMode) {
        Configuration difficultyWindow = new Configuration(gameMode);
        difficultyWindow.setVisible(true);

        dispose();
    }

    /*
     * Configures window behavior when attempting to close it.
     */
    private void closeWindowAction() {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_NO_OPTION) {
            stopMusic();
            System.exit(0);
        }
    }

    /*
     * Starts playing the music.
     * @param filePath, path to the music file.
     */
    private void playMusic(String filePath) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }

            File musicFile = new File(filePath);
            if (!musicFile.exists()) {
                System.out.println("Music file does not exist: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Play in loop
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /*
     * Restarts the music if it is currently playing.
     */
    private void restartMusic() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /*
     * Stops the music if it is currently playing.
     */
    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void main(String[] args) {
        PVZGUI pvzGUI = new PVZGUI();
    }
}