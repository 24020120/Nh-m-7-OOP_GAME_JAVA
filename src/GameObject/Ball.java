package GameObject;

import java.awt.*;
import javax.swing.ImageIcon;

public class Ball extends GameObject {

    private boolean ghostMode = false; // status ball
    private long ghostEndTime = 0;

    private Image normalImage;
    private Image ghostImage;

    public Ball(int x, int y, int diameter, double dx, double dy) {
        super(x, y, diameter, diameter);
        this.dx = dx;
        this.dy = dy;

        normalImage = new ImageIcon("images/ball.png").getImage();
        ghostImage = new ImageIcon("images/ghostball.png").getImage();
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        // Hết thời gian thì tắt ghost mode
        if (ghostMode && System.currentTimeMillis() > ghostEndTime) {
            ghostMode = false;
        }
    }

    public void reset(int startX, int startY, double initialDx, double initialDy) {
        this.x = startX;
        this.y = startY;
        this.dx = initialDx;
        this.dy = initialDy;
    }

    @Override
    public void draw(Graphics g) {
        Image imgToDraw = ghostMode ? ghostImage : normalImage;

        if (imgToDraw != null) {
            // Luôn vẽ theo kích thước width, height của Ball
            g.drawImage(imgToDraw, x, y, width, height, null);
        } else {
            // fallback nếu không có ảnh
            g.setColor(ghostMode ? new Color(128, 0, 128) : Color.RED);
            g.fillOval(x, y, width, height);
        }
    }


    //  Ghost mode
    public void activateGhostMode(int durationSeconds) {
        ghostMode = true;
        ghostEndTime = System.currentTimeMillis() + durationSeconds * 1000L;
    }

    public boolean isGhostMode() {
        return ghostMode;
    }
}
