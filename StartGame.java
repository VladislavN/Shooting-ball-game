import javax.swing.*;

/**
 * Created by владислав on 15.04.2018.
 */

public class StartGame {

    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("Belorussian Shooter Boy");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);                 //display Frame in center of screen
        startFrame.setVisible(true);
        panel.start();
    }

}
