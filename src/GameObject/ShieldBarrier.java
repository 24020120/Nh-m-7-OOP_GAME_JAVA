package GameObject;

import java.awt.Color;
import java.awt.Graphics;

import GameBoard.GameBoard;

/**
 * A temporary barrier that sits at the bottom of the screen and bounces balls.
 */
public class ShieldBarrier extends GameObject {
    private int remainingTicks; // lifespan in ticks (60 FPS)

    public ShieldBarrier(int height, int durationSeconds) {
        super(0, GameBoard.HEIGHT - height, GameBoard.WIDTH, height);
        this.remainingTicks = durationSeconds * 60;
    }

    @Override
    public void update() {
        if (remainingTicks > 0) remainingTicks--;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 191, 255, 200)); // a translucent cyan
        g.fillRect(x, y, width, height);
    }

    public boolean isExpired() {
        return remainingTicks <= 0;
    }
}
