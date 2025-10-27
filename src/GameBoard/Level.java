package GameBoard;

import java.util.ArrayList;
import java.util.List;
import GameObject.Brick;

public class Level {

    // Create bricks for the given level number. boardWidth is used to center the layout.
    public static List<Brick> createLevel(int levelNumber, int boardWidth) {
        List<Brick> bricks = new ArrayList<>();

        switch (levelNumber) {
            case 1:
            default:
                final int BRICK_X = 10;
                final int BRICK_Y = 6;
                final int BRICK_WIDTH = 48;
                final int BRICK_HEIGHT = 20;
                final int START_Y_OFFSET = 70;
                final int brickSpacing = 2;

                int totalBrickWidth = BRICK_X * (BRICK_WIDTH + brickSpacing);
                int startX = (boardWidth - totalBrickWidth) / 2;

                for (int i = 0; i < BRICK_X; i++) {
                    for (int j = 0; j < BRICK_Y; j++) {
                        bricks.add(new Brick(
                                startX + i * (BRICK_WIDTH + brickSpacing),
                                START_Y_OFFSET + j * (BRICK_HEIGHT + brickSpacing),
                                BRICK_WIDTH, BRICK_HEIGHT));
                    }
                }
                break;
            // Future levels can be added here with different layouts
            case 2:
                final int BRICK_X2 = 12;
                final int BRICK_Y2 = 5;
                final int BRICK_WIDTH2 = 60;
                final int BRICK_HEIGHT2 = 20;
                final int START_Y_OFFSET2 = 80;
                final int brickSpacing2 = 3;

                int totalBrickWidth2 = BRICK_X2 * (BRICK_WIDTH2 + brickSpacing2);
                int startX2 = (boardWidth - totalBrickWidth2) / 2;

                for (int i = 0; i < BRICK_X2; i++) {
                    for (int j = 0; j < BRICK_Y2; j++) {
                        bricks.add(new Brick(
                                startX2 + i * (BRICK_WIDTH2 + brickSpacing2),
                                START_Y_OFFSET2 + j * (BRICK_HEIGHT2 + brickSpacing2),
                                BRICK_WIDTH2, BRICK_HEIGHT2));
                    }
                }
                break;
        }

        return bricks;
    }
}
