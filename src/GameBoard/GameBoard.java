package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import Collidable.CollisionManager;
import Game.Main;
import GameObject.*;
import Menu.LevelMenu;
import Item.Item;

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
    private List<Item> items;
    private score score;
    private ShieldBarrier shield;
    private LevelMenu levelMenu;

    private boolean gameOver = false;
    private boolean gameWin = false;
    private int totalBricks = 0;
    private int destroyedBricksCount = 0;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameBoard(Main mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }

    // Cho phép Main truyền LevelMenu để đọc level hiện tại
    public void setLevelMenu(LevelMenu levelMenu) {
        this.levelMenu = levelMenu;
    }

    public void initGame() {
        gameOver = false;
        gameWin = false;
        destroyedBricksCount = 0;

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

        items = new ArrayList<>();
        shield = null;

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
        if (leftPressed) player.move(-1);
        if (rightPressed) player.move(1);

        int prevX = ball.getX();
        int prevY = ball.getY();
        ball.update();

        collisionManager.checkAll(this, prevX, prevY);

        // Cập nhật item rơi
        if (items != null) {
            for (int i = items.size() - 1; i >= 0; i--) {
                Item it = items.get(i);
                if (it == null) continue;
                it.update();
                if (!it.isActive() || it.getY() > HEIGHT) {
                    items.remove(i);
                }
            }
        }

        // Cập nhật shield (nếu có)
        if (shield != null) {
            shield.update();
            if (shield.isExpired()) {
                shield = null;
            }
        }

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

        // Vẽ item (rơi từ brick)
        if (items != null) {
            for (Item it : items) {
                it.draw(g);
            }
        }

        // Vẽ shield
        if (shield != null) {
            shield.draw(g);
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
        if (!gameOver && !gameWin && player != null) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightPressed = true;
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

    // Getters & Setters
    public Ball getBall() {
        return ball;
    }

    public Paddle getPlayer() {
        return player;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (items == null) items = new ArrayList<>();
        items.add(item);
    }

    public score getScore() {
        return score;
    }

    public ShieldBarrier getShield() {
        return shield;
    }

    public void setShield(ShieldBarrier shield) {
        this.shield = shield;
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
