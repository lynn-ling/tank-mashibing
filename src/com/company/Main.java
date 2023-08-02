package com.company;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount =  Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.createTank(50 + i*80,200,Dir.DOWN,Group.BAD,tf));
        }

        //加入背景音乐
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
