import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by владислав on 15.04.2018.
 */

//Паааанелька
public class GamePanel extends JPanel implements Runnable{


    public static int WIDTH = 650;//1350;
    public static int HEIGHT = 650;//700;

    public static int mouseX;
    public static int mouseY;

    public static boolean leftMouse;

    private Thread thread = new Thread(this);

    private BufferedImage image;
    private Graphics2D g;

    private int FPS;
    private double millisToFPS;
    private long timerFPS;
    private int sleepTime;

    public enum STATES {
        MENU,
        PLAY
    }

    public static STATES state = STATES.MENU;

    public static GameBack backgound;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Menu menu;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        thread.start();

        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        FPS = 30;
        millisToFPS = 1000/FPS;
        sleepTime = 0;
        leftMouse = false;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backgound = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menu = new Menu();

        while (true) {
            timerFPS = System.nanoTime();
            if (state.equals(STATES.MENU)) {
                backgound.update();
                backgound.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
            }
            if (state.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

            timerFPS = (System.nanoTime() - timerFPS)/100000;

            if (millisToFPS > timerFPS) {
                sleepTime = (int) (millisToFPS -timerFPS);
            } else {
                sleepTime = 1;
            }

            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;

        }
    }

    public void gameUpdate() {
        //Background upd
        backgound.update();

        //Player upd
        player.update();

        //Bullet upd
        for (int i = 0;i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.remove()) {
                bullets.remove(i);
                i--;
            }
        }

        //enemies upd
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        //Bullets-enemies collide
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            for(int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();

                double dx = ex - bx;
                double dy = ey - by;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if((int) dist <= (e.getR() + b.getR())) {
                    e.hit();
                    bullets.remove(j);
                    j--;
                    if (e.remove()) {
                        enemies.remove(i);
                        i--;
                        break;
                    }
                }

            }
        }

        //Player-enemy collides
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();

            double dx = ex - px;
            double dy = ey - py;

            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= (e.getR() + player.getR())) {
                e.hit();
                player.hit();
                if (e.remove()) {
                    enemies.remove(i);
                    i--;
                }
            }
        }

        //Wave update
        wave.update();
    }

    public void gameRender() {
        //Draw backgound
        backgound.draw(g);

        //Draw player
        player.draw(g);

        //Draw bullet
        for(Bullet bullet : bullets) {
            bullet.draw(g);
        }

        //Draw enemy
        for(Enemy enemy : enemies) {
            enemy.draw(g);
        }

        //Draw wave
        if (wave.showWave()) {
            wave.draw(g);
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}
