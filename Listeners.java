import java.awt.event.*;

/**
 * Created by владислав on 15.04.2018.
 */
public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

    @Override
    public void keyPressed(KeyEvent e) {
        keyHelper(e, true);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GamePanel.state = GamePanel.STATES.MENU;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            GamePanel.state = GamePanel.STATES.PLAY;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyHelper(e, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    private void keyHelper(KeyEvent e, boolean b) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            Player.up = b;
        }
        if (key == KeyEvent.VK_S) {
            Player.down = b;
        }
        if (key == KeyEvent.VK_A) {
            Player.left = b;
        }
        if (key == KeyEvent.VK_D) {
            Player.right = b;
        }
        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = b;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiringMouse = true;
            GamePanel.leftMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiringMouse = false;
            GamePanel.leftMouse = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}