package abstractfactory;

import com.company.Dir;
import com.company.Group;

import java.awt.*;

public abstract class BaseTank {
    public Group group;

    //这里已经有rect，在RectTank里就不能再有了
    public Rectangle rect;

    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public abstract void fire() ;

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);
}
