package Item;

import GameBoard.GameBoard;
import GameObject.Ball;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Item nhân bản bóng. Khi va chạm, nó sẽ thêm 2 quả bóng mới.
 */
public class MultiBallItem extends Item {

    public MultiBallItem(int x, int y) {
        super(x, y);
        this.width = 20;
        this.height = 20;
    }

    /**
     * Thêm 2 quả bóng mới vào sân chơi.
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

    @Override
    public void draw(Graphics g) {

        if (isActive()) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, width, height);
            g.setColor(Color.WHITE);
            g.fillOval(x + 3, y + 5, 5, 5);
            g.fillOval(x + 12, y + 5, 5, 5);
            g.fillOval(x + 8, y + 12, 5, 5);
        }
    }
}