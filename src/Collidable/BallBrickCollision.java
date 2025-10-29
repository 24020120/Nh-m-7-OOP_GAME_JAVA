package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import Item.Shield;
import Item.MultiBallItem;
import Item.ShootingItem;
import java.util.Random;

public class BallBrickCollision extends Collidable {

    private Random rand = new Random();

    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();

                // Xử lý rơi item với xác suất
                double chance = rand.nextDouble();
                int spawnX = brick.getX() + brick.getWidth() / 2 - 10;
                int spawnY = brick.getY();

                if (chance < 0.10) { // 10% chance Shield
                    board.addItem(new Shield(spawnX, spawnY));
                } else if (chance < 0.20) { // 10% chance MultiBall
                    board.addItem(new MultiBallItem(spawnX, spawnY));
                } else if (chance < 0.30) { // 10% chance ShootingItem (MỚI)
                    board.addItem(new ShootingItem(spawnX, spawnY));
                }

                // Xử lý bounce
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