package Collidable;

import GameBoard.GameBoard;

import GameObject.ShieldBarrier;

public class BallScreenCollision extends Collidable {

    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var ball = board.getBall();

        if (ball.getX() < 0) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(0);
        } else if (ball.getX() > (GameBoard.WIDTH - ball.getWidth())) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(GameBoard.WIDTH - ball.getWidth());
        }

        if (ball.getY() < 0) {
            ball.setDy(Math.abs(ball.getDy()));
            ball.setY(0);
        }

        // if a shield barrier exists and the ball intersects it, bounce the ball
        ShieldBarrier shield = board.getShield();
        if (shield != null && ball.getBounds().intersects(shield.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));
            ball.setY(shield.getY() - ball.getHeight());
            return;
        }

        if (ball.getY() > GameBoard.HEIGHT - ball.getHeight()) {
            board.setGameOver(true);
        }
    }
}
