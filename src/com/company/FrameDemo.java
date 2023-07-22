package com.company;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameDemo {
    public static void main(String[] args) {
        //new一个窗口对象
        Frame f = new Frame();
        //设置窗口大小
        f.setSize(800,600);
        //使窗口无法改变大小
        f.setResizable(false);
        //设置窗口的title
        f.setTitle("tank war");
        //使窗口可见，如果不写这行，使无法看到窗口的
        f.setVisible(true);
        //使得点击×可以关闭窗口
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
