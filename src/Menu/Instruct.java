package Menu;

<<<<<<< HEAD

import Game.Main; // SỬA LỖI: Import lớp Main từ package Game
import Game.Main;
import javax.swing.*;
import java.awt.*;

public class Instruct extends JPanel {

    private Image imageInstruct;

    public Instruct() {
        imageInstruct = new ImageIcon("images/instruction.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageInstruct, 0, 0, getWidth(), getHeight(), this);
    private Main mainFrame;
    private Image imageInstruct;

    public Instruct(Main mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                    g.drawImage(imageInstruct, 0, 0, getWidth(), getHeight(), this);

            }
        };


            imageInstruct = new ImageIcon("images/instruction.png").getImage();

        Return returnPanel = new Return(mainFrame);
        add(imagePanel, BorderLayout.CENTER);
        add(returnPanel, BorderLayout.SOUTH);
    }
}

