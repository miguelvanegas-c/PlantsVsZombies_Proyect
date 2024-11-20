package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class PVZGUI extends JFrame {
    private JMenuItem abrir, salvar, nuevo, salir;
    private JPanel fondo; // Cambiado de JLabel a JPanel
    private JButton PvsP, MvsM, PvsM, controles, exit;
    private static Clip clip;
    private static boolean isMusicPlaying = false;

    public PVZGUI() {
        super("POOB vs Zombies");
        prepareElements();
        prepareActions();

        if (!isMusicPlaying) {
            playMusic(gertClip("Plants-vs-Zombies-Main-Theme.wav"));
            isMusicPlaying = true;
        } else {
            restartMusic();
        }

        setVisible(true);
    }

    private void prepareElements() {
        changeSizeToImage();
        createFondo();
        prepareElementsMenu();
        prepareBotones();
    }

    private String gertClip(String fileName){
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String musicPath = baseDir + "\\Sounds" + "\\" + fileName;

        return musicPath;
    }

    private ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }

    private void createFondo() {
        fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = getImageIcon("fondoFinal.png");
                Image backGroundImage = icon.getImage();
                g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        fondo.setLayout(null); // Para colocar componentes de forma absoluta
        setContentPane(fondo);
    }

    private void changeSizeToImage() {
        ImageIcon icon = getImageIcon("fondoFinal.png");

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null); // Centrar el JFrame en la pantalla
        setResizable(false);
    }

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

    private void prepareBotones() {
        PvsP = new JButton("PvsP");
        MvsM = new JButton("MvsM");
        PvsM = new JButton("PvsM");
        controles = new JButton("Opciones");
        exit = new JButton("Salir");

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

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        salir.addActionListener(e -> closeWindowAction());
        PvsP.addActionListener(e -> JOptionPane.showMessageDialog(PVZGUI.this, "En construcción"));
        MvsM.addActionListener(e -> openDifficultyWindow());
        PvsM.addActionListener(e -> openDifficultyWindow());
        controles.addActionListener(e -> JOptionPane.showMessageDialog(PVZGUI.this, "Controles"));
        exit.addActionListener(e -> closeWindowAction());
    }

    private void openDifficultyWindow(){
        Difficulty difficultyWindow = new Difficulty();
        difficultyWindow.setVisible(true);

        dispose();
    }

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            stopMusic();
            System.exit(0);
        }
    }

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

    private void restartMusic(){
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void main(String[] args) {
        PVZGUI pvzGUI = new PVZGUI();
    }
}