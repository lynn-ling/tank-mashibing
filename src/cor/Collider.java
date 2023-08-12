package cor;

import com.company.GameObject;

//负责两个游戏物体之间的碰撞。添加这个接口的好处是，到时添加新的物体的时候，不需要改原来的代码，只需添加即可
public interface Collider {
    void collide(GameObject o1,GameObject o2);
}
