package GameObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class panddle {
    private int x, y;
    private int width, height;
    private int speed;
    private int screenWidth;
    private BufferedImage paddleImage;
    private BufferedImage[] paddleFrames;
    private int currentFrame;
    private long lastFrameTime;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public panddle(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.width = 100;
        this.height = 20;
        this.x = (screenWidth - width) / 2;
        this.y = screenHeight - 50;
        this.speed = 8;
        this.currentFrame = 0;
        this.lastFrameTime = System.currentTimeMillis();

        loadImages();
    }

    private void loadImages() {
        try {
            // Load ảnh paddle từ file
            paddleImage = ImageIO.read(new File("images/panddle.png"));

            // Hoặc tạo ảnh mặc định nếu không load được
            if (paddleImage == null) {
                createDefaultPaddleImage();
            }

        } catch (IOException e) {
            System.err.println("Không thể load ảnh paddle: " + e.getMessage());
            createDefaultPaddleImage();
        }
    }

    private void createDefaultPaddleImage() {
        // Tạo ảnh paddle mặc định, nếu không vẽ được panddle ảnh
        paddleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = paddleImage.createGraphics();

        java.awt.GradientPaint gradient = new java.awt.GradientPaint(
                0, 0, new java.awt.Color(70, 130, 180),
                0, height, new java.awt.Color(30, 90, 140)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
    }

    public void update() {
        if (moveLeft) {
            moveLeft();
        }
        if (moveRight) {
            moveRight();
        }
    }

    public void moveLeft() {
        x = Math.max(0, x - speed);
    }

    public void moveRight() {
        x = Math.min(screenWidth - width, x + speed);
    }

    public void draw(Graphics2D g) {
        if (paddleFrames != null && paddleFrames.length > 0) {
            g.drawImage(paddleFrames[currentFrame], x, y, width, height, null);
            updateAnimation();
        } else {
            // vẽ hình ảnh paddle
            g.drawImage(paddleImage, x, y, width, height, null);
        }

    }

    private void updateAnimation() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime > 100) {
            currentFrame = (currentFrame + 1) % paddleFrames.length;
            lastFrameTime = currentTime;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setPosition(int x) {
        this.x = Math.max(0, Math.min(screenWidth - width, x));
    }

    public void setWidth(int width) {
        this.width = Math.max(30, Math.min(200, width));
        createDefaultPaddleImage();
    }
}