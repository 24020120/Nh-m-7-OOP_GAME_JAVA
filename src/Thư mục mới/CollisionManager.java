package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;
import java.util.List;

public class CollisionManager {
    private final List<Collidable> collidables;

    public CollisionManager() {
        collidables = List.of(
                new BallScreenCollision(),
                new BallPaddleCollision(),
                new BallBrickCollision(),
                new ItemPaddleCollision()
        );
    }

    /**
     * Kiểm tra tất cả va chạm LIÊN QUAN ĐẾN BÓNG cho một quả bóng cụ thể.
     * Được gọi lặp đi lặp lại cho mỗi quả bóng từ GameBoard.
     */
    public void checkBallCollisions(GameBoard board, Ball ball, int prevX, int prevY) {
        for (Collidable c : collidables) {
            if (!(c instanceof ItemPaddleCollision)) {
                c.checkCollision(board, ball, prevX, prevY);
            }
        }
    }

    /**
     * Kiểm tra tất cả va chạm LIÊN QUAN ĐẾN ITEM.
     * Được gọi một lần mỗi frame từ GameBoard.
     */
    public void checkItemCollisions(GameBoard board) {
        for (Collidable c : collidables) {

            if (c instanceof ItemPaddleCollision) {

                c.checkCollision(board, null, 0, 0);
            }
        }
    }
}