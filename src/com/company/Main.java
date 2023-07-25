package com.company;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //读取配置文件里initTankCount的key对应的value值，为10，这里会new出10辆坦克
        //因为在PropertyMgr文件里定义的get方法返回值类型为Object，而这里要的是int类型
        //所以需要类型强转
        int initTankCount =  Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD,tf));
        }

        //加入背景音乐
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
