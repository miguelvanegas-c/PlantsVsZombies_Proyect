package presentation;

import javax.swing.*;
import java.awt.*;

public class DayMap extends JFrame {
    private final int row = 5;
    private final int col = 8;
    private JPanel mainPanel;
    private JPanel tablero;


    public DayMap(){
        super("Día");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepareElements();

        setSize(600,600);
        add(mainPanel);
        setVisible(true);
    }

    private void prepareElements(){
        createMainPanel();
        panelSize();
        prepareButtons();
    }

    private ImageIcon getImageIcon(String fileName) {
        String baseDir = System.getProperty("user.dir");
        baseDir = baseDir.replace("/", "\\");
        String imagePath = baseDir + "\\images" + "\\" + fileName;

        return new ImageIcon(imagePath);
    }

    private void createMainPanel(){
        mainPanel = new JPanel();
        tablero = new JPanel();

//        tablero = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
//            }
//        };

        mainPanel.setLayout(new BorderLayout());
        tablero.setLayout(new GridLayout(row, col));
        formarTablero();
    }

    private void formarTablero(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                JButton celda = new JButton();

                // Alternar colores como un tablero de ajedrez
                if ((i + j) % 2 == 0) {
                    celda.setBackground(Color.GREEN);
                } else {
                    celda.setBackground(Color.LIGHT_GRAY);
                }
                tablero.add(celda);
            }
        }
    }

    private void panelSize(){
        setSize(600, 600);
    }

    private void prepareButtons(){
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton priBoton = new JButton("Boton1");
        JButton secBoton = new JButton("Boton2");
        JButton terBoton = new JButton("Boton3");
        JButton cuartBoton = new JButton("Boton4");

        mainPanel.add(tablero, BorderLayout.CENTER); // Tablero al centro
        mainPanel.add(priBoton, BorderLayout.NORTH);
        mainPanel.add(secBoton, BorderLayout.SOUTH);
        mainPanel.add(terBoton, BorderLayout.WEST);
        mainPanel.add(cuartBoton, BorderLayout.EAST);

    }

    public static void main(String[] args){
        DayMap dayMap = new DayMap();
    }

}
