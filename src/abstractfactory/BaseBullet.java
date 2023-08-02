package abstractfactory;

import com.company.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWith(BaseTank tank);
}
