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
        this(300, 350, 200, 60); // Kích thước mới 200x60
    }

    public Exit(int x, int y, int width, int height) {
        exitBound = new Rectangle(x, y, width, height);
        try {
            exitButton = ImageIO.read(new File("images/exit_button.png")); // File hình ảnh mới
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
        } else {
            // Fallback: vẽ nút cơ bản nếu không có hình
            g.setColor(Color.RED);
            g.fillRect(exitBound.x, exitBound.y, exitBound.width, exitBound.height);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("EXIT", exitBound.x + 70, exitBound.y + 35);
        }
    }

    public boolean isClicked(int MouseX, int MouseY) {
        return exitBound.contains(MouseX, MouseY);
    }
}