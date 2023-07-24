package com.company;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 30;

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x,y;
    private Dir dir;

    private boolean living = true;
    TankFrame tf = null;
    //子弹也需要区分是我方坦克打出的还是敌人方坦克打出的
    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!living){
            tf.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }

    private void move(){
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

        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            living = false;
        }

    }

    //自己写入坦克碰撞检测的方法
    public void collideWith(Tank tank) {
        //判断子弹是否和坦克是一个阵营的，如果是一个阵营的则不执行碰撞检测，即不攻击本方队友坦克，只攻击敌人坦克
        if(this.group == tank.getGroup()) return;
        //判断坦克这个方块和子弹这个方块是否相交，需要使用到辅助类rectangle
        //取得子弹本身的矩形和坦克的矩形
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        //判断如果两个矩形相交，那么坦克和子弹都要消失
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
            tf.explodes.add(new Explode(x,y,tf));
        }
    }

    //添加die方法
    private void die() {
        this.living = false;
    }
}
