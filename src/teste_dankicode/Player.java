package teste_dankicode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{
    //rectangle é do java que manipula vetores e ajuda na detecção colisão

    //variável para definir velocidade
    public int spd = 4;
    //variáveis para definir direção
    public boolean right, up, down, left;

    public boolean moved;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int dir = 1;

    public Player( int x, int y) {
        //define eixos do player e tamanho = 32x32
        super(x,y, 32, 32);
    }

    //tick = nome padrão para lógica
    public void tick() {
        moved=false;
        //ir para direita e esquerda = eixo x
        if(right && World.isFree(x+spd, y)) {
            x+=spd; //aumentar o x = ir para a direita
            moved=true;
            dir = 1;
        } else if (left && World.isFree(x-spd, y)) {
            x-=spd; //diminuir o x = ir para esquerda
            moved=true;
            dir = -1;
        }
        //ir para cima ou para baixo = eixo y
        if(up && World.isFree(x, y-spd)) {
            y-=spd; //diminur o y = ir para cima
            moved=true;
        } else if (down && World.isFree(x, y+spd)) {
            y+=spd; //aumentar o y = ir para baixo
            moved=true;
        }

        if(moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.player_front.length) {
                    curAnimation = 0;
                }
            }
        }

        if(shoot) {
            shoot = false;
            bullets.add(new Bullet(x,y,dir));
        }

        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }

    }

    //render = renderização dos objetos
    public void render(Graphics g) {
//    g.setColor(Color.blue); //define a cor
//    g.fillRect(x, y, width, height); //define eixo e tamanho
        g.drawImage(Spritesheet.player_front[curAnimation],x,y,32,32,null);
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }

}
