package Item;

import GameBoard.GameBoard;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Item bắn đạn tự động. Khi nhặt, paddle sẽ tự động bắn đạn trong 4 giây
 */
public class ShootingItem extends Item {

    private static final int SHOOTING_DURATION = 4; 
    private Image itemImage;

    public ShootingItem(int x, int y) {
        super(x, y);
        this.width = 20;
        this.height = 20;

        try {
            itemImage = new ImageIcon("images/gun_item.png").getImage();
        } catch (Exception e) {
            itemImage = null;
            System.out.println("Không thể load hình ảnh gun_item.png");
        }
    }

    @Override
    public void applyEffect(GameBoard board) {
        if (board.getPlayer() != null) {
            board.getPlayer().activateAutoShoot(SHOOTING_DURATION);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!isActive()) return;

        if (itemImage != null) {
            g.drawImage(itemImage, x, y, width, height, null);
        } else {
    
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            g.drawString("G", x + 6, y + 15);
        }
    }
}