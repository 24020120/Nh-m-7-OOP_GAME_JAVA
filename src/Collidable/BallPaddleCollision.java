package Collidable;

import GameObject.*;
import java.awt.Rectangle;

public class BallPaddleCollision {
    public static void handleBallPaddle(Ball ball, Paddle paddle) {
        Rectangle ballBounds = ball.getBounds();
        Rectangle paddleBounds = paddle.getBounds();

        if (!ballBounds.intersects(paddleBounds)) return; //check xem co va cham khong
        
        double ballCenterX   = ballBounds.x   + ballBounds.width  / 2.0;
        double paddleCenterX = paddleBounds.x + paddleBounds.width / 2.0;

        double hitPos = (ballCenterX - paddleCenterX) / (paddleBounds.width / 2.0);

        ball.setSpeedY(-Math.abs(ball.getSpeedY()));

        double newSpeedX = hitPos * ball.getBaseSpeedX(); //huong nay cua ball so voi mep paddle
        ball.setSpeedX(newSpeedX);

        ball.setY(paddleBounds.y - ballBounds.height - 1); //push ball ra khoi padđle
    }
}
