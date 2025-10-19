package GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObject {
    private boolean isVisible = true;

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.dx = 0;
        this.dy = 0;
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
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