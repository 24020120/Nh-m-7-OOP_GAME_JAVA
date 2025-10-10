import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Arkanoid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);

        add(new GamePanel());

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

