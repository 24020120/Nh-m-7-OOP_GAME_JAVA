package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import Collidable.CollisionManager;
import Game.Main;
import GameObject.*;
import Menu.LevelMenu;
import Game.GameState;
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
    private int lives = 3;
    private int currentLevel = 1;


    private boolean gameOver = false;
    private boolean gameWin = false;
    private int totalBricks = 0;
    private int destroyedBricksCount = 0;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    

    private Image heartImage;

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
        lives = 3;

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
        heartImage = new ImageIcon("images/heart.png").getImage();

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

        // Khi phá hết gạch
        if (destroyedBricksCount == totalBricks) {
            gameWin = true;
        }

        // Nếu không còn bóng nào -> mất mạng
        if (balls.isEmpty() && !gameWin && !gameOver) {
            decreaseLife();
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

        // === VẼ KHUNG MẠNG (TRÁI TIM) Ở GÓC PHẢI ===
        if (heartImage != null) {
            int maxLives = 3; // tổng mạng tối đa
            int heartSize = 30;
            int spacing = 5;
            int margin = 10;

            // Kích thước khung cố định cho 3 trái tim
            int totalWidth = maxLives * (heartSize + spacing) - spacing;
            int totalHeight = heartSize;
            int x = WIDTH - totalWidth - margin - 20;
            int y = 10;

            // Khung chứa trái tim
            int framePadding = 8;
            int frameX = x - framePadding;
            int frameY = y - framePadding;
            int frameWidth = totalWidth + framePadding * 2;
            int frameHeight = totalHeight + framePadding * 2;

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Nền mờ
            g2.setColor(new Color(0, 0, 0, 120));
            g2.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 15, 15);

            // Viền cam
            g2.setStroke(new BasicStroke(3));
            g2.setColor(new Color(255, 140, 0));
            g2.drawRoundRect(frameX, frameY, frameWidth, frameHeight, 15, 15);

            // Viền sáng nhẹ bên trong
            g2.setColor(new Color(255, 200, 80, 120));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(frameX + 2, frameY + 2, frameWidth - 4, frameHeight - 4, 12, 12);

            // Vẽ trái tim tương ứng với số mạng hiện tại
            for (int i = 0; i < lives; i++) {
                g2.drawImage(heartImage, x + i * (heartSize + spacing), y, heartSize, heartSize, this);
            }
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
        try {
            if (gameThread != null) {
                gameThread.interrupt();
                gameThread = null;
            }
        } catch (Exception ignored) {}

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
    
    public void saveGameState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            GameState state = new GameState();

            state.score = (score != null) ? score.getScore() : 0;
            state.lives = this.lives;
            state.level = this.currentLevel;

        
            if (player != null)
                state.paddleX = player.getX();
            if (balls != null) {
            for (Ball b : balls) {
                double[] bs = new double[5];
                bs[0] = b.getX();
                bs[1] = b.getY();
                bs[2] = b.getDX();
                bs[3] = b.getDY();
                bs[4] = b.getDiameter();
                state.balls.add(bs);
            }
        }

        
        if (bricks != null) {
            for (Brick b : bricks) {
                if (b.isVisible()) {
                    state.bricks.add(new int[]{b.getX(), b.getY(), b.getWidth(), b.getHeight()});
                }
            }
        }

            out.writeObject(state);
          

        } catch (Exception ex) {
            ex.printStackTrace();
    }
}


    public void loadGameState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            GameState state = (GameState) in.readObject();


            if (score == null) score = new score();
                score.setScore(state.score);

            this.lives = state.lives;
            this.currentLevel = state.level;

       
            if (player == null) {
                player = new Paddle((int) state.paddleX, HEIGHT - 55, 100, 30);
            } else {
                player.setX((int) state.paddleX);
            }

        
            balls = new ArrayList<>();
            for (double[] bs : state.balls) {
                balls.add(new Ball((int) bs[0], (int) bs[1], (int) bs[4], bs[2], bs[3]));
            }

       
            bricks = new ArrayList<>();
            for (int[] b : state.bricks) {
            bricks.add(new Brick(b[0], b[1], b[2], b[3]));
        }
        totalBricks = bricks.size();

        collisionManager = new CollisionManager();

        items = new ArrayList<>();
        ballsToRemove.clear();
        shield = null;

        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new Thread(this);
            gameThread.start();
        }

        pauseMenu.setActive(false);
        gameOver = false;
        gameWin = false;
        repaint();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    public static boolean hasSavedGame() {
        return new java.io.File("savegame.dat").exists();
    }

    public void decreaseLife() {
        if (!heartBreaking && lives > 0) {
            heartBreaking = true;
            breakingHeartIndex = lives - 1; // trái tim cuối cùng bị vỡ
            heartBreakStartTime = System.currentTimeMillis();
        }
    }


}
