package cor;

import com.company.Bullet;
import com.company.GameObject;
import com.company.Tank;

public class TankTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.x = t1.oldX;
                t1.y = t1.oldY;
                t2.x = t2.oldX;
                t2.y = t2.oldY;
            }
        }else{
            return;
        }
    }
}
