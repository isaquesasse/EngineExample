package teste_dankicode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.IllegalFormatWidthException;

public class Spritesheet {

    public static BufferedImage spritesheet;

    public static BufferedImage[] player_front;

    public static BufferedImage[] enemy_front;

    public static BufferedImage tileWall;

    public static BufferedImage[] bullet;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/res/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2];

        player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
        player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);

        enemy_front = new BufferedImage[2];

        enemy_front[0] = Spritesheet.getSprite(274, 256, 16, 16);
        enemy_front[1] = Spritesheet.getSprite(291, 256, 16, 16);

        tileWall = Spritesheet.getSprite(280,221,16,16);

        bullet = new BufferedImage[3];

        bullet[0] = Spritesheet.getSprite(64, 185,8,16);
        bullet[1] = Spritesheet.getSprite(72, 185,10,16);
        bullet[2] = Spritesheet.getSprite(81, 185,10,16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int heigth) {
        return spritesheet.getSubimage(x, y, width, heigth);
    }

}
