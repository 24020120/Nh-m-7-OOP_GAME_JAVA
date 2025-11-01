package Item;

import GameBoard.GameBoard;
import GameObject.Ball;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.List;

public class MultiBallItem extends Item {

    private Image itemImage;

    public MultiBallItem(int x, int y) {
        super(x, y);
        this.width = 28;
        this.height = 28;

        // 🟢 Load ảnh item từ thư mục resources
        // Đảm bảo đường dẫn này đúng vị trí ảnh trong project của bạn
        itemImage = new ImageIcon("images/MultiBallItem.png").getImage();
    }

    /**
     * Khi va chạm thì tạo thêm 2 quả bóng mới
     */
    @Override
    public void applyEffect(GameBoard board) {
        List<Ball> currentBalls = board.getBalls();
        if (currentBalls.isEmpty()) return;

        Ball originalBall = currentBalls.get(0);
        int ballX = originalBall.getX();
        int ballY = originalBall.getY();
        int ballSize = originalBall.getWidth();
        double originalDy = originalBall.getDy();

        double newDy = (originalDy > 0) ? Math.abs(originalDy) : -Math.abs(originalDy);
        if (newDy == 0) newDy = -3.0;

        Ball ball2 = new Ball(ballX, ballY, ballSize, -3.5, newDy);
        Ball ball3 = new Ball(ballX, ballY, ballSize, 3.5, newDy);

        board.addBall(ball2);
        board.addBall(ball3);
    }

    /**
     * Vẽ item bằng ảnh
     */
    @Override
    public void draw(Graphics g) {
        if (isActive()) {
            g.drawImage(itemImage, x, y, width, height, null);
        }
    }
}
