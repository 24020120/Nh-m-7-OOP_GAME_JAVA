package GameBoard;

import java.util.ArrayList;
import java.util.List;
import GameObject.Brick;

public class Level {

    public static List<Brick> createLevel(int levelNumber, int boardWidth) {
        List<Brick> bricks = new ArrayList<>();

        final int BRICK_WIDTH = 50;
        final int BRICK_HEIGHT = 18;
        final int BRICK_SPACING = 2;
        final int START_Y_OFFSET = 70;
        final int COLS = 12; 
        int[][] layouts = {
            // Level 1
            {
                1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1
            },
            // Level 2
            {
                1,1,0,0,0,0,0,0,0,0,1,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,1,1,1,1,1,1,1,1,1,1,1
            },
            // Level 3
            {
                1,1,1,1,1,1,1,1,1,1,1,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,0,0,0,0,0,0,0,0,0,0,1,
                1,1,1,1,1,1,1,1,1,1,1,1
            },
            // Level 4
            {
                0,0,1,1,1,0,0,1,1,1,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0,
                0,1,1,1,1,1,1,1,1,1,1,0,
                0,1,1,1,1,1,1,1,1,1,1,0,
                0,0,1,1,1,1,1,1,1,1,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,0,1,1,1,1,0,0,0,0,
                0,0,0,0,0,1,1,0,0,0,0,0
            }
        };

        int level = Math.min(Math.max(1, levelNumber), layouts.length);
        int[] layout = layouts[level - 1];

        int rows = layout.length / COLS;

        int totalWidth = COLS * (BRICK_WIDTH + BRICK_SPACING) - BRICK_SPACING;
        int startX = (boardWidth - totalWidth) / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < COLS; col++) {
                int index = row * COLS + col;
                if (index < layout.length && layout[index] == 1) {
                    int x = startX + col * (BRICK_WIDTH + BRICK_SPACING);
                    int y = START_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SPACING);
                    bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, (row + col) % 2));
                }
            }
        }

        return bricks;
    }
}
