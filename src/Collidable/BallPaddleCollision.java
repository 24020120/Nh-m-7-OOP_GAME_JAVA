/*package Collidable;

import GameObject.*;
import java.awt.Rectangle;

public class BallPaddleCollision {
    public static void handleBallPaddle(Ball ball, Paddle paddle) {
        Rectangle ballBounds = ball.getBounds();
        Rectangle paddleBounds = paddle.getBounds();
        if (ballBounds.intersects(paddleBounds) && ball.getSpeedY() > 0) {
            ball.setSpeedY( - Math.abs(ball.getSpeedY()));
            double relativeIntersectX = (ballBounds.getCenterX() - paddleBounds.getCenterX());
            double normalizedRelativeIntersectionX = relativeIntersectX / (paddleBounds.getWidth() / 2);
            double bounceAngleFactor = 0.5;
            ball.setSpeedX(normalizedRelativeIntersectionX * Math.abs(ball.getSpeedY()) * bounceAngleFactor);
        }
    }
}*/