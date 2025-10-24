package Item;

import java.awt.Color;
import java.awt.Graphics;

import GameBoard.GameBoard;
import GameObject.GameObject;

/**
 * Base abstract class for falling items (power-ups).
 * Items are circular and slowly fall down; when they reach the paddle
 * they should apply their effect (applyEffect) and disappear.
 */
public abstract class Item extends GameObject {

    protected double fallSpeed;
    private boolean active = true;

    public Item(int x, int y, int diameter, double fallSpeed) {
        super(x, y, diameter, diameter);
        this.fallSpeed = fallSpeed;
    }

    @Override
    public void update() {
        // fall down
        this.y += fallSpeed;
    }

    @Override
    public void draw(Graphics g) {
        Color c = getColor();
        if (c != null) g.setColor(c);
        g.fillOval(x, y, width, height);
    }

    /**
     * Color used to draw this item.
     */
    protected abstract Color getColor();

    /**
     * Apply the item effect to the given board (called when picked up).
     * Implementations should perform their buff and any state changes.
     */
    public abstract void applyEffect(GameBoard board);

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}

