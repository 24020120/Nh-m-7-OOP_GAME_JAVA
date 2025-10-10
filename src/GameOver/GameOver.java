package GameOver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameOver extends JPanel{
    private BufferedImage GameOverBG;
    private Exit exitButton;
    private Return returnButton;

    public GameOver() {
        setSize(800, 600);

        try {
            GameOverBG = ImageIO.read(new File("images/GameOver.png"));
        } catch (Exception e) {
            e.printStackTrace();;
            GameOverBG = null;
        }

        exitButton = new Exit();
        returnButton = new Return();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (exitButton.isClicked(x, y)) {
                    System.exit(0);
                }
                if (returnButton.isClicked(x, y)) {
                    // Temporary
                    System.exit(0);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (GameOverBG != null) {
            g.drawImage(
                GameOverBG,
                0,
                0,
                getWidth(),
                getHeight(),
                null
            );
        }

        exitButton.draw(g);
        returnButton.draw(g);
    }

}