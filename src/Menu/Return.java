package Menu;

import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Return extends JPanel {

    public Return(Main mainFrame) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));

        returnButton.addActionListener(e -> {
            e.getSource();
            mainFrame.switchToPanel("MENU");
        });

        add(returnButton);
    }
}