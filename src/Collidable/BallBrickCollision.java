package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import Item.Shield;
import Item.MultiBallItem;
import java.util.Random;

public class BallBrickCollision extends Collidable {

    private Random rand = new Random();

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        // var ball = board.getBall();

        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();


                double chance = rand.nextDouble();
                int spawnX = brick.getX() + brick.getWidth() / 2 - 10; // căn giữa gạch
                int spawnY = brick.getY();

                if (chance < 0.10) {
                    board.addItem(new Shield(spawnX, spawnY));
                } else if (chance < 0.20) {
                    board.addItem(new MultiBallItem(spawnX, spawnY));
                }

                if (prevX + ball.getWidth() <= brick.getX() || prevX >= brick.getX() + brick.getWidth()) {
                    ball.setDx(-ball.getDx());
                } else {
                    ball.setDy(-ball.getDy());
                }

                break;
            }
        }
    }
}