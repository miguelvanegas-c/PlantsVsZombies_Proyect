<<<<<<< HEAD
=======

>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {

    // Constructor
    public TransparentButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Serif", Font.BOLD, 20));
    }

<<<<<<< HEAD

=======
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);

        g2d.dispose();
    }


}