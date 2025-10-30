package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import GameObject.ShieldBarrier;

public class BallScreenCollision extends Collidable {

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {



        if (ball.getX() < 0) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(0);
        }

        else if (ball.getX() > (GameBoard.WIDTH - ball.getWidth())) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(GameBoard.WIDTH - ball.getWidth());
        }


        if (ball.getY() < 0) {
            ball.setDy(Math.abs(ball.getDy()));
            ball.setY(0);
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