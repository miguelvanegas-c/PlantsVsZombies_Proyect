package presentation;
/**
 * Boton solo que el contorno del mismo.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class BorderButton extends TransparentButton {
    /**
     * constructor de la clase BorderButton.
     * @param text, texto del boton.
     */
    public BorderButton(String text) {
        super(text);
        setBorderPainted(true);
    }
}
