package Menu;

import Game.Main;
import GameBoard.GameBoard;
import javax.swing.*;
import java.awt.*;
import Game.SoundManager;

public class Menu extends JPanel {
    private Image backgroundImage;
    private Image titleImage;
    private IconButton continueButton;

    public Menu(Main mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        backgroundImage = new ImageIcon("images/background.png").getImage();
        titleImage = new ImageIcon("images/arkanoid_title.png").getImage();

        SoundManager.getInstance().playMusic("menu");

        add(Box.createVerticalGlue());

       
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (titleImage != null) {
                   
                    int imgWidth = titleImage.getWidth(this);
                    int imgHeight = titleImage.getHeight(this);
                    int x = (getWidth() - imgWidth) / 2;
                    int y = (getHeight() - imgHeight) / 2;
                    g.drawImage(titleImage, x, y, this);
                }
            }
        };
        titlePanel.setPreferredSize(new Dimension(400, 100));
        titlePanel.setMaximumSize(new Dimension(400, 100));
        titlePanel.setOpaque(false);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titlePanel);

        add(Box.createRigidArea(new Dimension(0, 30)));

        IconButton continueButton = new IconButton(
                "images/continue_basic.png",      // ảnh bình thường
                "images/continue_hover.png",      // ảnh khi hover
                "images/continue_pressed.png"     // ảnh khi nhấn
        );
        continueButton.setPreferredSize(new Dimension(250, 50));
        continueButton.setMaximumSize(new Dimension(250, 50));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setEnabled(GameBoard.hasSavedGame());
        continueButton.addActionListener(e -> mainFrame.switchToPanel("CONTINUE"));

        add(continueButton);
        add(Box.createRigidArea(new Dimension(0, 15)));

        IconButton playButton = new IconButton(
                "images/start_game_normal.png",
                "images/start_game_hover.png",
                "images/start_game_pressed.png"
        );
        playButton.setPreferredSize(new Dimension(250, 50));
        playButton.setMaximumSize(new Dimension(250, 50));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(e -> {
            mainFrame.switchToPanel("LEVELMENU");
        });

        IconButton instructButton = new IconButton(
                "images/instructions_normal.png",
                "images/instructions_hover.png",
                "images/instructions_pressed.png"
        );
        instructButton.setPreferredSize(new Dimension(250, 50));
        instructButton.setMaximumSize(new Dimension(250, 50));
        instructButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructButton.addActionListener(e -> {
            mainFrame.switchToPanel("INSTRUCTIONS");
        });

        IconButton settingsButton = new IconButton(
                "images/settings_normal.png",
                "images/settings_hover.png",
                "images/settings_pressed.png"
        );
        settingsButton.setPreferredSize(new Dimension(250, 50));
        settingsButton.setMaximumSize(new Dimension(250, 50));
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.addActionListener(e -> {
            mainFrame.switchToPanel("SETTINGS");
        });

        IconButton exitButton = new IconButton(
                "images/exit_normal.png",
                "images/exit_hover.png",
                "images/exit_pressed.png"
        );
        exitButton.setPreferredSize(new Dimension(250, 50));
        exitButton.setMaximumSize(new Dimension(250, 50));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            mainFrame.switchToPanel("EXIT");
        });

        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(instructButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(settingsButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(exitButton);

        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void refreshContinueButton() {
        if (continueButton != null) continueButton.setEnabled(GameBoard.hasSavedGame());
    }
}