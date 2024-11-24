package presentation;


import domain.PVZ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Esta es la pantalla del tablero para el juego POOB vs ZOMBIES.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class PVZInGame extends JFrame implements GeneralInterface {
    private JPanel gamePanel;
    private JMenuItem abrir, salvar, nuevo, salir;
    private String gameMode, plantaSeleccionada;
    private String[] plantasAJugar;
    private String[] zombiesAJugar;
    private JButton[] botonesPlantas;
    private JButton[] botonesZombies;
    private JButton[][] celdas = new JButton[5][10];
    private PVZ pvz;



    public PVZInGame(String gameMode, HashSet<String> plantasAJugar) {
        this.gameMode = gameMode;
        this.plantasAJugar = plantasAJugar.toArray(new String[0]);
        botonesPlantas = new JButton[this.plantasAJugar.length];
        pvz = new PVZ();
        prepareElements();
        prepareAcciones();
        refresh();
    }


    public void prepareElements() {
        prepareMenu();
        createPanel();
        prepareBotonesZombies();
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
                    ImageIcon iconPlant = getImageIcon(planta+".png");
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 90, 90, null);
                    count += 100;
                }
                //Se hara un ciclo para recorrer el tablero de PVZ para nuevos cambios en el.
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
        int columna = 140;
        int fila =55;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                celdas[row][col] = new BorderButton("");
                celdas[row][col].setBounds(columna,fila,70,75);
                gamePanel.add(celdas[row][col]);
                columna +=70 ;
            }
            columna = 140;
            fila += 75 ;
        }
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
        int len;
        if (zombiesAJugar != null) {
            len = zombiesAJugar.length;
        }
        len = 0;
        botonesZombies = new JButton[len];
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
        nuevo.addActionListener(e -> openPrincipalWindow());

        // De aqui en adelanta todo lo escrito es para interactuar con el juego directamente
        int longitudPlantas = botonesPlantas.length;
        for(int i = 0; i < longitudPlantas; i++) {
            String planta = plantasAJugar[i];
            if (botonesPlantas[i] != null) botonesPlantas[i].addActionListener(e ->seleccionarPlanta(planta));
        }
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 10; col++) {
                int i = row;
                int j = col;
                if (celdas[i][j] != null) celdas[row][col].addActionListener(e -> addPlant(i,j));
            }
        }
        //El timer es para mover el juego e interactuar con el.
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                System.out.println(1);
            }
        });
        timer.start();
    }

    private void addPlant(int row, int col) {
        pvz.addPlant(row,col);
    }

    private void seleccionarPlanta(String planta){
        plantaSeleccionada = planta;
    }

    private void openPrincipalWindow() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres volver a la pantalla de inicio?", "Confirmar retroceder", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            PVZGUI pvzguiwindow = new PVZGUI();
            pvzguiwindow.setVisible(true);

            dispose();
        }
    }
    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }
    private void refresh(){
        gamePanel.repaint();
    }

}
