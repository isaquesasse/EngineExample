package teste_dankicode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle{
    //rectangle é do java que manipula vetores e ajuda na detecção colisão

    //variável para definir velocidade
    public int spd = 3;
    //variáveis para definir direção
    public int right = 1, up = 0, down = 0, left = 0;

    public boolean moved;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int dir = 1;

    public void perseguirPlayer() {
        Player p = Game.player;
        if(x < p.x && World.isFree(x+spd, y)) {
            if(new Random().nextInt(100) < 50)
                x += spd;
        } else if(x > p.x && World.isFree(x-spd, y)) {
            if(new Random().nextInt(100) < 50)
            x-=spd;
        }
        if(y < p.y && World.isFree(x, y+spd)) {
            if(new Random().nextInt(100) < 50)
            y+=spd;
        } else if(y > p.y && World.isFree(x, y-spd)) {
            if(new Random().nextInt(100) < 50)
            y-=spd;
        }
    }

    public Enemy( int x, int y) {
        //define eixos do enemy e tamanho = 32x32
        super(x,y, 32, 32);
    }

    //tick = nome padrão para lógica
    public void tick() {
        moved=true;

        perseguirPlayer();

        if(moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.enemy_front.length) {
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
        g.drawImage(Spritesheet.enemy_front[curAnimation],x,y,32,32,null);
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }

}

