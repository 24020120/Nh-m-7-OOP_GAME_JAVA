package Menu;
import javax.swing.*;

public class Game {
    public static void GameLoop() {
        JFrame frame = new JFrame("Arkanoid");
        JButton startButton = Start.CreateStartButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(Menu.CreateMenuBar());
        frame.add(startButton);

        startButton.getLocation();
        startButton.setVisible(true);

        frame.pack();
        frame.setVisible(true);
    }
}
