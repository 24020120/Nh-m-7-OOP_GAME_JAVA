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

        // Tạo các panel chính
        GameBoard gameBoardPanel = new GameBoard(this);
        gameBoardPanel.setName("GAMEBOARD");

        Menu menuPanel = new Menu(this);
        Instruct instructPanel = new Instruct(this);
        Setting settingsPanel = new Setting(this);
        Exit exitPanel = new Exit(this);
        GameOver gameOverPanel = new GameOver(this);
        LevelMenu levelMenuPanel = new LevelMenu(this);

        // Liên kết level menu với game board để lấy dữ liệu màn chơi
        gameBoardPanel.setLevelMenu(levelMenuPanel);

        // Thêm các panel vào layout
        mainPanel.add(gameBoardPanel, "GAMEBOARD");
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(instructPanel, "INSTRUCTIONS");
        mainPanel.add(settingsPanel, "SETTINGS");
        mainPanel.add(exitPanel, "EXIT");
        mainPanel.add(gameOverPanel, "GAMEOVER");
        mainPanel.add(levelMenuPanel, "LEVELMENU");

        add(mainPanel);
        switchToPanel("MENU"); // Bắt đầu ở màn hình Menu
    }

    public void switchToPanel(String panelName) {
        GameBoard gameBoard = null;

        if (panelName.equals("GAMEBOARD")) {
            // Tìm panel GameBoard
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

        // Chuyển sang panel
        cardLayout.show(mainPanel, panelName);

        // Sau khi chuyển, đảm bảo GameBoard nhận focus để điều khiển được
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
