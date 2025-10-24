package Collidable;

import GameBoard.GameBoard;
import Item.Shield; //  Giữ import này để có thể spawn item

public class BallBrickCollision extends Collidable {
    @Override
    public void checkCollision(GameBoard board, int prevX, int prevY) {
        var ball = board.getBall();

        for (var brick : board.getBricks()) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                // Khi va chạm, ẩn gạch và tăng điểm
                brick.hit();
                board.getScore().addScore(10);
                board.incrementDestroyedBricks();

                //  Thêm chức năng: 10% khả năng spawn item Shield khi phá gạch
                if (Math.random() < 0.10) {
                    int spawnX = brick.getX() + brick.getWidth() / 2 - 10; // căn giữa gạch
                    int spawnY = brick.getY();
                    board.addItem(new Shield(spawnX, spawnY));
                }

                //  Xử lý hướng bật lại của bóng
                if (prevX + ball.getWidth() <= brick.getX() || prevX >= brick.getX() + brick.getWidth()) {
                    ball.setDx(-ball.getDx()); // va bên trái/phải
                } else {
                    ball.setDy(-ball.getDy()); // va trên/dưới
                }

                break; // chỉ xử lý 1 gạch mỗi frame
            }
        }
    }
}
