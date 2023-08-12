package com.company;

import cor.BulletTankCollider;
import cor.Collider;
import cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200,400, Dir.DOWN,Group.GOOD,this);


    /*
    以后只要有新的物体，只需要扔到objects里面即可，不用再一个一个new list了
    所以下面这些就不要了
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
     */
    private List<GameObject> objects = new ArrayList<>();

    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();

    public GameModel(){
        int initTankCount =  Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD,this));
        }

    }

    //以后要加物体的时候只要调用这个方法即可
    public void add(GameObject go){
        this.objects.add(go);
    }

    //以后要删减物体的时候只要调用这个方法即可
    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量："+bullets.size(),10,60);
//        g.drawString("敌人的数量："+tanks.size(),10,80);
//        g.drawString("爆炸的数量："+explodes.size(),10,100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                collider.collide(o1,o2);
                collider2.collide(o1,o2);
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}
