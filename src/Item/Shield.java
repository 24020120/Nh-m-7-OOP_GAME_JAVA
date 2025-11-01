package Item;

import GameBoard.GameBoard;
import GameObject.ShieldBarrier;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Vật phẩm Khiên. Khi nhặt, nó tạo ra một rào chắn ở dưới màn hình.
 */
public class Shield extends Item {

    private static final int shieldDurationSeconds = 10;
    private static final int shieldHeight = 30;
    private Image itemImage; // THÊM: Biến hình ảnh

    /**
     * Hàm khởi tạo cho vật phẩm Shield.
     * @param x Vị trí X ban đầu (nơi gạch vỡ)
     * @param y Vị trí Y ban đầu (nơi gạch vỡ)
     */
    public Shield(int x, int y) {
        super(x, y);
        this.width = 20;
        this.height = 20;

        // THÊM: Load hình ảnh khiên
        try {
            itemImage = new ImageIcon("images/shield_item.png").getImage();
        } catch (Exception e) {
            itemImage = null;
            System.out.println("Không thể load hình ảnh shield_item.png");
        }
    }

    /**
     * Áp dụng hiệu ứng: Tạo ra một ShieldBarrier ở dưới cùng GameBoard.
     */
    @Override
    public void applyEffect(GameBoard board) {
        ShieldBarrier newShield = new ShieldBarrier(shieldHeight, shieldDurationSeconds);
        board.setShield(newShield);
    }

    /**
     * Vẽ vật phẩm Shield (khi nó đang rơi).
     */
    @Override
    public void draw(Graphics g) {
        if (!isActive()) return;

        // SỬA: Vẽ hình ảnh thay vì chữ "S"
        if (itemImage != null) {
            g.drawImage(itemImage, x, y, width, height, null);
        } else {
            // Fallback: vẽ hình chữ nhật và chữ nếu không có ảnh
            g.setColor(Color.MAGENTA);
            g.fillRect(x, y, width, height);
            g.setColor(Color.WHITE);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
            g.drawString("S", x + 6, y + 15);
        }
    }
}