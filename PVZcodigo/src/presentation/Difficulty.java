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
public class Difficulty extends JFrame implements GeneralInterface{
    private JMenuItem abrir, salvar, nuevo, salir;
    private JPanel mainPanel; // Panel principal con fondo y botones
    private JButton  retroceder, sunflower, peashooter, wallnut, jugar, seleccionar, siguiente, zombie, zombieCono, zombieBalde ;
    //Configuracion del juego para crear el tablero.
    private HashSet<String> plantasAJugar = new HashSet<>();
    private HashSet<String> zombiesAJugar = new HashSet<>();
    private String gameMode,posiblePlantaAJugar, nombreJugadorPlanta, nombreJugadorZombie, tipoDeZombie, tipoDePlanta, posibleZombieAJugar;
    private JComboBox<String> eleccionDePlanta,eleccionDeZombie;
    private JTextField namePlantsPlayer, nameZombiesPlayer;

    /**
     * constructor de la clase Difficulty..
     * @param gameMode, el modo de juego elegido.
     */
    public Difficulty(String gameMode) {
        super("Selección de dificultad");
        this.gameMode = gameMode;
        prepareElements();
        prepareActionsToPlantsElection();
        setVisible(true);
    }

    /*
     * prepara los elementos para la ventana.
     */

    private void prepareElements() {
        changeSizeToImage("PantallaSeleccion.png");
        createPlantsElectionPanel();
        prepareElementsMenu();
        prepareBotonesToPlantsElection();
        prepareNameElectionToPlants();
        prepareTypeElectionToPlants();
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
                g.drawImage(peashooterImage, 150, 155, 100, 100, null);
                g.drawImage(sunflowerImage, 250, 155, 100, 100, null);
                g.drawImage(wallnutImage, 350, 155, 100, 100, null);

                //Informacion de la planta seleccionada.
                if(posiblePlantaAJugar != null){
                    ImageIcon icon = getImageIcon(posiblePlantaAJugar+"I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }

                //Zona de plantas ya seleccionadas.
                g.setColor(new Color(139, 69, 19));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(101, 67, 33));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for(String planta : plantasAJugar){
                    ImageIcon icon = getImageIcon(planta+".png");
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
     * Prepara los elementos para la eleccion de tipo de maquina de plantas.
     */

    private void prepareTypeElectionToPlants() {
        String[] opcionesPlantas = {"PlantsIntellIgent","PlantsStrategic"};
        eleccionDePlanta = new JComboBox<>(opcionesPlantas);
        eleccionDePlanta.setBounds(100,50,200,40);
        if(gameMode.equals("MvsM")){
            mainPanel.add(eleccionDePlanta);
        }
    }

    /*
     * Prepara los elementos para la eleccion de nombre de jugador para las plantas.
     */
    private void prepareNameElectionToPlants(){
        JLabel nombre = new JLabel("Nombre del jugador:");
        nombre.setBounds(70,50,150,30);

        namePlantsPlayer = new JTextField();
        namePlantsPlayer.setBounds(200, 50, 200, 30);
        if(!gameMode.equals("MvsM")){
            mainPanel.add(namePlantsPlayer);
            mainPanel.add(nombre);
        }
    }


    /*
     * prepara los elementos para la eleccion de plantas.
     */

    private void prepareBotonesToPlantsElection() {
        //Botones encima de cada planta para poder elegirlas
        retroceder = new EspecialButton("retroceder");
        retroceder.setBounds(1100, 610, 150, 30);


        siguiente = new EspecialButton("siguiente");
        siguiente.setBounds(1100, 550, 150, 30);


        peashooter = new BorderButton("     ");
        wallnut = new BorderButton("      ");
        sunflower = new BorderButton("       ");
        seleccionar = new EspecialButton("Seleccionar");
        peashooter.setBounds(150, 155, 100, 100);
        sunflower.setBounds(250, 155, 100, 100);
        wallnut.setBounds(350, 155, 100, 100);
        seleccionar.setBounds(845,460,140,30);

        mainPanel.add(peashooter);
        mainPanel.add(sunflower);
        mainPanel.add(wallnut);
        mainPanel.add(retroceder);
        mainPanel.add(siguiente);

    }

    /*
     * prepara las acciones para los botones de eleccion de plantas.
     */

    private void prepareActionsToPlantsElection() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        retroceder.addActionListener(e -> openPrincipalWindow());
        salir.addActionListener(e -> closeWindowAction());
        nuevo.addActionListener(e -> openPrincipalWindow());
        peashooter.addActionListener(e -> choosePlant("peashooter"));
        sunflower.addActionListener(e -> choosePlant("sunflower"));
        wallnut.addActionListener(e -> choosePlant("wallnut"));
        siguiente.addActionListener(e -> siguientePanel());
        seleccionar.addActionListener(e -> selectPlant());
        eleccionDePlanta.addActionListener(e -> seleccionTipoDePlanta());
        namePlantsPlayer.addActionListener(e -> seleccionNombreDePlanta());
    }

    /*
     * Abre la ventana inicial del juego POOB vs ZOMBIES.
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
     * Al oprimir una planta la selecciona para jugar.
     */

    private void choosePlant(String plant) {
        posiblePlantaAJugar = plant;
        mainPanel.add(seleccionar);
        mainPanel.repaint();
    }

    /*
     *
     */
    private void selectPlant() {
        plantasAJugar.add(posiblePlantaAJugar);
        mainPanel.repaint();
    }
    /*
     *
     */
    private void siguientePanel() {
        try{
            validePlantasAJugar();
            createZombiesElectionPanel();
            prepareBotonesToZombiesElection();
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
     *
     */
    private void seleccionTipoDePlanta() {
        String seleccion = (String) eleccionDePlanta.getSelectedItem();
        tipoDePlanta = seleccion;
    }

    /*
     *
     */
    private void seleccionNombreDePlanta() {
        String nombre = namePlantsPlayer.getText();
        nombreJugadorPlanta = nombre;
    }


    private void createZombiesElectionPanel() {
        ImageIcon icon = getImageIcon("pantallaSeleccionZombies.png");
        Image originalImage = icon.getImage();
        icon = getImageIcon("zombie.png");
        Image zombieImage = icon.getImage();
        icon = getImageIcon("zombieBalde.png");
        Image zombieBaldeImage = icon.getImage();
        icon = getImageIcon("zombieCono.png");
        Image zombieConoImage = icon.getImage();
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);

                //Posibles plantas a agregar.
                g.drawImage(zombieImage, 150, 155, 100, 100, null);
                g.drawImage(zombieConoImage, 250, 155, 100, 100, null);
                g.drawImage(zombieBaldeImage, 350, 155, 100, 100, null);

                //Informacion de la planta seleccionada.
                if(posibleZombieAJugar != null){
                    ImageIcon icon = getImageIcon(posibleZombieAJugar+"I.png");
                    Image image = icon.getImage();
                    g.drawImage(image, 700, 100, 310, 400, null);

                }

                //Zona de plantas ya seleccionadas.
                g.setColor(new Color(0, 0, 0));
                g.fillRect(150, 520, 800, 120);

                g.setColor(new Color(64, 64, 64));
                g.fillRect(160, 530, 780, 100);
                int x = 160;

                for(String zombie : zombiesAJugar){
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

    private void prepareBotonesToZombiesElection() {
        zombie = new BorderButton("     ");
        zombieCono = new BorderButton("      ");
        zombieBalde = new BorderButton("       ");
        seleccionar = new EspecialButton("Seleccionar");
        zombie.setBounds(150, 155, 100, 100);
        zombieCono.setBounds(250, 155, 100, 100);
        zombieBalde.setBounds(350,155,100,100);
        seleccionar.setBounds(860,460,140,30);

        jugar = new EspecialButton("Jugar");
        jugar.setBounds(1100, 550, 150, 30);

        mainPanel.add(zombie);
        mainPanel.add(zombieCono);
        mainPanel.add(zombieBalde);
        mainPanel.add(retroceder);
        mainPanel.add(jugar);
    }

    private void prepareActionsToZombiesElection() {
        zombie.addActionListener(e -> chooseZombie("zombie"));
        zombieCono.addActionListener(e -> chooseZombie("zombieCono"));
        zombieBalde.addActionListener(e -> chooseZombie("zombieBalde"));
        seleccionar.addActionListener(e -> selectZombie());
        jugar.addActionListener(e -> openPVZInGameWindow());
        eleccionDeZombie.addActionListener(e -> seleccionTipoDeZombie());
        nameZombiesPlayer.addActionListener(e -> seleccionNombreDeZombie());

    }

    /*
     * Permite elegir un zombie para poder seleccionarlo.
     * @param zombie, nombre del zombie que se ha elgido
     */
    private void chooseZombie(String zombie) {
        posibleZombieAJugar = zombie;
        mainPanel.add(seleccionar);
        mainPanel.repaint();
    }

    /*
     * Seleccion el zombie que este elegido.
     */
    private void selectZombie() {
        zombiesAJugar.add(posibleZombieAJugar);
        mainPanel.repaint();
    }


    /*
     * Al oprimir jugar abre un PVZInGame.
     */
    private void openPVZInGameWindow(){
        try {
            valideZombiesAJugar();
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
     * configura el comportamiento al cerrar la ventana.
     */

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /*
     * Prepara los elementos para la eleccion de tipo de maquina de zombies.
     */

    private void prepareTypeElectionToZombies() {
        String[] opcionesZombies = {"ZombiesIntellIgent","ZombiesStrategic"};
        eleccionDeZombie = new JComboBox<>(opcionesZombies);
        eleccionDeZombie.setBounds(100,50,200,40);
        if(gameMode.equals("MvsM") || gameMode.equals("PvsM")){
            mainPanel.add(eleccionDeZombie);
        }
    }

    /*
     * Prepara los elementos para la eleccion de nombre de jugador para los zombies.
     */
    private void prepareNameElectionToZombies(){
        JLabel nombre = new JLabel("Nombre del jugador:");
        nombre.setBounds(70,50,150,30);

        nameZombiesPlayer = new JTextField();
        nameZombiesPlayer.setBounds(200, 50, 200, 30);
        if(gameMode.equals("PvsP")){
            mainPanel.add(nameZombiesPlayer);
            mainPanel.add(nombre);
        }
    }

    /*
     *
     */
    private void seleccionTipoDeZombie() {
        String seleccion = (String) eleccionDePlanta.getSelectedItem();
        tipoDeZombie = seleccion;
    }

    /*
     *
     */
    private void seleccionNombreDeZombie() {
        String nombre = namePlantsPlayer.getText();
        nombreJugadorZombie = nombre;
    }

    /*
     * valida si se eligieron plantas para jugar.
     */
    private void validePlantasAJugar() throws PVZException {
        if(plantasAJugar.isEmpty()) {throw new PVZException(PVZException.NOT_PLANTS_CHOOSED_TO_PLAY);}
    }

    /*
     * valida si se eligieron zombies para jugar.
     */
    private void valideZombiesAJugar() throws PVZException {
        if(zombiesAJugar.isEmpty()) {throw new PVZException(PVZException.NOT_ZOMBIES_CHOOSED_TO_PLAY);}
    }


}



