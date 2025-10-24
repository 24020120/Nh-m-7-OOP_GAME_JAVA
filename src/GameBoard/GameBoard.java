package GameBoard;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Collidable.CollisionManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.Main;
import GameObject.*;
import Menu.LevelMenu;

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
    private score score;
    private LevelMenu levelMenu;

    private boolean gameOver = false;
    private boolean gameWin = false;
    private int totalBricks = 0;
    private int destroyedBricksCount = 0;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Random rand = new Random();

    public GameBoard(Main mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);

        initGame();

        // Đảm bảo focus sau khi hiển thị panel
        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
        addKeyListener(this);
    }

    public void setLevelMenu(LevelMenu levelMenu) {
        this.levelMenu = levelMenu;
    }

    public void initGame() {
        gameOver = false;
        gameWin = false;
        destroyedBricksCount = 0;

        player = new Paddle(WIDTH / 2 - 50, HEIGHT - 60, 100, 30);
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
                int typeIndex = (i + j) % 2;
                bricks.add(new Brick(
                        startX + i * (BRICK_WIDTH + brickSpacing),
                        START_Y_OFFSET + j * (BRICK_HEIGHT + brickSpacing),
                        BRICK_WIDTH, BRICK_HEIGHT,
                        typeIndex));
            }
        }

        int paddleWidth = 100;
        int paddleHeight = 30;
        int paddleX = WIDTH / 2 - paddleWidth / 2;
        int paddleY = HEIGHT - paddleHeight - 25;
        player = new Paddle(paddleX, paddleY, paddleWidth, paddleHeight);

        int ballDiameter = 12;
        int ballX = paddleX + (paddleWidth - ballDiameter) / 2;
        int ballY = paddleY - ballDiameter - 2;
        ball = new Ball(ballX, ballY, ballDiameter, 3, -3);

        collisionManager = new CollisionManager();
        score = new score();

        int levelToLoad = 1;
        if (levelMenu != null && levelMenu.getSelectedLevel() > 0) {
            levelToLoad = levelMenu.getSelectedLevel();
        }

        bricks = Level.createLevel(levelToLoad, WIDTH);
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
        if (leftPressed) {
            player.move(-1);
        }
        if (rightPressed) {
            player.move(1);
        }

        int prevX = ball.getX();
        int prevY = ball.getY();
        ball.update();

        collisionManager.checkAll(this, prevX, prevY);

        if (destroyedBricksCount == totalBricks) {
            gameWin = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (player == null || ball == null || bricks == null || score == null) {
            return;
        }

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
                leftPressed = true;
                player.move(-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightPressed = true;
                player.move(1);
            }
        }

        if ((gameOver || gameWin) && e.getKeyCode() == KeyEvent.VK_SPACE) {
            initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Getter
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
        if (gameOver && mainFrame != null) {
            mainFrame.switchToPanel("GAMEOVER");
        }
    }

    public void incrementDestroyedBricks() {
        destroyedBricksCount++;
    }
}
