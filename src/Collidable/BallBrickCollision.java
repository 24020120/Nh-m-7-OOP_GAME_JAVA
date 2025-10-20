package Collidable;

import GameBoard.GameBoard;

public class BallBrickCollision extends Collidable {
    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var ball = board.getBall();

        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();

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
