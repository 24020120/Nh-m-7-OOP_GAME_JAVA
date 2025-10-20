package Menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IconButton extends JButton {
    private Image normal;
    private Image rollOver;
    private Image select;

    public IconButton(String normalPath, String rollOverPath, String selectPath) {
        super();
        normal = new ImageIcon(normalPath).getImage();
        rollOver = new ImageIcon(rollOverPath).getImage();
        select = new ImageIcon(selectPath).getImage();

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        // set the default icon
        setIcon(new ImageIcon(normal));
    }

    public void processMouseEvent(MouseEvent me) {
        if (me.getID() == MouseEvent.MOUSE_ENTERED) {
            setIconScaled(rollOver);
        } else if (me.getID() == MouseEvent.MOUSE_EXITED) {
            setIconScaled(normal);
        } else if (me.getID() == MouseEvent.MOUSE_PRESSED) {
            setIconScaled(select);
        } else if (me.getID() == MouseEvent.MOUSE_RELEASED) {
            setIconScaled(normal);
        }
        super.processMouseEvent(me);
    }

    private void setIconScaled(Image img) {
        if (getWidth() > 0 && getHeight() > 0) {
            Image scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaled));
        }
    }

    public void paintComponent(Graphics g) {
        if (getIcon() != null) {
            setIconScaled(((ImageIcon)getIcon()).getImage());
        }
        super.paintComponent(g);
    }
}