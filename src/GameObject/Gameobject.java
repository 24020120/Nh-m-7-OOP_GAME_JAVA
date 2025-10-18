package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Gameobject {
    protected int x, y;
    protected int width, height;
    protected double dx, dy;

    public Gameobject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);
    public abstract void update();

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Getters & Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getDx() { return dx; }
    public double getDy() { return dy; }
    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}