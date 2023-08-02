package abstractfactory;

import com.company.Audio;
import com.company.ResourceMgr;
import com.company.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode{


    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;


    TankFrame tf = null;

    private int step = 0;

    public RectExplode(int x, int y,TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if(step >= 5) tf.explodes.remove(this);

//        for (int i = 0; i < 4; i++) {
//            g.fillRect(x,y,10*step,10*step);
//            step++;
//        }
//        for (int i = 0; i < 5; i++) {
//            if(step == -1) tf.explodes.remove(this);
//            g.fillRect(x,y,10*step,10*step);
//            step--;
//        }

        g.setColor(c);
    }
}
