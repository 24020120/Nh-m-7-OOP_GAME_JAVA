package Collidable;

import GameBoard.GameBoard;

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

        if (ball.getY() > GameBoard.HEIGHT - ball.getHeight()) {
            board.setGameOver(true);
        }
    }
}