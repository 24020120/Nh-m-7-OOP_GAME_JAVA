package GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends GameObject {
    private final int MOVE_SPEED = 30;
    private boolean canShoot = true;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public void activateShooting() {
        this.canShoot = true;
    }

    public void deactivateShooting() {
        this.canShoot = false;
    }

    /**
     * Bắn một viên đạn nếu đang ở chế độ bắn.
     * @return Viên đạn mới được tạo, hoặc null nếu không thể bắn.
     */
    public Bullet shoot() {
        if (canShoot) {
            int startX = this.x + (this.width / 2);
            int startY = this.y;

            Bullet newBullet = new Bullet(startX, startY);
            return newBullet;
        }
        return null;
    }
    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public void move(int direction) { // direction: -1 (trái) hoặc 1 (phải)
        x += direction * MOVE_SPEED;

        if (x < 0) x = 0;
        if (x > 800 - width) x = 800 - width; // Sử dụng 800 (WIDTH) của GameBoard
    }
}