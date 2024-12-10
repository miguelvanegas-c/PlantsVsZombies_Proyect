<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======
>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
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
<<<<<<< HEAD

=======
>>>>>>> 34e246993894e450ab7a9306bb9ab88f5a4cf910
=======

>>>>>>> b49ed8088702da98f5dfe19437f9c75858913b6b
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);

        g2d.dispose();
    }


}