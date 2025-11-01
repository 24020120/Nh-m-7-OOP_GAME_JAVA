package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;

public abstract class Collidable {
    /**
     * Kiểm tra va chạm.
     * @param board Bảng điều khiển game
     * @param ball Quả bóng cụ thể đang được kiểm tra (có thể là null)
     * @param prevX Vị trí X trước đó của bóng
     * @param prevY Vị trí Y trước đó của bóng
     */
    public abstract void checkCollision(GameBoard board, Ball ball, int prevX, int prevY);
}