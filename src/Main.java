import Menu.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game.GameLoop();
            }
        });
    }
}