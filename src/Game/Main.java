package Game;

import javax.swing.*;
import Menu.*;
import GameBoard.GameBoard;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
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
        mainPanel.add(gameBoardPanel, "GAMEBOARD");
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(instructPanel, "INSTRUCTIONS");
        mainPanel.add(settingsPanel, "SETTINGS");
        mainPanel.add(exitPanel, "EXIT");
        add(mainPanel);
        switchToPanel("MENU"); // Bắt đầu ở màn hình Menu
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        if (panelName.equals("GAMEBOARD")) {
            SwingUtilities.invokeLater(() -> {
                for (Component comp : mainPanel.getComponents()) {
                    if ("GAMEBOARD".equals(comp.getName())) {
                        comp.requestFocusInWindow();
                        break;
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}