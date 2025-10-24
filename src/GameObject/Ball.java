package GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {

    public Ball(int x, int y, int diameter, double dx, double dy) {
        super(x, y, diameter, diameter);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
    }

    public void reset(int startX, int startY, double initialDx, double initialDy) {
        this.x = startX;
        this.y = startY;
        this.dx = initialDx;
        this.dy = initialDy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }
}
