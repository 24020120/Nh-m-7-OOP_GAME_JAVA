package GameBoard;

import javax.swing.JPanel;

import Collidable.CollisionManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.Main;
import GameObject.*;

public class GameBoard extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final int DELAY = 1000 / 60; // 60 FPS

    private Main mainFrame;
    private Thread gameThread;

    private Paddle player;
    private Ball ball;
    private CollisionManager collisionManager;
    private List<Brick> bricks;
    private score score; // Đảm bảo lớp Score đã được triển khai

    private boolean gameOver = false;
    private boolean gameWin = false;
    private int totalBricks = 0;
    private int destroyedBricksCount = 0;

    private Random rand = new Random();

    public GameBoard(Main mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    public void initGame() {
        gameOver = false;
        gameWin = false;
        destroyedBricksCount = 0;

        player = new Paddle(WIDTH / 2 - 50, HEIGHT - 40, 100, 30);
        ball = new Ball(WIDTH / 2 - 50, HEIGHT - 40, 12, 3, -3);
        collisionManager = new CollisionManager();
        score = new score();
        bricks = new ArrayList<>();

        final int BRICK_X = 10;
        final int BRICK_Y = 6;
        final int BRICK_WIDTH = 48;
        final int BRICK_HEIGHT = 20;
        final int START_Y_OFFSET = 70;
        final int brickSpacing = 2;

        int totalBrickWidth = BRICK_X * (BRICK_WIDTH + brickSpacing);
        int startX = (WIDTH - totalBrickWidth) / 2;

        for (int i = 0; i < BRICK_X; i++) {
            for (int j = 0; j < BRICK_Y; j++) {
                bricks.add(new Brick(
                        startX + i * (BRICK_WIDTH + brickSpacing),
                        START_Y_OFFSET + j * (BRICK_HEIGHT + brickSpacing),
                        BRICK_WIDTH, BRICK_HEIGHT));
            }
        }
        totalBricks = bricks.size();

        if (gameThread != null) {
            gameThread.interrupt();
        }
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double targetTick = 60.0;
        final double nsPerTick = 1000000000 / targetTick;
        double delta = 0;

        while (gameThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                if (!gameOver && !gameWin) {
                    update();
                }
                delta--;
            }

            repaint();

            try {
                Thread.sleep((long) (DELAY / 1.5));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void update() {
        int prevX = ball.getX();
        int prevY = ball.getY();
        ball.update();

        collisionManager.checkAll(this, prevX, prevY);

        if (destroyedBricksCount == totalBricks) {
            gameWin = true;
        }
    }

    /*
    private void checkCollisions(int prevX, int prevY) {

        if (ball.getX() < 0) {
            ball.setDx(Math.abs(ball.getDx()));
            ball.setX(0);
        } else if (ball.getX() > (WIDTH - ball.getWidth())) {
            ball.setDx(-Math.abs(ball.getDx()));
            ball.setX(WIDTH - ball.getWidth());
        }

        if (ball.getY() < 0) {
            ball.setDy(Math.abs(ball.getDy()));
            ball.setY(0);
        }

        if (ball.getY() > HEIGHT - ball.getHeight()) {
            gameOver = true;
        }


        if (player.getBounds().intersects(ball.getBounds()) && ball.getDy() > 0) {
            ball.setDy(-Math.abs(ball.getDy()));

            double hitPoint = ball.getBounds().getCenterX();
            double paddleCenter = player.getBounds().getCenterX();
            double relativeIntersect = (hitPoint - paddleCenter) / (player.getWidth() / 2.0);

            ball.setDx(relativeIntersect * 5.0);
        }

        for (Brick brick : bricks) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {

                brick.hit();
                score.addScore(10);
                destroyedBricksCount++;

                if (prevX + ball.getWidth() <= brick.getX() || prevX >= brick.getX() + brick.getWidth()) {
                    ball.setDx(-ball.getDx());
                }
                else {
                    ball.setDy(-ball.getDy());
                }

                break;
            }
        }
    }

    */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }

        score.draw(g);

        if (gameOver) {

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", WIDTH / 2 - 140, HEIGHT / 2);
            g.drawString("Press SPACE to restart", WIDTH / 2 - 240, HEIGHT / 2 + 60);


        }

        if (gameWin) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME WIN!", WIDTH / 2 - 140, HEIGHT / 2);
            g.drawString("Press SPACE to restart", WIDTH / 2 - 240, HEIGHT / 2 + 60);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && !gameWin) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                player.move(-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                player.move(1);
            }
        }

        if ((gameOver || gameWin) && e.getKeyCode() == KeyEvent.VK_SPACE) {
            initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    //Getter
    public Ball getBall() {
        return ball;
    }

    public Paddle getPlayer() {
        return player;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public score getScore() {
        return score;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void incrementDestroyedBricks() {
        destroyedBricksCount++;
    }
}