package Menu;

import GameObject.Brick;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// tạo lưới gạch với 5 hàng và 10 cot
public class GamePanel extends JPanel {
    private List<Brick> bricks;
    private int rows = 5;
    private int cols = 10;
    private int brickWidth = 70;
    private int brickHeight = 30;
    private int distan = 2; // khoảng cách gạch
    int offsetY = 30;

    String[] images = {"images/brick1.png", "images/brick2.png"};

    public GamePanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 600));
        bricks = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Xóa danh sách bricks cũ và vẽ lại để đảm bảo căn giữa theo kích thước thực tế
        bricks.clear();
        int totalWidth = cols * brickWidth + (cols - 1) * distan;
        int offsetX = (getWidth() - totalWidth) / 2; // vị trí đặt viên gạch đầu tiên

        // tạo lại gạch theo vị trí chính xác
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String imagePath = images[(int)(Math.random() * images.length)]; // random 1 trong 2 ảnh
                int x = offsetX + col * (brickWidth + distan);
                int y = offsetY + row * (brickHeight + distan);
                bricks.add(new Brick(x, y, brickWidth, brickHeight, imagePath));
            }
        }

        // vẽ toàn bộ bricks
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }
}
