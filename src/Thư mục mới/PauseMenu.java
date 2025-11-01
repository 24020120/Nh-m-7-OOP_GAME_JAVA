package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Game.Main;

public class PauseMenu {
    private final Main mainFrame;
    private final GameBoard parent;
    private boolean active = false;

    private final Rectangle resumeButton;
    private final Rectangle exitButton;
    private boolean hoverResume = false;
    private boolean hoverExit = false;

    private final int btnWidth = 200;
    private final int btnHeight = 50;

    private final MouseAdapter mouseAdapter;

    public PauseMenu(Main mainFrame, GameBoard parent) {
        this.mainFrame = mainFrame;
        this.parent = parent;

        int centerX = GameBoard.WIDTH / 2 - btnWidth / 2;
        resumeButton = new Rectangle(centerX, 250, btnWidth, btnHeight);
        exitButton = new Rectangle(centerX, 320, btnWidth, btnHeight);

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!active) return;
                Point p = e.getPoint();

                if (resumeButton.contains(p)) {
                    setActive(false);
                } else if (exitButton.contains(p)) {
                    parent.saveGameState();
                    parent.exitGame();
                    if (mainFrame != null) {
                        mainFrame.switchToPanel("MENU");
                    } else {
                        System.exit(0);
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!active) return;
                Point p = e.getPoint();
                hoverResume = resumeButton.contains(p);
                hoverExit = exitButton.contains(p);
                parent.repaint();
            }
        };
    }

    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void draw(Graphics2D g2d) {
        if (!active) return;

        Composite oldComposite = g2d.getComposite();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GameBoard.WIDTH, GameBoard.HEIGHT);
        g2d.setComposite(oldComposite);

        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        g2d.setColor(Color.WHITE);
        g2d.drawString("PAUSED", GameBoard.WIDTH / 2 - 90, 180);

        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.setColor(hoverResume ? Color.YELLOW : Color.WHITE);
        g2d.drawString("Resume", resumeButton.x + 45, resumeButton.y + 35);
        g2d.draw(resumeButton);

        g2d.setColor(hoverExit ? Color.YELLOW : Color.WHITE);
        g2d.drawString("Exit Game", exitButton.x + 25, exitButton.y + 35);
        g2d.draw(exitButton);
    }
}
