package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Setting extends JPanel {
    private Main mainFrame;
    private Image backgroundImage;
    public Setting(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        backgroundImage = new ImageIcon("images/background.png").getImage();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());

        JLabel title = new JLabel("Settings will be here");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        title.setForeground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 30)));

        Return returnPanel = new Return(mainFrame);
        add(returnPanel);

        add(Box.createVerticalGlue());
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }
}

