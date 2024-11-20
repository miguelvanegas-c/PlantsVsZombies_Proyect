package presentation;

import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PVZInGame extends JFrame {
    private JPanel gamePanel;
    private JMenuItem abrir, salvar, nuevo, salir;
    private String gameMode;
    private String[] plantasAJugar;
    private JButton[] botonesPlantas;
    private JButton[] botonesZombies;
    private JButton[][] celdas = new JButton[5][8];


    public PVZInGame(String gameMode, HashSet<String> plantasAJugar) {
        this.gameMode = gameMode;
        this.plantasAJugar = plantasAJugar.toArray(new String[0]);
        botonesPlantas = new JButton[this.plantasAJugar.length];
        prepareElements();
        prepareAcciones();
    }




    private ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }


    public void prepareElements() {
        prepareMenu();

        // se crea el panel dependiendo del gameMode.
        if(gameMode.equals("PvsM")){
            createPvsMPanel();

        }
        if(gameMode.equals("PvsP") || gameMode.equals("MvsM")){
            createPanel();
            prepareBotonesZombies();
        }
        prepareBotonesPlantas();
        changeSizeToImage("fondoTablero.png");
        prepareBottonesTablero();


    }

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
                for (String planta : plantasAJugar) {
                    ImageIcon iconPlant = getImageIcon(planta);
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 90, 90, null);
                    count += 100;
                }
            }
        };

        gamePanel.setLayout(null); // Usar layout absoluto para colocar botones
        setContentPane(gamePanel);
    }
    private void createPvsMPanel() {
        // Crear panel con las plantas que se van a jugar
        ImageIcon icon = getImageIcon("fondoTablero.png");
        Image originalImage = icon.getImage();

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                int count = 20;
                for (String planta : plantasAJugar) {
                    ImageIcon iconPlant = getImageIcon(planta);
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 90, 90, null);
                    count += 100;
                }
            }
        };

        gamePanel.setLayout(null); // Usar layout absoluto para colocar botones
        setContentPane(gamePanel);
    }

    private void changeSizeToImage(String imageName) {
        ImageIcon icon = getImageIcon(imageName);

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void prepareMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        abrir= new JMenu("abrir");
        salvar = new JMenuItem("salvar");
        nuevo = new JMenuItem("nuevo");
        salir = new JMenuItem("salir");

        menu.add(abrir);
        menu.add(salvar);
        menu.add(nuevo);
        menu.add(salir);

        menuBar.add(menu);
        setJMenuBar(menuBar);

    }

    private void prepareBottonesTablero() {
        int columna = 210;
        int fila =55;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 8; col++) {
                celdas[row][col] = new BorderButton("");
                celdas[row][col].setBounds(columna,fila,70,75);
                gamePanel.add(celdas[row][col]);
                columna +=70 ;
            }
            columna = 210;
            fila += 75 ;
        }
        fila = 55;
    }

    private void prepareBotonesPlantas() {
        int count = 0;
        int fila = 20;
        for (JButton boton : botonesPlantas) {
            boton = new BorderButton(plantasAJugar[count]);
            boton.setBounds(20,fila,90,90);
            count++;
            fila += 100;
            gamePanel.add(boton);
        }
    }

    private void prepareBotonesZombies() {
        int count = 0;
        int fila = 20;
        botonesZombies = new JButton[4];
        for(JButton boton : botonesZombies) {
            boton = new BorderButton("zombie");
            boton.setBounds(930,fila,90,90);
            count++;
            fila += 100;
            gamePanel.add(boton);
        }
    }

    public void prepareAcciones(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });
        salir.addActionListener(e -> closeWindowAction());
    }

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }

}
