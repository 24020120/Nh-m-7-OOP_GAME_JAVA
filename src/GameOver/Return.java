package GameOver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Return{
    private Rectangle returnBound;
    private BufferedImage returnButton;

    public Return() {
        this(350, 400, 100, 30);
        
    }

    public Return(int x, int y, int width, int height) {
        returnBound = new Rectangle(x, y, width, height);
        try {
            returnButton = ImageIO.read(new File("images/ReturnButton.png"));
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
        }
    }
    
    public boolean isClicked(int MouseX, int MouseY) {
        return returnBound.contains(MouseX, MouseY);
    }
}
