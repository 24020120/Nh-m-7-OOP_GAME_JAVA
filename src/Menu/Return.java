package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Return extends JPanel {
    public Return(JFrame frame, JPanel mainMenu) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 250));

        JButton returnButton = new JButton("Back to Menu");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));

        add(returnButton);

        returnButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }
}
