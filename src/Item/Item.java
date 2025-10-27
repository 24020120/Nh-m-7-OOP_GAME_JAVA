package Item;

import GameBoard.GameBoard;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Lớp cơ sở trừu tượng cho tất cả các vật phẩm (Items).
 */
public abstract class Item {
    protected int x, y, width = 20, height = 20;
    protected boolean active = true;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Áp dụng hiệu ứng của item lên GameBoard (ví dụ: thêm bóng, tạo khiên).
     */
    public abstract void applyEffect(GameBoard board);

    /**
     * Vẽ item lên màn hình.
     */
    public abstract void draw(Graphics g);

    /**
     * Cập nhật vị trí item (cho item rơi xuống).
     */
    public void update() {
        y += 2; // Tốc độ rơi
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