import sun.security.jgss.GSSCaller;

import java.awt.*;

/**
 * Created by владислав on 18.04.2018.
 */
public class Menu {

    //Field
    private int buttonWidth;
    private int buttonHeight;
    private Color color;
    private String s1;
    private int transp;

    //Construction
    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;
        transp = 0;

        color = Color.GRAY;
        s1 = "Play";
    }

    //Methods
    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH  / 2 - buttonWidth / 2
                && GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
                && GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2
                && GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transp = 60;
            if (GamePanel.leftMouse) {
                GamePanel.state = GamePanel.STATES.PLAY;
            }
        } else {
            transp = 0;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRect((GamePanel.WIDTH / 2) - (buttonWidth / 2),
                (GamePanel.HEIGHT / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
        g.setColor(new Color(255, 255, 255, transp));
        g.fillRect((GamePanel.WIDTH / 2) - (buttonWidth / 2),
                (GamePanel.HEIGHT / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));
        g.setColor(color);
        g.setFont(new Font("Consolas", Font.BOLD, 40));
        long length = (int) g.getFontMetrics()
                .getStringBounds(s1, g).getWidth();
        g.drawString(s1, (int) (GamePanel.WIDTH / 2 - length / 2),
                (int) (GamePanel.HEIGHT / 2 + buttonHeight / 4));
    }

}
