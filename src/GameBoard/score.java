package GameBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class score {
    private int scoreValue = 0;

    // Lớp phụ cho hiệu ứng +X bay lên
    private static class FloatingScore {
        int x, y, value;
        float alpha = 1f; // độ trong suốt
        int lifetime = 60; // ~1 giây nếu 60fps

        FloatingScore(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        boolean update() {
            y -= 0.5;           // bay lên
            alpha -= 0.01f;   // mờ dần
            lifetime--;
            return lifetime > 0 && alpha > 0;
        }
    }

    private ArrayList<FloatingScore> floatingScores = new ArrayList<>();

    // Gọi khi tăng điểm
    public void addScore(int points, int x, int y) {
        this.scoreValue += points;
        floatingScores.add(new FloatingScore(x, y, points));
    }

    public int getScore() {
        return scoreValue;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // ----- Vẽ nền mờ cho khung SCORE -----
        int boxWidth = 230;
        int boxHeight = 55;
        int boxX = 30;
        int boxY = 20;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.setColor(new Color(0, 0, 0, 180)); // nền đen mờ
        g2d.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        // ----- Viền sáng -----
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2d.setColor(new Color(255, 255, 255, 120));
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        // ----- Chữ SCORE -----
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 34));
        g2d.setColor(Color.WHITE);
        g2d.drawString("SCORE:", 45, 58);

        // Viền chữ (đổ bóng vàng)
        g2d.setColor(new Color(255, 215, 0));
        g2d.drawString(String.valueOf(scoreValue), 165, 58);

        // ----- Hiệu ứng +X bay lên -----
        Iterator<FloatingScore> it = floatingScores.iterator();
        while (it.hasNext()) {
            FloatingScore fs = it.next();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fs.alpha));
            g2d.setFont(new Font("Arial", Font.BOLD, 22));
            g2d.setColor(new Color(0, 255, 255, (int)(255 * fs.alpha))); // màu cyan mờ dần
            g2d.drawString("+" + fs.value, fs.x, fs.y);
            if (!fs.update()) it.remove();
        }

        g2d.dispose();
    }
}
