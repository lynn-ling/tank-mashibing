package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {
    public static void main(String[] args) {
        try {
            //这种读取图片的方式不太好，会要求以后要执行这个程序的人电脑里也有一个一模一样的路径，里面存放着这个图片，很不方便
            BufferedImage image = ImageIO.read(new File("F:\\project\\mashibing-tank\\tank\\tank\\src\\images\\bulletD.gif"));
            //我把存放图片的文件夹放到src文件夹里面，这样等我把代码打包成jar包后，放到哪台电脑上都能正常打开图片
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
