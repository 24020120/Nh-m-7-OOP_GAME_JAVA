
package Menu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Start {

    public static JButton CreateStartButton() {
        JButton startButton = new JButton("START");
        startButton.setSize(50, 20);
        startButton.setVisible(true);
        startButton.setBackground(new ColorUIResource(0, 128, 0));
        return startButton;
    }
}

