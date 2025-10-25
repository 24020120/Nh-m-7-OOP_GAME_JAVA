package GameObject;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    private int x, y;
    private final int width = 5;
    private final int height = 15;
    private final int speed = 8;
    private boolean isActive = true;

    // Constructor
    public Bullet(int startX, int startY) {
        this.x = startX - (width / 2);
        this.y = startY;
    }

    // Phương thức di chuyển đạn
    public void move() {
        y -= speed;
        if (y < 0) {
            isActive = false;
        }
    }

    // Trả về vùng va chạm
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Vẽ đạn
    public void draw(Graphics g) {
        if (isActive) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    // Getters và Setters
    public boolean isActive() {
        return isActive;
    }

    public void setInactive() {
        this.isActive = false;
    }

}