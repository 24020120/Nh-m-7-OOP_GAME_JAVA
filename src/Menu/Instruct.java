package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Instruct extends JPanel {
    private Image imageInstruct;

    public Instruct(Main mainFrame) {
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imageInstruct != null) {
                    g.drawImage(imageInstruct, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        imageInstruct = new ImageIcon("images/instruction.png").getImage();

        IconButton returnButton = new IconButton(
                "images/return_to_menu_normal.png",
                "images/return_to_menu_hover.png",
                "images/return_to_menu_pressed.png"
        );
        returnButton.setPreferredSize(new Dimension(200, 50));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(returnButton);

        returnButton.addActionListener(e -> {
            mainFrame.switchToPanel("MENU");
        });

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}