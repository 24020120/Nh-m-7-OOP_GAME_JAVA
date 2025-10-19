package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Exit extends JPanel {
    private Main mainFrame;
    private Image backgroundImage;
    public Exit(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        backgroundImage = new ImageIcon("images/background.png").getImage();
        //setBackground(new Color(40, 40, 60));
        // setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel confirmationLabel = new JLabel("Are you sure you want to quit?");
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 28));
        confirmationLabel.setForeground(Color.WHITE);
        confirmationLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton yesButton = new JButton("Yes, Quit");
        yesButton.setFont(new Font("Arial", Font.BOLD, 16));
        yesButton.addActionListener(e -> System.exit(0));

        JButton noButton = new JButton("No, Go Back");
        noButton.setFont(new Font("Arial", Font.BOLD, 16));
        noButton.addActionListener(e -> mainFrame.switchToPanel("MENU"));

        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(noButton);

        add(confirmationLabel, gbc);
        add(buttonPanel, gbc);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }
}




