package Menu;
import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel{
    private Image image;

    public Menu() { // COnstructor
        image = new ImageIcon("assets/Untitlsed.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    
}
