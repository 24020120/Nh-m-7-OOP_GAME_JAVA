import javax.swing.*;

import GameOver.GameOver;

public class Game extends JFrame {
    private GameOver gameOverPanel;

    public Game() {
        setTitle("Arkanoid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); //Force window size to be 800x600
        setLocationRelativeTo(null);

        gameOverPanel = new GameOver();
        
        add(gameOverPanel);

        setVisible(true);
    }

    public void showGameOver() {
        getContentPane().removeAll();
        add(gameOverPanel);
        revalidate();
        repaint();
    }

}