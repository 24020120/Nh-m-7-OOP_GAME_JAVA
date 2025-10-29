package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import Item.Shield;
import Item.MultiBallItem;
import Item.ShootingItem;
import Item.GhostBallItem;
import java.util.Random;

public class BallBrickCollision extends Collidable {

    private Random rand = new Random();

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                // Nếu bóng ở ghost mode → phá gạch không nảy lại
                if (ball.isGhostMode()) {
                    brick.hit();
                    board.getScore().addScore(10);
                    board.incrementDestroyedBricks();
                    continue;
                }

                // Phá gạch bình thường
                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();

                // Xác suất rơi item
                double chance = rand.nextDouble();
                int spawnX = brick.getX() + brick.getWidth() / 2 - 10;
                int spawnY = brick.getY();

                if (chance < 0.10) {
                    board.addItem(new Shield(spawnX, spawnY));
                } else if (chance < 0.20) {
                    board.addItem(new MultiBallItem(spawnX, spawnY));
                } else if (chance < 0.30) {
                    board.addItem(new ShootingItem(spawnX, spawnY));
                } else if (chance < 0.40) {
                    board.addItem(new GhostBallItem(spawnX, spawnY));
                }

                // Xử lý bật lại
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
