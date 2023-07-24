package com.company;

import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;


    TankFrame tf = null;

    //定义step，每画一次，就让step加1
    private int step = 0;

    public Explode(int x, int y,TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        //每画一次，step就加1，第一次画explodes里的第一张，第二次画第二张，以此类推
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        //等到step大于等于explodes长度的时候，就不再画了，从explodes的list里移除，否则会一直爆炸
        if(step >= ResourceMgr.explodes.length) tf.explodes.remove(this);
    }




}
