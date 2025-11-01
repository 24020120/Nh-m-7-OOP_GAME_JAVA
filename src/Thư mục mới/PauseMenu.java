package GameBoard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import Game.Main;

public class PauseMenu {
    private boolean isActive = false;

    private Rectangle resumeButton;
    private Rectangle returnButton;
    private Rectangle exitButton;
    private boolean hoverResume = false;
    private boolean hoverReturn = false;
    private boolean hoverExit = false;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Main mainFrame;
    private MouseAdapter mouseHandler;
    private JComponent parent;

    public PauseMenu(Main mainFrame, JComponent parent) {
        this.mainFrame = mainFrame;
        this.parent = parent;

        int btnWidth = 200;
        int btnHeight = 50;
        int centerX = WIDTH / 2 - btnWidth / 2;
        int startY = HEIGHT / 2 - 100;

        resumeButton = new Rectangle(centerX, startY, btnWidth, btnHeight);
        returnButton = new Rectangle(centerX, startY + 70, btnWidth, btnHeight);
        exitButton = new Rectangle(centerX, startY + 140, btnWidth, btnHeight);

        // --- Mouse Handler ---
        mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isActive) return;
                Point p = e.getPoint();

                if (resumeButton.contains(p)) {
                    isActive = false;
                    parent.repaint();
                } else if (returnButton.contains(p)) {
                    // Gỡ listener trước khi quay lại menu
                    detachListeners(parent);
                    isActive = false;
                    mainFrame.showMenu();
                } else if (exitButton.contains(p)) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!isActive) return;
                Point p = e.getPoint();
                hoverResume = resumeButton.contains(p);
                hoverReturn = returnButton.contains(p);
                hoverExit = exitButton.contains(p);
                parent.repaint();
            }
        };

        parent.addMouseListener(mouseHandler);
        parent.addMouseMotionListener(mouseHandler);
    }

    // --- Gỡ bỏ listener khi quay lại menu ---
    public void detachListeners(JComponent parent) {
        parent.removeMouseListener(mouseHandler);
        parent.removeMouseMotionListener(mouseHandler);
    }

    public void draw(Graphics2D g2) {
        if (!isActive) return;

        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.setColor(Color.WHITE);
        g2.drawString("PAUSED", WIDTH / 2 - 120, HEIGHT / 2 - 150);

        g2.setFont(new Font("Arial", Font.BOLD, 24));

        // Resume
        g2.setColor(hoverResume ? Color.YELLOW : Color.WHITE);
        g2.draw(resumeButton);
        g2.drawString("Resume", resumeButton.x + 60, resumeButton.y + 32);

        // Return to Menu
        g2.setColor(hoverReturn ? Color.YELLOW : Color.WHITE);
        g2.draw(returnButton);
        g2.drawString("Return to Menu", returnButton.x + 25, returnButton.y + 32);

        // Exit
        g2.setColor(hoverExit ? Color.YELLOW : Color.WHITE);
        g2.draw(exitButton);
        g2.drawString("Exit Game", exitButton.x + 55, exitButton.y + 32);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
