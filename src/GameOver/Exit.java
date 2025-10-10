package GameOver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Exit {
    private Rectangle exitBound;
    private BufferedImage exitButton;

    public Exit() {
        this(350, 250, 100, 30);
    }

    public Exit(int x, int y, int width, int height) {
        exitBound = new Rectangle(x, y, width, height);
        try {
            exitButton = ImageIO.read(new File("images/ExitButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
            exitButton = null;
        }
    }

    protected void draw(Graphics g) {
        if (exitButton != null) {
            g.drawImage(
                exitButton, 
                exitBound.x,
                exitBound.y, 
                exitBound.width,
                exitBound.height,
                null);
        }
    }

    public boolean isClicked(int MouseX, int MouseY) {
        return exitBound.contains(MouseX, MouseY);
    }
}
