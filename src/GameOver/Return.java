package GameOver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Return {
    private Rectangle returnBound;
    private BufferedImage returnButton;

    public Return() {
        this(300, 450, 200, 60); // Kích thước mới 200x60
    }

    public Return(int x, int y, int width, int height) {
        returnBound = new Rectangle(x, y, width, height);
        try {
            returnButton = ImageIO.read(new File("images/return_button.png")); // File hình ảnh mới
        } catch (IOException e) {
            e.printStackTrace();
            returnButton = null;
        }
    }

    protected void draw(Graphics g) {
        if (returnButton != null) {
            g.drawImage(
                    returnButton,
                    returnBound.x,
                    returnBound.y,
                    returnBound.width,
                    returnBound.height,
                    null);
        } else {
            // Fallback: vẽ nút cơ bản nếu không có hình
            g.setColor(Color.BLUE);
            g.fillRect(returnBound.x, returnBound.y, returnBound.width, returnBound.height);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("RETURN TO MENU", returnBound.x + 20, returnBound.y + 35);
        }
    }

    public boolean isClicked(int MouseX, int MouseY) {
        return returnBound.contains(MouseX, MouseY);
    }
}