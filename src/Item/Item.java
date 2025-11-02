package Item;

import GameBoard.GameBoard;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public abstract class Item {
    protected int x, y, width = 20, height = 20;
    protected boolean active = true;
    protected Color color = Color.WHITE;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void applyEffect(GameBoard board);
    public abstract void draw(Graphics g);

    public void update() {
        y += 2;
        if (y > GameBoard.HEIGHT) {
            active = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
