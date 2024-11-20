package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Difficulty extends JFrame {
    private JMenuItem abrir, salvar, nuevo, salir;
    private JPanel mainPanel; // Panel principal con fondo y botones
    private JButton dia, retroceder, facil, medio, dificil;

    public Difficulty() {
        super("Selección de dificultad");
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        changeSizeToImage();
        createMainPanel();
        prepareElementsMenu();
        prepareBotones();
    }

    private ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }

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

    private void changeSizeToImage() {
        ImageIcon icon = getImageIcon("PantallaSeleccion.png");

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
        dia = new JButton("dia");
        retroceder = new JButton("retroceder");
        facil = new JButton("facil");
        medio = new JButton("medio");
        dificil = new JButton("dificil");

        // Configurar las posiciones absolutas de los botones
        facil.setBounds(300, 170, 120, 60);
        medio.setBounds(550, 170, 120, 60);
        dificil.setBounds(800, 170, 120, 60);
        dia.setBounds(200, 450, 95, 40);
        retroceder.setBounds(1150, 610, 100, 30);

        // Agregar los botones al fondo
        mainPanel.add(dia);
        mainPanel.add(retroceder);
        mainPanel.add(facil);
        mainPanel.add(medio);
        mainPanel.add(dificil);
    }

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });

        dia.addActionListener(e -> JOptionPane.showMessageDialog(Difficulty.this, "En construccion"));
        retroceder.addActionListener(e -> openPrincipalWindow());
        facil.addActionListener(e -> JOptionPane.showMessageDialog(Difficulty.this, "En construccion"));
        medio.addActionListener(e -> JOptionPane.showMessageDialog(Difficulty.this, "En construccion"));
        dificil.addActionListener(e -> JOptionPane.showMessageDialog(Difficulty.this, "En construccion"));

    }

    private void openPrincipalWindow(){
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres volver a la pantalla de inicio?", "Confirmar retroceder",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION){
        PVZGUI pvzguiwindow = new PVZGUI();
        pvzguiwindow.setVisible(true);

        dispose();
        }
    }

    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Difficulty difficulty = new Difficulty();
    }
}

