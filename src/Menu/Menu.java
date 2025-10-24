package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private Main mainFrame;
    private Image backgroundImage;

    public Menu(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        backgroundImage = new ImageIcon("images/background.png").getImage();


        add(Box.createVerticalGlue());

        JLabel title = new JLabel("ARKANOID");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 50)));


        JButton playButton = createMenuButton("Start Game");

        playButton.addActionListener(e -> mainFrame.switchToPanel("GAMEBOARD"));
        playButton.addActionListener(e -> mainFrame.switchToPanel("LEVELMENU"));
       // playButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Chức năng GameBoard sẽ được thêm ở đây!"));

        JButton instructButton = createMenuButton("Instructions");
        instructButton.addActionListener(e -> mainFrame.switchToPanel("INSTRUCTIONS"));

        JButton settingsButton = createMenuButton("Settings");
        settingsButton.addActionListener(e -> mainFrame.switchToPanel("SETTINGS"));

        //JButton exitButton = createMenuButton("Exit");
        //exitButton.addActionListener(e -> mainFrame.switchToPanel("EXIT"));

        IconButton exitButton = new IconButton(
            "images/exit_icon.png",
            "images/exit_icon_hover.png",
            "images/exit_icon_pressed.png"
            );
        exitButton.setPreferredSize(new Dimension(250, 50));
        exitButton.setMaximumSize(new Dimension(250, 50));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> mainFrame.switchToPanel("EXIT"));

        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(instructButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(settingsButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(exitButton);

        add(Box.createVerticalGlue());
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(250, 50));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }
}

