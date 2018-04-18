import java.awt.*;

/**
 * Created by владислав on 15.04.2018.
 */
public class GameBack {

    private Color color;

    public GameBack() {
        color = Color.MAGENTA;
    }

    public void update() {};

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    };


}
