import java.awt.*;

/**
 * Created by владислав on 16.04.2018.
 */
public class Wave {

    //Fields
    private int number;
    private int multiplier;
    private String text;
    private long timer;
    private long delay;
    private long timerDiff;


    //Constructor
    public Wave() {
        number = 1;
        multiplier = 10;
        timer = 0;
        delay = 5000;
        timerDiff = 0;
        text = " --- В О Л Н А --- ";
    }

    //Methods
    public void createEnemies() {
        int enemyCount = number * multiplier;
        if (number < 5) {
            while(enemyCount > 0) {
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (number < 10) {
            while(enemyCount > 0) {
                int type = 1;
                int rank = 2;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (number < 15) {
            while(enemyCount > 0) {
                int type = 1;
                int rank = 3;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (number < 20) {
            while(enemyCount > 0) {
                int type = 1;
                int rank = 4;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (number < 25) {
            while(enemyCount > 0) {
                int type = 1;
                int rank = 5;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        number++;
    }

    public void update() {
         if (GamePanel.enemies.isEmpty() && timer == 0) {
             timer = System.nanoTime();
         }
         if (timer > 0) {
             timerDiff += (System.nanoTime() - timer) / 1000000;
             timer = System.nanoTime();
         }
         if (timerDiff > delay) {
             createEnemies();
             timer = 0;
             timerDiff = 0;
         }
    }
    public boolean showWave() {
        if (timer != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void draw(Graphics2D g) {
        double divider = delay / 180;
        double alpha = timerDiff / divider;
        alpha = Math.sin(Math.toRadians(alpha)) * 255;
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 255) {
            alpha = 255;
        }
        g.setFont(new Font("consolas", Font.PLAIN, 20));
        g.setColor(new Color(255, 255, 255, (int) alpha));
        String s = "" + number + "ая" + text;
        long length = (long) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, GamePanel.WIDTH / 2 - (int) (length / 2), GamePanel.HEIGHT / 2);
    }
}
