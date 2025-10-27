package Menu;

import Game.Main;
import GameObject.Brick;
import javax.swing.*;
import java.awt.*;
import java.util.List; // --- NEW --- Import for List

public class LevelMenu extends JPanel {
    // constructor parameter 'mainFrame' is captured by lambdas; no field needed
    private Image backgroundImage;
    private List<Brick> bricks; // kept for compatibility but will not be used
    private int selectedLevel = 0;

    public LevelMenu(Main mainFrame) {

        backgroundImage = new ImageIcon("images/background.png").getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue()); // Pushes content to the vertical center

        // --- Title ---
        JLabel title = new JLabel("Select Your Level"); // Changed text
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 30))); // Spacer

        JPanel levelButtonPanel = new JPanel();
        levelButtonPanel.setLayout(new BoxLayout(levelButtonPanel, BoxLayout.X_AXIS)); // Arrange buttons horizontally
        levelButtonPanel.setOpaque(false); // Make this panel transparent
        levelButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center this panel horizontally

        // Create the level buttons (boxes)
    JButton level1Button = new JButton("Level 1");
    JButton level2Button = new JButton("Level 2");
        //JButton level3Button = new JButton("Level 3");

        // Add actions to the buttons
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

        /*
        level3Button.addActionListener(e -> {
            System.out.println("Start Level 3");
            // e.g., mainFrame.startGame(3);
        });
        */

        // Add buttons to the horizontal panel
        levelButtonPanel.add(Box.createHorizontalGlue()); // Centers buttons
        levelButtonPanel.add(level1Button);
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Space between buttons
        levelButtonPanel.add(level2Button);
        levelButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        /*
        levelButtonPanel.add(level3Button);
        levelButtonPanel.add(Box.createHorizontalGlue()); // Centers buttons
        */

        // Add the button panel to the main vertical panel
        add(levelButtonPanel);

        add(Box.createRigidArea(new Dimension(0, 30))); // Spacer

        // --- Return Panel ---
        Return returnPanel = new Return(mainFrame);
        returnPanel.setOpaque(false); // Make the return panel transparent too
        add(returnPanel);

        add(Box.createVerticalGlue()); // Pushes content to the vertical center
    }

    // --- FIX: Added paintComponent to draw the background image ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the image to fill the entire panel
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