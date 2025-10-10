
package Menu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Start {

    public static JButton CreateStartButton() {
        JButton startButton = new JButton("START");
        startButton.setSize(50, 20);
        startButton.setVisible(true);
        startButton.setBackground(new ColorUIResource(0, 128, 0));
        return startButton;
    }
}
/*package Menu;

import Game.Main; // SỬA LỖI: Import lớp Main từ package Game
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Start extends JPanel {
    private Main gameFrame;
    private BufferedImage startBG;

    public Start(Main gameFrame) {
        this.gameFrame = gameFrame;
        setSize(800, 600);


            startBG = ImageIO.read(new File("images/background.png"));
        } catch (Exception e) {
            System.err.println("Không thể tải ảnh images/StartScreen.png. Sử dụng background mặc định.");
            startBG = createDefaultBackground();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameFrame.switchToPanel("MENU");
            }
        });
    }

    private BufferedImage createDefaultBackground() {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(new GradientPaint(0, 0, new Color(10, 20, 40), 800, 600, new Color(20, 40, 80)));
        g2d.fillRect(0, 0, 800, 600);
        g2d.dispose();
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (startBG != null) {
            g2d.drawImage(startBG, 0, 0, getWidth(), getHeight(), null);
        }
        drawClickInstruction(g2d);
    }

    private void drawClickInstruction(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        String instruction = "Click anywhere to start";
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(instruction)) / 2;
        int y = getHeight() - 50;
        g2d.setColor(Color.YELLOW);
        g2d.drawString(instruction, x, y);
    }
}*/

