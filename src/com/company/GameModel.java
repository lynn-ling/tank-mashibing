package com.company;

import cor.BulletTankCollider;
import cor.Collider;
import cor.ColliderChain;
import cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200,400, Dir.DOWN,Group.GOOD,this);

    private List<GameObject> objects = new ArrayList<>();

    ColliderChain chain = new ColliderChain();



    public GameModel(){
        int initTankCount =  Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD,this));
        }


        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));

    }

    public Tank getMainTank() {
        return myTank;
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);

        int bulletsNum = 0;
        int tanksNum = 0;
        int explodesNum = 0;
        for (int i = 0; i < objects.size(); i++) {
            if(objects.get(i) instanceof Bullet){
                bulletsNum++;
            }if(objects.get(i) instanceof Tank){
                tanksNum++;
            }if(objects.get(i) instanceof Explode){
                explodesNum++;
            }
        }
        g.drawString("子弹的数量："+bulletsNum,10,60);
        g.drawString("敌人的数量："+tanksNum,10,80);
        g.drawString("爆炸的数量："+explodesNum,10,100);
        g.setColor(c);

        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }


        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
            }
        }

    }


}
