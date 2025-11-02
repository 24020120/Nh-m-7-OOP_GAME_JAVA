package Collidable;

import Game.SoundManager;
import GameBoard.GameBoard;
import GameObject.Ball;

public class BallPaddleCollision extends Collidable {

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        var player = board.getPlayer();

        if (player.getBounds().intersects(ball.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));

            double hitPoint = ball.getBounds().getCenterX();
            double paddleCenter = player.getBounds().getCenterX();
            double relativeIntersect = (hitPoint - paddleCenter) / (player.getWidth() / 2.0);

            ball.setDx(relativeIntersect * 5.0);

            SoundManager.getInstance().playSound("ball_bounce");
        }
    }
}