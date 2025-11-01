package Game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    public int score;
    public int lives;
    public int level;
    public double ballX, ballY, ballDX, ballDY;
    public double paddleX;
    public List<int[]> bricks = new ArrayList<>();

    public GameState() {}
}
