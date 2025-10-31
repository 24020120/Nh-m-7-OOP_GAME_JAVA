package Menu;

import Game.Main;
import GameObject.Brick;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LevelMenu extends JPanel {
    private Image backgroundImage;
    private List<Brick> bricks;
    private int selectedLevel = 0;

    public LevelMenu(Main mainFrame) {
        backgroundImage = new ImageIcon("images/background.png").getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        // --- Title ---
        JLabel title = new JLabel("Select Your Level");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 30)));

        // --- Level Buttons (giữ nguyên chữ) ---
        JPanel levelButtonPanel = new JPanel();
        levelButtonPanel.setLayout(new BoxLayout(levelButtonPanel, BoxLayout.X_AXIS));
        levelButtonPanel.setOpaque(false);
        levelButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");
        JButton level3Button = new JButton("Level 3");
        JButton level4Button = new JButton("Level 4");

        // Style cho level buttons
        level1Button.addActionListener(e -> {
            e.getSource();
            System.out.println("Selected Level 1");
            selectedLevel = 1;
            mainFrame.switchToPanel("GAMEBOARD");
        });

        level2Button.addActionListener(e -> {
            e.getSource();
            System.out.println("Selected Level 2");
            selectedLevel = 2;
            mainFrame.switchToPanel("GAMEBOARD");
        });


        level3Button.addActionListener(e -> {
            e.getSource();
            System.out.println("Start Level 3");
            selectedLevel = 3;
            //mainFrame.switchToPanel(panelName:"GAMEBOARD");
            mainFrame.switchToPanel("GAMEBOARD");
            // e.g., mainFrame.startGame(3);
        });

        level4Button.addActionListener(e -> {
            e.getSource();
            System.out.println("Selected Level 4");
            selectedLevel = 4;
            mainFrame.switchToPanel("GAMEBOARD");
        });

        levelButtonPanel.add(Box.createHorizontalGlue()); // Centers buttons
        levelButtonPanel.add(level1Button);
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Space between buttons
        levelButtonPanel.add(level2Button);
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        levelButtonPanel.add(level3Button);
        //levelButtonPanel.add(Box.createRigidArea(new Dimension(width:20, height:0)));
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        levelButtonPanel.add(level4Button);
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));


        add(levelButtonPanel);
        add(Box.createRigidArea(new Dimension(0, 30)));



        // --- Return Button với hình ảnh (THAY ĐỔI) ---
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

    public List<Brick> getBricks() {
        return bricks;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }
}