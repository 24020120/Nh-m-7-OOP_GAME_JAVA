package Game;

import javax.swing.*;
import Menu.*;
import java.awt.CardLayout;
public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Main() {
        setTitle("Arkanoid Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Start startPanel = new Start(this);
        Menu menuPanel = new Menu(this);
        //Menu.Menu menuPanel = new Menu.Menu(this);
        Instruct instructPanel = new Instruct(this);
        Setting settingsPanel = new Setting(this);
        Exit exitPanel = new Exit(this);

       // mainPanel.add(startPanel, "START");
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(instructPanel, "INSTRUCTIONS");
        mainPanel.add(settingsPanel, "SETTINGS");
        mainPanel.add(exitPanel, "EXIT");

        add(mainPanel);
    }

    public void switchToPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}

