package GameBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class score {
    private int scoreValue = 0;

    public void addScore(int points) {
        this.scoreValue += points;
    }

    public int getScore() {
        return scoreValue;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("SCORE " + scoreValue, 10, 40);
    }
}