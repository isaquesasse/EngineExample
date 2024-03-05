package teste_dankicode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Blocks> blocks = new ArrayList<Blocks>();

    public World() {
        //colocar blocos em cima
        for(int xx = 0; xx < 20; xx++) {
            blocks.add(new Blocks(xx*32,0));
        }
        //colocar blocos em baixo
        for(int xx = 0; xx < 20; xx++) {
            blocks.add(new Blocks(xx*32,480-32));
        }
        //colocar blocos esquerda da vertical
        for(int yy = 0; yy < 15; yy++) {
            blocks.add(new Blocks(0,yy*32));
        }
        //colocar blocos na direita da vertical
        for(int yy = 0; yy < 15; yy++) {
            blocks.add(new Blocks(640-32,yy*32));
        }

        blocks.add(new Blocks(220,100));
    }

    public static boolean isFree(int x, int y) {
        for(int i = 0; i < blocks.size(); i++) {
            Blocks actualBlock = blocks.get(i);

            if(actualBlock.intersects(new Rectangle(x,y,32,32))) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g) {
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
    }

}
