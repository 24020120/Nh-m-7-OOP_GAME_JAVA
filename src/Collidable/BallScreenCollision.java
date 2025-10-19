/*package Collidable;

import GameObject.*;


public class BallScreenCollision {

    public static void handleScreenCollision(Ball ball) {
        int x = ball.getX();
        int y = ball.getY();
        int diameter = ball.getDiameter();
        int screenWidth = ball.getScreenWidth();
        int screenHeight = ball.getScreenHeight();

        if (x <= 0) {
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
        }

        else if (x + diameter >= screenWidth) {
            ball.setSpeedX(-Math.abs(ball.getSpeedX()));
        }

        if (y <= 0) {
            ball.setSpeedY(Math.abs(ball.getSpeedY()));
        }

        else if (y + diameter >= screenHeight) {
            ball.reset();
        }
    }
}*/