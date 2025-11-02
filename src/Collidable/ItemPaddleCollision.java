package Collidable;

import Game.SoundManager;
import GameBoard.GameBoard;
import Item.Item;
import GameObject.Ball;

public class ItemPaddleCollision extends Collidable {

    /**
     * Kiểm tra va chạm giữa Item và Paddle.
     * Phương thức này bỏ qua các tham số ball, prevX, prevY.
     */
    @Override
    public void checkCollision(GameBoard board, Ball ball, int prevX, int prevY) {
        var player = board.getPlayer();
        var items = board.getItems();
        if (items == null || player == null) return;

        for (int i = items.size() - 1; i >= 0; i--) {
            Item it = items.get(i);
            if (it == null) continue;

            if (player.getBounds().intersects(it.getBounds())) {

                it.applyEffect(board);
                it.deactivate();

                SoundManager.getInstance().playSound("item_collect");
            }
        }
    }
}