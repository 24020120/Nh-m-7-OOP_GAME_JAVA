package Item;

import GameBoard.GameBoard;
import javax.swing.*;
import java.awt.*;

public class GhostBallItem extends Item {

    private Image itemImage;

    public GhostBallItem(int x, int y) {
        super(x, y);
        // Load ảnh item GhostBall
        itemImage = new ImageIcon("images/ghostball_item.png").getImage();
    }

    @Override
    public void applyEffect(GameBoard board) {
        if (board.getBalls().isEmpty()) return;

        // Kích hoạt ghost mode cho TẤT CẢ bóng trong 4 giây
        for (var ball : board.getBalls()) {
            ball.activateGhostMode(4);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!active) return;

        if (itemImage != null) {
            g.drawImage(itemImage, x, y, width, height, null);
        } else {
            // fallback nếu ảnh lỗi
            g.setColor(Color.MAGENTA);
            g.fillRect(x, y, width, height);
        }
    }
}
