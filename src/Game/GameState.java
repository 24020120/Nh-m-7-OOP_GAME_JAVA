package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public int score = 0;
    public int lives = 0;
    public int level = 1;
    public double paddleX = 0.0;

    public List<double[]> balls = new ArrayList<>();

    public List<int[]> bricks = new ArrayList<>();

    public GameState() {}
}
