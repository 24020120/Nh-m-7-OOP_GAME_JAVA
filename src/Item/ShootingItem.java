package Item;

import GameBoard.GameBoard;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Item bắn đạn tự động. Khi nhặt, paddle sẽ tự động bắn đạn trong 10 giây.
 */
public class ShootingItem extends Item {

    private static final int SHOOTING_DURATION = 10; // 10 giây

    public ShootingItem(int x, int y) {
        super(x, y);
        this.width = 20;
        this.height = 20;
    }

    @Override
    public void applyEffect(GameBoard board) {
        if (board.getPlayer() != null) {
            board.getPlayer().activateAutoShoot(SHOOTING_DURATION);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isActive()) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);

            g.setColor(Color.BLACK);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            g.drawString("G", x + 6, y + 15); // G for Gun
        }
    }
}