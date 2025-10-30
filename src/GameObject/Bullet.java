package GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {
    private final int SPEED = 7;
    private boolean active = true;

    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        y -= SPEED;
        if (y < 0) {
            active = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!active) return;

        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        g.setColor(Color.ORANGE);
        g.drawRect(x, y, width, height);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}