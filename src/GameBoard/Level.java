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

        int[][] layouts = {
                // Level 1: 4 hàng, 12 cột - đầy đủ gạch
                {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                },
                // Level 2: 5 hàng, 12 cột - pattern viền
                {
                        1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                },
                // Level 3: 5 hàng, 12 cột - pattern khung
                {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                },
                // Level 4: 8 hàng, 12 cột - pattern kim cương
                {
                        0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0,
                        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                        0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0,
                        0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0
                }
        };

        int level = Math.min(Math.max(1, levelNumber), layouts.length);
        int[] layout = layouts[level - 1];

        // Tính số cột và hàng dựa trên layout
        int cols = 12; // Tất cả layout đều có 12 cột
        int rows = layout.length / cols;

        // Tính toán vị trí bắt đầu để căn giữa
        int totalWidth = cols * (BRICK_WIDTH + BRICK_SPACING) - BRICK_SPACING;
        int startX = (boardWidth - totalWidth) / 2;

        // Tạo bricks từ layout
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int index = row * cols + col;
                if (index < layout.length && layout[index] == 1) {
                    int x = startX + col * (BRICK_WIDTH + BRICK_SPACING);
                    int y = START_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SPACING);
                    // Sử dụng constructor với typeIndex để tạo gạch nhiều màu
                    bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, (row + col) % 2));
                }
            }
        }

        return bricks;
    }
}