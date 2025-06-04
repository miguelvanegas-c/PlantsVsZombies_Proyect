/**
 * A button that only displays its border.
 *
 * @author Miguel Angel Vanegas and Julian Castiblanco.
 * @version 1.0
 */
public class BorderButton extends TransparentButton  {
    /**
     * Constructor for the BorderButton class.
     * @param text, the button's text.
     */
    public BorderButton(String text) {
        super(text);
        setBorderPainted(true);
    }
}
