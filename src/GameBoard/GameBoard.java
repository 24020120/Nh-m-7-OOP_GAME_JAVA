package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import Collidable.CollisionManager;
import Game.Main;
import GameObject.*;
import Menu.LevelMenu;
import Item.Item;

public class GameBoard extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final int DELAY = 1000 / 60;

    private Main mainFrame;
    private Thread gameThread;

    private Paddle player;
    private List<Ball> balls;
    private List<Ball> ballsToRemove = new ArrayList<>();
    private CollisionManager collisionManager;
    private List<Brick> bricks;
    private List<Item> items;
    private score score;
    private ShieldBarrier shield;
    private LevelMenu levelMenu;
    private PauseMenu pauseMenu;

    private boolean gameOver = false;
    private boolean gameWin = false;
    private int totalBricks = 0;
    private int destroyedBricksCount = 0;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Image backgroundImage;

    public GameBoard(Main mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        loadBackgroundImage();
        pauseMenu = new PauseMenu(mainFrame, this);
       
        addMouseListener(pauseMenu.getMouseAdapter());
        addMouseMotionListener(pauseMenu.getMouseAdapter());

    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = new ImageIcon("images/game_background.png").getImage();
        } catch (Exception e) {
            backgroundImage = null;
        }
    }

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

        balls = new ArrayList<>();
        balls.add(new Ball(ballX, ballY, ballDiameter, 3, -3));

        ballsToRemove.clear();

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

        if (player != null) {
            player.clearBullets();
        }
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
                if (!pauseMenu.isActive() && !gameOver && !gameWin) {
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

        if (player != null) {
            player.update();
            checkBulletCollisions();
        }

        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
            Ball b = ballIterator.next();

            int prevX = b.getX();
            int prevY = b.getY();
            b.update();
            collisionManager.checkBallCollisions(this, b, prevX, prevY);
        }

        if (!ballsToRemove.isEmpty()) {
            balls.removeAll(ballsToRemove);
            ballsToRemove.clear();
        }

        if (items != null) {
            collisionManager.checkItemCollisions(this);
        }

        if (items != null) {
            Iterator<Item> itemIterator = items.iterator();
            while (itemIterator.hasNext()) {
                Item it = itemIterator.next();
                if (it == null) continue;
                it.update();
                if (!it.isActive() || it.getY() > HEIGHT) {
                    itemIterator.remove();
                }
            }
        }

        if (shield != null) {
            shield.update();
            if (shield.isExpired()) {
                shield = null;
            }
        }

        if (destroyedBricksCount == totalBricks) {
            gameWin = true;
        }

        if (balls.isEmpty() && !gameWin) {
            setGameOver(true);
        }
        if (pauseMenu != null && pauseMenu.isActive()) return;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (player == null || balls == null || bricks == null || score == null) {
            return;
        }

        player.draw(g);
        for (Ball b : balls) b.draw(g);
        for (Brick brick : bricks) brick.draw(g);
        if (items != null) for (Item it : items) it.draw(g);
        if (shield != null) shield.draw(g);
        score.draw(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", WIDTH / 2 - 140, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press SPACE to restart", WIDTH / 2 - 140, HEIGHT / 2 + 60);
        }

        if (gameWin) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME WIN!", WIDTH / 2 - 140, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press SPACE to restart", WIDTH / 2 - 140, HEIGHT / 2 + 60);
        }

        pauseMenu.draw((Graphics2D) g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (pauseMenu.isActive()) {
            if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_R) {
                togglePause();
            }
            return;
        }

        if (!gameOver && !gameWin && player != null) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                leftPressed = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                rightPressed = true;
        }

        if ((gameOver || gameWin) && e.getKeyCode() == KeyEvent.VK_SPACE) {
            initGame();
        }

        if (e.getKeyCode() == KeyEvent.VK_P) togglePause();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void togglePause() {
        boolean newState = !pauseMenu.isActive();
        pauseMenu.setActive(newState);
        leftPressed = false;
        rightPressed = false;
        repaint();
    }

    public void resumeGame() {
        pauseMenu.setActive(false);
        leftPressed = false;
        rightPressed = false;
        requestFocusInWindow();
    }

    public void exitGame() {
        if (mainFrame != null) mainFrame.switchToPanel("MENU");
        else System.exit(0);
    }

    public List<Ball> getBalls() { return balls; }
    public void addBall(Ball b) { if (this.balls != null) this.balls.add(b); }
    public void removeBall(Ball b) { if (this.balls != null) ballsToRemove.add(b); }
    public Paddle getPlayer() { return player; }
    public List<Brick> getBricks() { return bricks; }
    public List<Item> getItems() { return items; }
    public void addItem(Item item) { if (items == null) items = new ArrayList<>(); items.add(item); }
    public score getScore() { return score; }
    public ShieldBarrier getShield() { return shield; }
    public void setShield(ShieldBarrier shield) { this.shield = shield; }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver && mainFrame != null) mainFrame.switchToPanel("GAMEOVER");
    }
    public void incrementDestroyedBricks() { destroyedBricksCount++; }

    private void checkBulletCollisions() {
        if (player == null || bricks == null) return;
        List<Bullet> bullets = player.getBullets();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Brick> brickIterator = bricks.iterator();
            while (brickIterator.hasNext()) {
                Brick brick = brickIterator.next();
                if (brick.isVisible() &&
                        bullet.getX() < brick.getX() + brick.getWidth() &&
                        bullet.getX() + bullet.getWidth() > brick.getX() &&
                        bullet.getY() < brick.getY() + brick.getHeight() &&
                        bullet.getY() + bullet.getHeight() > brick.getY()) {
                    brick.hit();
                    bullet.setActive(false);
                    incrementDestroyedBricks();
                    score.addScore(10, brick.getX(), brick.getY());
                    break;
                }
            }
        }
    }
}
