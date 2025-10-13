package Collidable;

import GameObject.*;
import java.awt.Rectangle;

public class BallBrickCollision {
    public static void handleBallBrick(Ball ball, Brick brick) {
        Rectangle ballBounds = ball.getBounds();
        Rectangle brickBounds = brick.getBounds();
        
        if (brick.isVisible() == false) return;

        double overLapLeft = ballBounds.x + ballBounds.width - brickBounds.x;
        double overLapRight = brickBounds.x + brickBounds.width - ballBounds.x;
        double overLapTop = ballBounds.y + ballBounds.height - brickBounds.y;
        double overLapBottom = brickBounds.y + brickBounds.height - ballBounds.y;

        double minOverLap = Math.min(Math.min(overLapTop, overLapBottom), Math.min(overLapRight, overLapLeft));

        if (minOverLap == overLapBottom || minOverLap == overLapTop) {
            ball.setSpeedX( - ball.getSpeedX());
        }
        else if (minOverLap == overLapLeft || minOverLap == overLapRight) {
            ball.setSpeedY( - ball.getSpeedY());
        }

        brick.setVisible(false);
    }
}
