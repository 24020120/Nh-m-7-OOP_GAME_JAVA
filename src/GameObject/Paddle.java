package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Paddle extends GameObject {
    private final int MOVE_SPEED = 8;
    private static BufferedImage paddleImage;

    static {
        try {
            paddleImage = ImageIO.read(new File("images/paddle.png"));
        } catch (Exception e) {
            paddleImage = null;
        }
    }

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {} // Không cần update tự động

    @Override
    public void draw(Graphics g) {
        if (paddleImage != null) {
            g.drawImage(paddleImage, x, y, width, height, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }


    public void move(int direction) { // direction: -1 (trái) hoặc 1 (phải)
        x += direction * MOVE_SPEED;

        if (x < 0) x = 0;
        if (x > 800 - width) x = 800 - width; // Sử dụng 800 (WIDTH) của GameBoard
    }
}