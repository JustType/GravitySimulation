import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter {
    private Star selected;
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            selected.modVelocity(-1, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            selected.modVelocity(1, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            selected.modVelocity(0, -1);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            selected.modVelocity(0, 1);
        }
    }


    public void setSelected(Star s){
        this.selected = s;
    }
}
