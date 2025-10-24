package Collidable;

import GameBoard.GameBoard;

public class BallPaddleCollision extends Collidable {

    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var ball = board.getBall();
        var player = board.getPlayer();

        if (player.getBounds().intersects(ball.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));

            double hitPoint = ball.getBounds().getCenterX();
            double paddleCenter = player.getBounds().getCenterX();
            double relativeIntersect = (hitPoint - paddleCenter) / (player.getWidth() / 2.0);

            ball.setDx(relativeIntersect * 5.0);
        }
    }
}


