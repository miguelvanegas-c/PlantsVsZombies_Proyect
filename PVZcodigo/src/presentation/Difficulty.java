package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.HashSet;
/**
 * Esta es la pantalla de configuracion de un nuevo juego para la interfaz del juego POOB vs ZOMBIES.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class Difficulty extends JFrame {
    private JMenuItem abrir, salvar, nuevo, salir;
    private JPanel mainPanel; // Panel principal con fondo y botones
    private JButton dia, retroceder, facil, medio, dificil,sunflower,peashooter,wallnut, jugar;
    private JLabel plantasSeleccionadas;
    //Configuracion del juego para crear el tablero.
    private HashSet<String> plantasAJugar = new HashSet<>();
    private String gameMode;

    /**
     * constructor de la clase Difficulty..
     * @param gameMode, el modo de juego elegido.
     */
    public Difficulty(String gameMode) {
        super("Selección de dificultad");
        this.gameMode = gameMode;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    /*
     * prepara los elementos para la ventana.
     */

    private void prepareElements() {
        changeSizeToImage("PantallaSeleccion.png");
        createMainPanel();
        prepareElementsMenu();
        prepareBotones();
    }

    /*
     * Busca el ImageIcon de un archivo de imagen.
     * @param fileName, nombre del archivo.
     * @return retorna el ImageIcon del archivo.
     */
    private ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }

    /*
     * crear el panel principal para la eleccion de dificultad de un nuevo juego.
     */
    private void createMainPanel() {
        // Crear el panel principal con fondo
        ImageIcon icon = getImageIcon("PantallaSeleccion.png");
        Image originalImage = icon.getImage();

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
            }
        };

        mainPanel.setLayout(null); // Usar layout absoluto para colocar botones
        setContentPane(mainPanel);
    }

    /*
     * cambia el tamaño de la ventana al de la imagen de fondo.
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
     * Prepara los elementos para el menu, para los items de abrir, salvar, nuevo y salir.
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
     * prepara los botones para elegir la dificultad del juego.
     */
    private void prepareBotones() {
        dia = new EspecialButton("dia");
        retroceder = new EspecialButton("retroceder");
        facil = new EspecialButton("facil");
        medio = new EspecialButton("medio");
        dificil = new EspecialButton("dificil");

        // Configurar las posiciones absolutas de los botones
        facil.setBounds(300, 170, 120, 60);
        medio.setBounds(550, 170, 120, 60);
        dificil.setBounds(800, 170, 120, 60);
        dia.setBounds(200, 450, 95, 40);
        retroceder.setBounds(1100, 610, 150, 30);

        // Agregar los botones al fondo
        mainPanel.add(dia);
        mainPanel.add(retroceder);
        mainPanel.add(facil);
        mainPanel.add(medio);
        mainPanel.add(dificil);
    }

    /*
     * prepara las acciones para la eleccion de dificultad y los items del menu.
     */
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        dia.addActionListener(e -> JOptionPane.showMessageDialog(Difficulty.this, "En construccion"));
        retroceder.addActionListener(e -> openPrincipalWindow());
        facil.addActionListener(e -> plantsElectionToPlay());
        medio.addActionListener(e -> plantsElectionToPlay());
        dificil.addActionListener(e -> plantsElectionToPlay());
        salir.addActionListener(e -> closeWindowAction());
        nuevo.addActionListener(e -> openPrincipalWindow());

    }

    /*
     * Abre la ventana inicial dle juego POOB vs ZOMBIES.
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
     * Prepara los elementos para la eleccion de las plantas para un juego nuevo.
     */
    private void plantsElectionToPlay() {
        createPlantsElectionPanel();
        prepareBotonesToPlantsElection();
        prepareActionsToPlantsElection();
        setVisible(true);
    }

    /*
     * Crea el panel para la eleccion de plantas para el inicio de un juego.
     */
    private void createPlantsElectionPanel(){
        ImageIcon icon = getImageIcon("pantallaSeleccion.png");
        Image originalImage = icon.getImage();
        icon = getImageIcon("peashooter.png");
        Image peashooterImage = icon.getImage();
        icon = getImageIcon("sunflower.png");
        Image sunflowerImage = icon.getImage();
        icon = getImageIcon("wallnut.png");
        Image wallnutImage = icon.getImage();

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                //Posibles plantas a agregar.
                g.drawImage(peashooterImage, 65, 155, 100, 100, null);
                g.drawImage(sunflowerImage, 385, 155, 100, 100, null);
                g.drawImage(wallnutImage, 705, 155, 100, 100, null);
            }
        };
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
    }

    /*
     * prepara los elementos para la eleccion de plantas.
     */

    private void prepareBotonesToPlantsElection() {
        peashooter = new BorderButton("     ");
        wallnut = new BorderButton("      ");
        sunflower = new BorderButton("       ");

        //Boton para jugar
        jugar = new EspecialButton("Jugar");
        jugar.setBounds(1100, 550, 150, 30);

        //Botones encima de cada planta para poder elegirlas
        peashooter.setBounds(65, 155, 100, 100);
        sunflower.setBounds(385, 155, 100, 100);
        wallnut.setBounds(705, 155, 100, 100);


        mainPanel.add(peashooter);
        mainPanel.add(sunflower);
        mainPanel.add(wallnut);
        mainPanel.add(retroceder);
        mainPanel.add(jugar);

        //mostrar plantas ya elegidas en un texto.
        plantasSeleccionadas = new JLabel("La plantas seleccionadas son las siguientes:");
        plantasSeleccionadas.setBounds(100, 400, 1000, 70);
        plantasSeleccionadas.setForeground(Color.RED);
        plantasSeleccionadas.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(plantasSeleccionadas);
    }

    /*
     * prepara las acciones para los botones de eleccion de plantas.
     */

    private void prepareActionsToPlantsElection() {
        peashooter.addActionListener(e -> choosePlant("peashooter"));
        sunflower.addActionListener(e -> choosePlant("sunflower"));
        wallnut.addActionListener(e -> choosePlant("wallnut"));

        jugar.addActionListener(e -> openPVZInGameWindow());
    }

    /*
     * Al oprimir una planta la selecciona para jugar.
     */

    private void choosePlant(String plant) {
        String text = plantasSeleccionadas.getText();
        if(!plantasAJugar.contains(plant)) {
            plantasSeleccionadas.setText(text + "  " + plant);
            plantasAJugar.add(plant);
        }
    }

    /*
     * Al oprimir jugar abre un PVZInGame.
     */
    private void openPVZInGameWindow(){
        try {
            validePlantasAJugar();
            PVZInGame pvzInGameWindow = new PVZInGame(gameMode, plantasAJugar);
            pvzInGameWindow.setVisible(true);
            dispose();
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
     * valida si se eligieron plantas para jugar.
     */
    private void validePlantasAJugar() throws PVZException {
        if(plantasAJugar.isEmpty()) {throw new PVZException(PVZException.NOT_PLANTS_CHOOSED_TO_PLAY);}
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



}



