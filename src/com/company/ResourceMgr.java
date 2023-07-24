package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    /*当ResourceMgr class被load到内存的时候就会执行下面这个static代码块
      1.读取图片的方式不太好，会要求以后要执行这个程序的人电脑里也有一个一模一样的路径，里面存放着这个图片，很不方便
      BufferedImage image = ImageIO.read(new File("F:\\project\\mashibing-tank\\tank\\tank\\src\\images\\bulletD.gif"));
      2.我把存放图片的文件夹放到src文件夹里面，这样等我把代码打包成jar包后，放到哪台电脑上都能正常打开图片
      BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
    */

    //一次性把下面这几张图片(向上，向下，向左，向右方向的坦克)都加载到内存里，到时就可以直接使用了
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
    //一次性加载子弹图片到内存里
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
