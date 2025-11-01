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
        LevelMenu levelMenuPanel = new LevelMenu(this);

        gameBoardPanel.setLevelMenu(levelMenuPanel);

        mainPanel.add(gameBoardPanel, "GAMEBOARD");
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(instructPanel, "INSTRUCTIONS");
        mainPanel.add(settingsPanel, "SETTINGS");
        mainPanel.add(exitPanel, "EXIT");
        mainPanel.add(gameOverPanel, "GAMEOVER");
        mainPanel.add(levelMenuPanel, "LEVELMENU");

        add(mainPanel);
        switchToPanel("MENU");
    }

    public void switchToPanel(String panelName) {
        GameBoard gameBoard = null;

        if (panelName.equals("GAMEBOARD") || panelName.equals("CONTINUE")) {
            for (Component comp : mainPanel.getComponents()) {
                if ("GAMEBOARD".equals(comp.getName()) && comp instanceof GameBoard) {
                    gameBoard = (GameBoard) comp;
                    break;
                }
            }

            if (gameBoard != null) {
                if (panelName.equals("CONTINUE")) {
                    gameBoard.loadGameState();
                } else {
                    gameBoard.initGame();
                }
                gameBoard.resumeGame();
            }
        }


        String cardToShow = panelName.equals("CONTINUE") ? "GAMEBOARD" : panelName;
        cardLayout.show(mainPanel, cardToShow);

        if ("MENU".equals(panelName)) {
            for (Component comp : mainPanel.getComponents()) {
                if (comp instanceof Menu) {
                    ((Menu) comp).refreshContinueButton();
                }
            }
        }

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
