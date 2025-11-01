package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Setting extends JPanel {
    private Image backgroundImage;

    public Setting(Main mainFrame) {
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

        // THAY ĐỔI: Sử dụng IconButton thay vì Return panel
        IconButton returnButton = new IconButton(
                "images/return_to_menu_normal.png",
                "images/return_to_menu_hover.png",
                "images/return_to_menu_pressed.png"
        );
        returnButton.setPreferredSize(new Dimension(200, 50));
        returnButton.setMaximumSize(new Dimension(200, 50));
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> {
            mainFrame.switchToPanel("MENU");
        });

        add(returnButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}