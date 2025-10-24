package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Brick extends GameObject {
    private boolean isVisible = true;
    private BufferedImage brickImage;

    // Mảng chứa hình ảnh các loại gạch
    private static BufferedImage[] brickImages;

    static {
        try {
            brickImages = new BufferedImage[]{
                    ImageIO.read(new File("images/brick1.png")),
                    ImageIO.read(new File("images/brick2.png"))
            };
        } catch (IOException e) {
            brickImages = new BufferedImage[0]; // fallback rỗng
        }
    }

    public Brick(int x, int y, int width, int height, int typeIndex) {
        super(x, y, width, height);
        if (brickImages.length > 0) {
            brickImage = brickImages[typeIndex % brickImages.length];
        }
    }

    @Override
    public void update() {
        // Gạch không di chuyển
    }

    @Override
    public void draw(Graphics g) {
        if (!isVisible) return;

        if (brickImage != null) {
            g.drawImage(brickImage, x, y, width, height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }

    public void hit() {
        this.isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
