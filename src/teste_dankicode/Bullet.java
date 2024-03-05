package teste_dankicode;

import java.awt.*;

public class Bullet extends Rectangle {

    public int dir = 1;
    public int speed = 8;

    public int frames = 0;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public Bullet(int x, int y, int dir) {
        super(x + 16, y + 16, 20, 20);
        this.dir = dir;
    }


    public void tick() {
        x += speed * dir;

        frames++;
        if (frames == 60) {
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.bullet[0], x, y, 16, 16, null);
    }

}
