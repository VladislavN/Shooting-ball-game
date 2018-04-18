import java.awt.*;

import static java.lang.Math.*;

/**
 * Created by владислав on 16.04.2018.
 */
public class Enemy {

    //Fields
    private double x;
    private double y;
    private int r;
    private double speed;
    private double dx;
    private double dy;
    private double health;

    private int type;
    private int rank;

    private Color color;

    //Constructor
    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        switch (type) {
            case(1):
                color = Color.GREEN;
                switch (rank) {
                    case (1):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 7;

                        speed = 2;
                        health = 2;

                        double angle1 = Math.toRadians(Math.random()*360);
                        dx = sin(angle1) * speed;
                        dy = cos(angle1) * speed;
                        break;
                    case (2):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 8;

                        speed = 3;
                        health = 3;

                        double angle2 = Math.toRadians(Math.random()*360);
                        dx = sin(angle2) * speed;
                        dy = cos(angle2) * speed;
                        break;
                    case (3):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 9;

                        speed = 4;
                        health = 4;

                        double angle3 = Math.toRadians(Math.random()*360);
                        dx = sin(angle3) * speed;
                        dy = cos(angle3) * speed;
                        break;
                    case (4):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 10;

                        speed = 4;
                        health = 5;

                        double angle4 = Math.toRadians(Math.random()*360);
                        dx = sin(angle4) * speed;
                        dy = cos(angle4) * speed;
                        break;
                    case (5):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        r = 20;

                        speed = 8;
                        health = 20;

                        double angle5 = Math.toRadians(Math.random()*360);
                        dx = sin(angle5) * speed;
                        dy = cos(angle5) * speed;
                        break;
                }
        }
    }

    //Methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean remove() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    public void hit() {
        health--;
    }
    public void update() {
        x += dx;
        y += dy;

        if (x < 0 && dx < 0) {
            dx = -dx;
        }
        if (x > GamePanel.WIDTH && dx > 0) {
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            dy = -dy;
        }
        if (y > GamePanel.HEIGHT && dy > 0) {
            dy = -dy;
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval( (int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval( (int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}
