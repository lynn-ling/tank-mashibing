package com.company;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 1;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    //要让坦克随机移动
    private Random random = new Random();

    //让坦克们游戏开局就动起来
    private boolean moving = true;
    private TankFrame tf = null;

    //加坦克的属性living为true
    private boolean living = true;
    //默认坦克阵营为敌方阵营
    private Group group = Group.BAD;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y, Dir dir, Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        //判断如果坦克没活着，就要将它从敌人坦克列表里移除
        //如果不移除，只是不画(return)，那这辆死了的坦克就一直占着位置，不能透过死的坦克打到其他坦克
        if(!living) tf.tanks.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        move();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void move(){
        if(!moving){
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //让敌人坦克也打子弹，这里是随机取得100以内的随机数，如果数字大于95，就打子弹
        if(this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();
        //如果坦克为敌方坦克就让坦克每移动一步改变个方向
        if(this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();
    }

    private void randomDir() {
        //Dir.values()返回个数组，随机取上下左右四个方向
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
    }

    //添加die方法
    public void die() {
        this.living = false;
    }
}