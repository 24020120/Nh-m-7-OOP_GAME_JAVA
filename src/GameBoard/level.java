package GameBoard;

import java.util.ArrayList;
import java.util.List;
import GameObject.Brick;

public class Level {

    public static List<Brick> createLevel(int levelNumber, int boardWidth) {
        List<Brick> bricks = new ArrayList<>();

       
        final int BRICK_WIDTH = 60;
        final int BRICK_HEIGHT = 20;
        final int BRICK_SPACING = 3;
        final int START_Y_OFFSET = 70;

        int[][] layouts = {
            {
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                
                
            },
            {
                1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            },
            {
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            },
            {
                0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0,
                0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
            }
        };

<<<<<<< HEAD
                for (int i = 0; i < BRICK_X; i++) {
                    for (int j = 0; j < BRICK_Y; j++) {
                        // (i + j) % 2 -> xen kẽ loại gạch 0 và 1
                        bricks.add(new Brick(
                                startX + i * (BRICK_WIDTH + brickSpacing),
                                START_Y_OFFSET + j * (BRICK_HEIGHT + brickSpacing),
                                BRICK_WIDTH, BRICK_HEIGHT,
                                (i + j) % 2));
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
                                BRICK_WIDTH2, BRICK_HEIGHT2,
                                (i + j) % 2));
                    }
                }

                break;
=======
       
        int level = Math.min(Math.max(1, levelNumber), layouts.length);
        int[] layout = layouts[level - 1];

        int cols = (level == 1) ? 12 : 12;
        int rows = layout.length / cols;
        
       
        int totalWidth = cols * (BRICK_WIDTH + BRICK_SPACING) - BRICK_SPACING;
        int startX = (boardWidth - totalWidth) / 2;

        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (layout[row * cols + col] == 1) {
                    int x = startX + col * (BRICK_WIDTH + BRICK_SPACING);
                    int y = START_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SPACING);
                    bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT));
                }
            }
>>>>>>> 2aa8d091d2a4d1486ab9e30b1cba7fe81896d396
        }

        return bricks;
    }
}
