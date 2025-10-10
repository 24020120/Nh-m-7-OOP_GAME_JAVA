package Menu;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;

public class Menu {
    public static JMenuBar CreateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new DimensionUIResource(800, 450));
        menuBar.setOpaque(true);
        menuBar.setBackground(new ColorUIResource(255, 255, 255));
        
        return menuBar;
    }
}
