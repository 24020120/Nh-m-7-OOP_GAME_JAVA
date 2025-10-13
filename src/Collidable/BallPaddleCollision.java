package Collidable;

import GameObject.*;
import java.awt.Rectangle;

public class BallPaddleCollision {
    public static void handleBallPaddle(Ball ball, Paddle paddle) {
        Rectangle ballBounds = ball.getBounds();
        Rectangle paddleBounds = paddle.getBounds();

        double overLapTop = ballBounds.y + ballBounds.height - paddleBounds.y;
        double overLapleft = ballBounds.x + ballBounds.width - paddleBounds.x;
        double overLapRight = paddleBounds.x + paddleBounds.width - ballBounds.x;

        if (overLapTop >= 0 && overLapRight >= 0 && overLapleft >= 0) {
            ball.setSpeedY( - ball.getSpeedY());
        }
    }
}
