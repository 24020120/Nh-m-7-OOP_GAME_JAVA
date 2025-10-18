package GameObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {
    private int x, y;
    private int diameter;
    private int screenWidth, screenHeight;
    private BufferedImage ballImage;
    private Paddle paddle;

    // Thuộc tính cho di chuyển
    private boolean isMoving = false;
    private double baseSpeedX = 5;
    private double speedX = 0;
    private double speedY = -5; // Di chuyển lên trên khi bắt đầu

    public Ball(int screenWidth, int screenHeight, Paddle paddle) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.paddle = paddle;
        this.diameter = 20; // Kích thước quả bóng

        // Đặt vị trí ban đầu ở giữa paddle
        updatePositionToPaddle();

        loadImage();
    }

    private void loadImage() {
        try {
            // Load ảnh bóng từ file
            ballImage = ImageIO.read(new File("images/ball.png"));

            // Nếu không load được, tạo ảnh mặc định
            if (ballImage == null) {
                createDefaultBallImage();
            }
        } catch (IOException e) {
            System.err.println("Không thể load ảnh ball: " + e.getMessage());
            createDefaultBallImage();
        }
    }

    private void createDefaultBallImage() {
        // Tạo ảnh bóng mặc định
        ballImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = ballImage.createGraphics();

        // Vẽ hình tròn với gradient
        java.awt.GradientPaint gradient = new java.awt.GradientPaint(
                0, 0, new java.awt.Color(255, 255, 255),
                diameter, diameter, new java.awt.Color(200, 200, 200)
        );
        g2d.setPaint(gradient);
        g2d.fillOval(0, 0, diameter, diameter);

        // Viền
        g2d.setColor(new java.awt.Color(100, 100, 100));
        g2d.drawOval(0, 0, diameter - 1, diameter - 1);

        g2d.dispose();
    }

    public void updatePositionToPaddle() {
        // Đặt bóng ở chính giữa paddle
        int paddleCenterX = paddle.getX() + paddle.getWidth() / 2;
        this.x = paddleCenterX - diameter / 2;
        this.y = paddle.getY() - diameter; // Đặt bóng ngay trên paddle
    }

    public void update() {
        if (isMoving) {
            // Di chuyển ball
            x += speedX;
            y += speedY;

        } else {
            // Nếu chưa di chuyển, ball vẫn theo paddle
            updatePositionToPaddle();
        }
    }

    public void startMoving() {
        isMoving = true;
        // Tốc độ ban đầu: di chuyển thẳng lên trên
        speedX = 0;
        speedY = -5;
    }

    public void reset() {
        isMoving = false;
        speedX = 0;
        speedY = -5;
        updatePositionToPaddle();
    }

    public void draw(Graphics2D g) {
        // Vẽ hình ảnh bóng
        g.drawImage(ballImage, x, y, diameter, diameter, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDiameter() { return diameter; }
    public int getRadius() { return diameter / 2; }
    public boolean isMoving() { return isMoving; }
    public double getSpeedX() { return speedX; }
    public double getSpeedY() { return speedY; }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getBaseSpeedX() {
        return baseSpeedX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}