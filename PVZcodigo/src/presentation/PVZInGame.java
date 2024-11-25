package presentation;


import domain.*;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

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
    private JButton[][] celdas = new JButton[5][11];
    private PVZ pvz;



    public PVZInGame(String gameMode, HashSet<String> plantasAJugar,HashSet<String> zombiesAJugar) {
        this.gameMode = gameMode;
        this.plantasAJugar = plantasAJugar.toArray(new String[0]);
        botonesPlantas = new JButton[this.plantasAJugar.length];
        this.zombiesAJugar = zombiesAJugar.toArray(new String[0]);
        pvz = new PVZ(this.plantasAJugar,this.zombiesAJugar, true);
        prepareElements();
        prepareAcciones();
        refresh();
        pvz.empiezaElJuego();
    }


    public void prepareElements() {
        prepareMenu();
        createPanel();
        if(gameMode.equals("PvsP")) prepareBotonesZombies();
        if(gameMode.equals("PvsP") || gameMode.equals("PvsM")) prepareBotonesPlantas();
        changeSizeToImage("fondoTablero.png");
        if(!gameMode.equals("MvsM"))prepareBottonesTablero();


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
                    g.drawImage(originalImagePlant, 20, count, 50, 70, null);
                    count += 80;
                }
                count = 20;
                for (String zombie : zombiesAJugar) {
                    ImageIcon iconZombie = getImageIcon(zombie + ".png");
                    Image originalImageZombie = iconZombie.getImage();
                    g.drawImage(originalImageZombie, 930, count, 70, 70, null);
                    count += 80;
                }
                for(Plant[] listaPlanta: pvz.getPlantasTablero()){
                    for(Plant plant : listaPlanta){
                        if(plant != null) {
                            ImageIcon imageIcon = getImageIcon(plant.getName() + "G.png");
                            Image image = imageIcon.getImage();
                            g.drawImage(image, plant.getXPosition(), plant.getYPosition(), 60, 65, null);
                        }
                    }
                }
                for(List<Zombie>[] matrizZombies: pvz.getZombiesTablero()) {
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
        int fila = 20;
        int len;
        len = plantasAJugar.length;
        botonesPlantas = new JButton[len];
        for (int i = 0; i < len; i++) {
            botonesPlantas[i] = new BorderButton(plantasAJugar[i]);
            botonesPlantas[i].setBounds(20,fila,50,70);
            fila += 80;
            gamePanel.add(botonesPlantas[i]);
        }
    }

    private void prepareBotonesZombies() {
        int count = 0;
        int fila = 20;
        int len;
        len = zombiesAJugar.length;
        botonesZombies = new JButton[len];
        for (int i = 0; i < len; i++) {
            botonesZombies[i] = new BorderButton(zombiesAJugar[i]);
            botonesZombies[i].setBounds(20,fila,70,70);
            fila += 80;
            gamePanel.add(botonesZombies[i]);
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
        Timer timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        timer.start();


    }

    private void addPlant(int row, int col) {
        try {
            pvz.addPlant(row, col, plantaSeleccionada);
            gamePanel.repaint();
        }catch(PVZException e) {
            JDialog dialog = new JDialog();
            dialog.setTitle(e.getMessage());
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null); // Centrar en la pantalla
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            new Timer(2000, s -> dialog.dispose()).start();
        }
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
