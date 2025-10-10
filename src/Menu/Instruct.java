package Menu;

import javax.swing.*;
import java.awt.*;

public class Instruct extends JPanel {
    private Image imageInstruct;

    public Instruct() {
        imageInstruct = new ImageIcon("images/instruction.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageInstruct, 0, 0, getWidth(), getHeight(), this);
    }
}
