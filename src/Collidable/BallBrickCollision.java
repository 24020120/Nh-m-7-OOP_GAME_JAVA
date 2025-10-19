/*
package Collidable;
import GameObject.*;
import java.awt.Rectangle;

public class BallBrickCollision {
    public static void handleBallBrick(Ball ball, Brick brick) {
        Rectangle ballBounds = ball.getBounds();
        Rectangle brickBounds = brick.getBounds();

        if (!brick.isVisible()) return;

        if (ballBounds.intersects(brickBounds)) {

            brick.hit();


            double dx = ballBounds.getCenterX() - brickBounds.getCenterX();
            double dy = ballBounds.getCenterY() - brickBounds.getCenterY();

            double minX = (ballBounds.getWidth() + brickBounds.getWidth()) / 2;
            double minY = (ballBounds.getHeight() + brickBounds.getHeight()) / 2;

            if (Math.abs(dx * minY) > Math.abs(dy * minX)) {

                ball.setSpeedX(-ball.getSpeedX());
            } else {

                ball.setSpeedY(-ball.getSpeedY());
            }
        }
    }
}*/