package Collidable;

import GameBoard.GameBoard;
import Item.Item;

public class ItemPaddleCollision extends Collidable {

    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var player = board.getPlayer();
        var items = board.getItems();
        if (items == null || player == null) return;

        for (int i = items.size() - 1; i >= 0; i--) {
            Item it = items.get(i);
            if (it == null) continue;
            if (player.getBounds().intersects(it.getBounds())) {
                // apply effect and mark inactive; GameBoard.update will remove it
                it.applyEffect(board);
                it.deactivate();
            }
        }
    }
}

