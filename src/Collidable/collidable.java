package Collidable;

import GameBoard.GameBoard;
import GameObject.Ball;

public abstract class Collidable {
<<<<<<< HEAD
    public abstract void checkCollision(GameBoard board, int prevX, int prevY);
}
=======
    /**
     * Kiểm tra va chạm.
     * @param board Bảng điều khiển game
     * @param ball Quả bóng cụ thể đang được kiểm tra (có thể là null)
     * @param prevX Vị trí X trước đó của bóng
     * @param prevY Vị trí Y trước đó của bóng
     */
    public abstract void checkCollision(GameBoard board, Ball ball, int prevX, int prevY);
<<<<<<< HEAD
}
=======
}
>>>>>>> 2aa8d091d2a4d1486ab9e30b1cba7fe81896d396
>>>>>>> 3dbf6617d898df83c8a813bfff90d0b871a9e758
