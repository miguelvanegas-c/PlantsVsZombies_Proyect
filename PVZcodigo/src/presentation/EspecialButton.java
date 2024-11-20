package presentation;

import javax.swing.*;
import java.awt.*;

public class EspecialButton extends JButton {
    // Constructor
    public EspecialButton(String text) {
        super(text);
        setFont(new Font("Serif", Font.BOLD, 20));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(119, 136, 153));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        g2d.setColor(new Color(105, 105, 105));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

        super.paintComponent(g);

        g2d.dispose();
    }
}
