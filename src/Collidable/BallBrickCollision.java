package Collidable;

import GameBoard.GameBoard;
import Item.Shield;

public class BallBrickCollision extends Collidable {
    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var ball = board.getBall();

        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();

                // Random chance to spawn a Shield item when a brick is destroyed
                // 10% chance currently; spawn position centered on the brick
                if (Math.random() < 0.10) {
                    int spawnX = brick.getX() + brick.getWidth() / 2 - 10; // center-ish
                    int spawnY = brick.getY();
                    board.addItem(new Shield(spawnX, spawnY));
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
