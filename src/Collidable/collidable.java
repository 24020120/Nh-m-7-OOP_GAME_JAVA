package Collidable;

import GameBoard.GameBoard;

public abstract class Collidable {
    public abstract void checkCollision(GameBoard board, int prevX, int prevY);
}