package Collidable;

import GameBoard.GameBoard;
import java.util.List;

public class CollisionManager {
    private final List<Collidable> collidables;

    public CollisionManager() {
        collidables = List.of(
                new BallScreenCollision(),
                new BallPaddleCollision(),
                new BallBrickCollision(),
                new ItemPaddleCollision() //  thêm xử lý va chạm giữa vật phẩm và thanh đỡ
        );
    }

    public void checkAll(GameBoard board, int prevX, int prevY) {
        for (Collidable c : collidables) {
            c.checkCollision(board, prevX, prevY);
        }
    }
}
