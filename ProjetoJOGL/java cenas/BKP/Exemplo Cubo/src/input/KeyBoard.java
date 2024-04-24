package input;
import cena.Cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
/**
 *
 * @author Kakugawa
 */
public class KeyBoard implements KeyListener{
    private Cena cena;
    
    public KeyBoard(Cena cena){
        this.cena = cena;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                cena.horizontal+=5;
                break;
            case KeyEvent.VK_RIGHT:
                cena.horizontal-=5;
                break;
            case KeyEvent.VK_UP:
                cena.vertical+=5;
                break;
            case KeyEvent.VK_DOWN:
                cena.vertical-=5;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;               
        }
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        
        if(e.getKeyChar() == 'a') {
            System.out.println("Pressionou tecla a");
            cena.angulo+=20;
     
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
