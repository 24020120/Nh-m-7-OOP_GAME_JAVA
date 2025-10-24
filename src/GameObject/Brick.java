package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;     // cần cho ImageIO.read()
import java.io.File;             // cần cho new File()
import java.io.IOException;      // cần cho try-catch
import java.util.Random;

public class Brick extends Gameobject {
    private boolean isVisible = true;
    private BufferedImage brickImage;

    private static BufferedImage[] brickImages;

    static {
        try {
            brickImages = new BufferedImage[] {
                    ImageIO.read(new File("images/brick1.png")),
                    ImageIO.read(new File("images/brick2.png")),
            };
        } catch (IOException e) {
            brickImages = new BufferedImage[0]; // fallback
        }
    }

    public Brick(int x, int y, int width, int height, int typeIndex) {
        super(x, y, width, height);

        if (brickImages.length > 0)
            brickImage = brickImages[typeIndex % brickImages.length];
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        if (!isVisible) return;

        if (brickImage != null) {
            g.drawImage(brickImage, x, y, width, height, null);
        } else {
            g.setColor(java.awt.Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(java.awt.Color.BLACK);
            g.drawRect(x, y, width + 1, height + 1);
        }
    }

    public void hit() {
        this.isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
