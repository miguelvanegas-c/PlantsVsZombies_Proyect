package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Esta es la pantalla inicial de la interfaz para el juego POOB vs ZOMBIES.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class PVZGUI extends JFrame implements GeneralInterface {
    private JMenuItem abrir, salvar, nuevo, salir;
    private JPanel fondo; // Cambiado de JLabel a JPanel
    private JButton PvsP, MvsM, PvsM, controles, exit;
    private static Clip clip;
    private static boolean isMusicPlaying = false;

    /**
     * creador de la clase PVZGUI.
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
     * prepara todos los elementos de la interfaz.
     */
    private void prepareElements() {
        changeSizeToImage();
        createFondo();
        prepareElementsMenu();
        prepareBotones();
    }


    /*
     * Crea el fondo del PVZGUI.
     */
    private void createFondo() {
        fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = getImageIcon("fondoConNombre.png");
                Image backGroundImage = icon.getImage();
                g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        fondo.setLayout(null); // Para colocar componentes de forma absoluta
        setContentPane(fondo);
    }


    /*
     * configura el tamaño de la PVZGUI a la imagen de fondo.
     */
    private void changeSizeToImage() {
        ImageIcon icon = getImageIcon("fondoConNombre.png");

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null); // Centrar el JFrame en la pantalla
        setResizable(false);
    }

    /*
     * prepara los elementus del menu, tanto para abrir, salvar, nuevo, salir.
     */
    private void prepareElementsMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        nuevo = new JMenuItem("Nuevo");
        salir = new JMenuItem("Salir");

        menuBar.add(menu);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(nuevo);
        menu.add(salir);

        setJMenuBar(menuBar);
    }

    /*
     * prepara los botones de la interfaz.
     */
    private void prepareBotones() {
        PvsP = new EspecialButton("PvsP");
        MvsM = new EspecialButton("MvsM");
        PvsM = new EspecialButton("PvsM");
        controles = new TransparentButton("       ");
        exit = new TransparentButton("    ");

        // Configurar las posiciones absolutas de los botones
        PvsP.setBounds(550, 70, 120, 60);
        MvsM.setBounds(550, 170, 120, 60);
        PvsM.setBounds(550, 270, 120, 60);
        controles.setBounds(625, 440, 88, 30);
        exit.setBounds(790, 450, 60, 30);

        // Agregar los botones al fondo
        fondo.add(PvsP);
        fondo.add(MvsM);
        fondo.add(PvsM);
        fondo.add(controles);
        fondo.add(exit);
    }

    /*
     * prepara las acciones tanto de los botones, como de los items del menu.
     */

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        salir.addActionListener(e -> closeWindowAction());
        PvsP.addActionListener(e -> openDifficultyWindow("PvsP"));
        MvsM.addActionListener(e -> openDifficultyWindow("MvsM"));
        PvsM.addActionListener(e -> openDifficultyWindow("PvsM"));
        controles.addActionListener(e -> JOptionPane.showMessageDialog(PVZGUI.this, "Controles"));
        exit.addActionListener(e -> closeWindowAction());
    }

    /*
     * Pasa a otra venta para la configuracion de un nuevo juego.
     * @param gameMode, modo de juego elegido.
     */
    private void openDifficultyWindow(String gameMode){
        Difficulty difficultyWindow = new Difficulty(gameMode);
        difficultyWindow.setVisible(true);

        dispose();
    }

    /*
     * Configura el comportamiento de la ventana al intentar cerrarla.
     */

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            stopMusic();
            System.exit(0);
        }
    }

    /*
     * Empieza a reproducir la musica.
     * @param filePath, ubicacion de el archivo de musica.
     */
    private void playMusic(String filePath) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }

            File musicFile = new File(filePath);
            if (!musicFile.exists()) {
                System.out.println("El archivo de música no existe: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /*
     * reinicia la reproduccion de la musica en caso de que haya musica reproduciendose.
     */
    private void restartMusic(){
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /*
     * Para la musica en caso de que se este reproduciendo.
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
