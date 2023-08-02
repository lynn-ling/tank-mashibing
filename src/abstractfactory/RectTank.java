package abstractfactory;

import com.company.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {

    FireStrategy fs ;

    int x, y;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

    public static int WIDTH = 40;
    public static int HEIGHT = 40;

    private Random random = new Random();

    private boolean moving = true;
    TankFrame tf = null;

    private boolean living = true;

    Group group = Group.BAD;


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

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD){
            String goodFSName = (String)PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            fs = new DefaultFireStrategy();
        }
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
        if(!living) tf.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(this.group==Group.GOOD?Color.RED:Color.WHITE);
        g.fillRect(x,y,40,40);
        g.setColor(c);
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

        if(this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x <0) x = 0;
        if(this.y < 30) y = 30;
        if(this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH) x = TankFrame.GAME_WIDTH - RectTank.WIDTH;
        if(this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + RectTank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for(Dir dir:dirs) {
            tf.gf.createBullet(bX, bY, dir, group, tf);
        }

        if(group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}