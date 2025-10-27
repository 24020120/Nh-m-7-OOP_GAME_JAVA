package GameBoard;

import java.util.ArrayList;
import java.util.List;
import GameObject.Brick;

public class Level {

    // Create bricks for the given level number. boardWidth is used to center the layout.
    public static List<Brick> createLevel(int levelNumber, int boardWidth) {
        List<Brick> bricks = new ArrayList<>();

        // Brick dimensions and spacing
        final int BRICK_WIDTH = 60;
        final int BRICK_HEIGHT = 20;
        final int BRICK_SPACING = 3;
        final int START_Y_OFFSET = 70;

        // Level definitions using 2D arrays (0 = no brick, 1 = brick)
        int[][] layouts = {
            // Level 1 - Simple pattern
            {
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                
            },
            // Level 2 - Frame pattern
            {
                1, 1, 0, 0, 1, 1,
                1, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1
            }
        };

        // Get the layout for current level (default to level 1 if invalid)
        int level = Math.min(Math.max(1, levelNumber), layouts.length);
        int[] layout = layouts[level - 1];

        // Calculate dimensions
        int cols = (level == 1) ? 4 : 6; // hardcoded for now; could be made dynamic
        int rows = layout.length / cols;
        
        // Center the bricks horizontally
        int totalWidth = cols * (BRICK_WIDTH + BRICK_SPACING) - BRICK_SPACING;
        int startX = (boardWidth - totalWidth) / 2;

        // Create bricks based on the layout
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (layout[row * cols + col] == 1) {
                    int x = startX + col * (BRICK_WIDTH + BRICK_SPACING);
                    int y = START_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SPACING);
                    bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT));
                }
            }
        }

        return bricks;
    }
}

