package presentation;

import javax.swing.*;
import java.awt.*;

public class Pruebas extends JFrame {
    private final int SIZE = 8; // Tamaño del tablero
    private JButton[][] cells = new JButton[SIZE][SIZE]; // Celdas del tablero
    private String[][] board = new String[SIZE][SIZE];   // Estado lógico del tablero
    private Point selectedCell = null; // Celda seleccionada (fila, columna)

    public Pruebas() {
        super("Juego de Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // Crear el panel principal
        JPanel chessBoard = new JPanel(new GridLayout(SIZE, SIZE));
        prepareChessBoard(chessBoard);

        add(chessBoard);
        setVisible(true);
    }

    private void prepareChessBoard(JPanel chessBoard) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Crear cada celda como un botón
                JButton cell = new JButton();
                cells[i][j] = cell;

                // Alternar colores para simular un tablero de ajedrez
                if ((i + j) % 2 == 0) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.GRAY);
                }

                cell.setOpaque(true);
                cell.setBorderPainted(false);

                // Inicializar piezas en las filas correspondientes
                if (i == 1) {
                    board[i][j] = "♟"; // Peones negros
                } else if (i == 6) {
                    board[i][j] = "♙"; // Peones blancos
                } else {
                    board[i][j] = ""; // Celdas vacías
                }

                // Mostrar la pieza en el botón
                cell.setText(board[i][j]);

                // Añadir ActionListener para manejar clics
                final int row = i;
                final int col = j;
                cell.addActionListener(e -> handleCellClick(row, col));

                // Añadir la celda al panel del tablero
                chessBoard.add(cell);
            }
        }
    }

    private void handleCellClick(int row, int col) {
        if (selectedCell == null) {
            // Seleccionar una celda inicial
            if (!board[row][col].isEmpty()) {
                selectedCell = new Point(row, col);
                cells[row][col].setBackground(Color.YELLOW); // Indicar selección
            }
        } else {
            // Mover la pieza a la celda destino
            int startRow = selectedCell.x;
            int startCol = selectedCell.y;

            // Actualizar el estado lógico del tablero
            board[row][col] = board[startRow][startCol];
            board[startRow][startCol] = "";

            // Actualizar la interfaz gráfica
            cells[row][col].setText(board[row][col]);
            cells[startRow][startCol].setText("");

            // Restaurar los colores originales
            cells[startRow][startCol].setBackground((startRow + startCol) % 2 == 0 ? Color.WHITE : Color.GRAY);
            cells[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);

            selectedCell = null; // Deseleccionar la celda
        }
    }

    public static void main(String[] args) {
        new Pruebas();
    }
}
