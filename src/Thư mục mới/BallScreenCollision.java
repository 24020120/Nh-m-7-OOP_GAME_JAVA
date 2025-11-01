package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import GameObject.ShieldBarrier;

public class BallScreenCollision extends Collidable {

    private final int BORDER_OFFSET = 57; // Khoảng cách lùi vào 50 pixel

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        // Va chạm với tường TRÁI (lùi vào 50 pixel)
        if (ball.getX() < BORDER_OFFSET - 17) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(BORDER_OFFSET - 17);
        }

        // Va chạm với tường PHẢI (lùi vào 50 pixel)
        else if (ball.getX() > (GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET)) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET);
        }

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

        // Va chạm với đáy (giữ nguyên)
        if (ball.getY() > GameBoard.HEIGHT - ball.getHeight()) {
            board.removeBall(ball);
        }
    }
}