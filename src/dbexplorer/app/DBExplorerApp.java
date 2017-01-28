package dbexplorer.app;

import javax.swing.*;
import java.awt.*;

/**
 * Created by RENT on 2017-01-28.
 */
public class DBExplorerApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("myWorkbench");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(1200, 600));

            DBExplorerPanel explorerPanel = new DBExplorerPanel();

            frame.setContentPane(explorerPanel);
            frame.pack();
            frame.setVisible(true);

        });
    }
}
