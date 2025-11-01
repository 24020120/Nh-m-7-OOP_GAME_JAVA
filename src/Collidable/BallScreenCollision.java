package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import GameObject.ShieldBarrier;

public class BallScreenCollision extends Collidable {

    private final int BORDER_OFFSET = 57; // Khoảng cách lùi vào 57 pixel

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        // Va chạm với tường TRÁI
        if (ball.getX() < BORDER_OFFSET - 17) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(BORDER_OFFSET - 17);
        }

        // Va chạm với tường PHẢI
        else if (ball.getX() > (GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET)) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET);
        }

        // Va chạm với tường TRÊN
        if (ball.getY() < BORDER_OFFSET - 10) {
            ball.setDy(Math.abs(ball.getDy()));
            ball.setY(BORDER_OFFSET - 10);
        }

        // Va chạm với Shield (nếu có)
        ShieldBarrier shield = board.getShield();
        if (shield != null && ball.getBounds().intersects(shield.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));
            ball.setY(shield.getY() - ball.getHeight());
            return;
        }

        // Nếu bóng rơi ra ngoài màn hình — chỉ loại bỏ bóng, KHÔNG trừ mạng ở đây
        if (ball.getY() > GameBoard.HEIGHT - ball.getHeight()) {
            board.removeBall(ball);
        }
    }
}
