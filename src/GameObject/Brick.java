package GameObject;

import java.awt.*;
import javax.swing.ImageIcon;

public class Brick {
    private int x, y;
    private int width, height;
    private Image image;
    private boolean visible = true; // trạn thái brick

    public Brick(int x, int y, int width, int height, String imagePath) {
        this.x = x; // tọa độ x
        this.y = y; // tọa độ y
        this.width = width;  // chiều rộng
        this.height = height;  // chiều cao

        // Load ảnh gạch, nếu ảnh không có thì để null
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImage().getWidth(null) > 0) {
            image = icon.getImage();
        } else {
            image = null;
        }
    }

    /** Vẽ viên gạch */
    public void draw(Graphics g) {
        if (!visible) return;

        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            // Vẽ hình chữ nhật màu nếu không có ảnh
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // getter
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
}

