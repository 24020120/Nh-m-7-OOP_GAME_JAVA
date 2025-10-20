package Game;

import javax.swing.*;
import Menu.*;
import GameBoard.GameBoard;
import GameOver.GameOver;
import java.awt.CardLayout;
import java.awt.Component;
public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Main() {
        setTitle("Arkanoid Game - Final Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        GameBoard gameBoardPanel = new GameBoard(this);
        gameBoardPanel.setName("GAMEBOARD");
        Menu menuPanel = new Menu(this);
        Instruct instructPanel = new Instruct(this);
        Setting settingsPanel = new Setting(this);
        Exit exitPanel = new Exit(this);
        GameOver gameOverPanel = new GameOver(this);
        mainPanel.add(gameBoardPanel, "GAMEBOARD");
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(instructPanel, "INSTRUCTIONS");
        mainPanel.add(settingsPanel, "SETTINGS");
        mainPanel.add(exitPanel, "EXIT");
        mainPanel.add(gameOverPanel, "GAMEOVER");
        add(mainPanel);
        switchToPanel("MENU"); // Bắt đầu ở màn hình Menu
    }
    public void switchToPanel(String panelName) {
        GameBoard gameBoard = null;

        if (panelName.equals("GAMEBOARD")) {
            // Find it once
            for (Component comp : mainPanel.getComponents()) {
                if ("GAMEBOARD".equals(comp.getName()) && comp instanceof GameBoard) {
                    gameBoard = (GameBoard) comp;
                    break;
                }
            }

            if (gameBoard != null) {
                gameBoard.initGame();
            }
        }

        cardLayout.show(mainPanel, panelName);

        if (gameBoard != null) {
            SwingUtilities.invokeLater(gameBoard::requestFocusInWindow);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}