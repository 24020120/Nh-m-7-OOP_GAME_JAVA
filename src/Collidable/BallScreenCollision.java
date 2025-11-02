package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import GameObject.ShieldBarrier;

public class BallScreenCollision extends Collidable {

    private final int BORDER_OFFSET = 57;

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        if (ball.getX() < BORDER_OFFSET - 17) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(BORDER_OFFSET - 17);
        }

        else if (ball.getX() > (GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET)) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(GameBoard.WIDTH - ball.getWidth() - BORDER_OFFSET);
        }

        if (ball.getY() < BORDER_OFFSET - 10) {
            ball.setDy(Math.abs(ball.getDy()));
            ball.setY(BORDER_OFFSET - 10);
        }

        ShieldBarrier shield = board.getShield();
        if (shield != null && ball.getBounds().intersects(shield.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));
            ball.setY(shield.getY() - ball.getHeight());
            return;
        }

        if (ball.getY() > GameBoard.HEIGHT - ball.getHeight()) {
            board.removeBall(ball);
        }
    }
}
