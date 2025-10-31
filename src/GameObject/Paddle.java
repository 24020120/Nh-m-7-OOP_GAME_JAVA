package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Paddle extends GameObject {
    private final int MOVE_SPEED = 8;
    private static BufferedImage paddleImage;

    private List<Bullet> bullets;
    private long lastShotTime = 0;
    private final long SHOT_DELAY = 200;

    private boolean autoShootEnabled = false;
    private long autoShootEndTime = 0;

    // THÊM: Biến để giới hạn di chuyển theo tường mới
    private final int BORDER_OFFSET = 50;

    static {
        try {
            paddleImage = ImageIO.read(new File("images/paddle.png"));
        } catch (Exception e) {
            paddleImage = null;
        }
    }

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bullets = new ArrayList<>();
    }

    @Override
    public void update() {
        bullets.removeIf(bullet -> !bullet.isActive());
        for (Bullet bullet : bullets) {
            bullet.update();
        }

        if (autoShootEnabled) {
            autoShoot();

            if (System.currentTimeMillis() >= autoShootEndTime) {
                autoShootEnabled = false;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (paddleImage != null) {
            g.drawImage(paddleImage, x, y, width, height, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void move(int direction) {
        x += direction * MOVE_SPEED;

        // SỬA: Giới hạn di chuyển theo tường mới (lùi vào 50 pixel mỗi bên)
        if (x < BORDER_OFFSET) x = BORDER_OFFSET;
        if (x > 800 - width - BORDER_OFFSET) x = 800 - width - BORDER_OFFSET;
    }

    private void autoShoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= SHOT_DELAY) {
            createBullet();
            lastShotTime = currentTime;
        }
    }

    private void createBullet() {
        int bulletWidth = 4;
        int bulletHeight = 10;
        int bulletX = x + (width - bulletWidth) / 2;
        int bulletY = y - bulletHeight;
        bullets.add(new Bullet(bulletX, bulletY, bulletWidth, bulletHeight));
    }

    public void shoot() {
        if (!autoShootEnabled) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShotTime >= SHOT_DELAY) {
                createBullet();
                lastShotTime = currentTime;
            }
        }
    }

    public void activateAutoShoot(int durationSeconds) {
        this.autoShootEnabled = true;
        this.autoShootEndTime = System.currentTimeMillis() + (durationSeconds * 1000);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void clearBullets() {
        bullets.clear();
    }

    public boolean isAutoShootEnabled() {
        return autoShootEnabled;
    }
}