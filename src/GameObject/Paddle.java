package GameObject;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Gameobject {
    private final int MOVE_SPEED = 30;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {} // Không cần update tự động

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