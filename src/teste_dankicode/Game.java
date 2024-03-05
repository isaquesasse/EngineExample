package teste_dankicode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480;

    public static int SCALE = 3;
    public static Player player;

    public List<Enemy> enemies = new ArrayList<Enemy>();
    public World world;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        new Spritesheet();

        player = new Player(32,32);
        enemies.add(new Enemy(32,32));
        enemies.add(new Enemy(32,64));
        enemies.add(new Enemy(32,96));
        world = new World();
    }

    //tick responsável por movimentação/colisões
    public void tick() {

        player.tick();

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }
    }

    //renderiza gráficos
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0,77,0));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        world.render(g);

        player.render(g);

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }

        bs.show();
    }

    public static void main(String[] args) {

        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");

        frame.pack();
        //para a camêra ficar certa, é necessário o LocationRelativeTo ficar abaixo do .pack
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        //passa o true para quando o jogo iniciar
        new Thread(game).start();

    }

    @Override
    public void run() {

        //inicia as ações do jogo
        while(true) {
            tick();
            render();
            //a quantos FPS vai rodar, aqui no caso é 60 fps
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}
