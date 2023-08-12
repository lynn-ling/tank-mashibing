package cor;

import com.company.Bullet;
import com.company.GameObject;
import com.company.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            b.collideWith(t);
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }else{
            //其他情况则暂时不做任何处理
            return;
        }
    }
}
