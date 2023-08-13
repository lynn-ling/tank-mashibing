package cor;

import com.company.GameObject;

import java.util.LinkedList;
import java.util.List;

//实现Collider接口的好处就是，两个ColliderChain就可以通过add方法连接在一起（add方法里传入的必须是Collider）
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain(){
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            //当链条里的某个结果为false时，则它后面的链条不再执行
            if(!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
