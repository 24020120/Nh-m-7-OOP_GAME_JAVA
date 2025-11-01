package GameObject;

import java.awt.Color;
import java.awt.Graphics;

import GameBoard.GameBoard;

public class ShieldBarrier extends GameObject {
    private int remainingTicks;

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
        g.setColor(new Color(0, 191, 255, 200));
        g.fillRect(x, y, width, height);
    }

    public boolean isExpired() {
        return remainingTicks <= 0;
    }
}
