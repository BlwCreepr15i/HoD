package main;

import entity.Player;
import environment.EnvironmentManager;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 23;
    public final int maxWorldRow = 23;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;
    final int FPS = 40;

    public int level = 1;


    KeyHandler key = new KeyHandler();
    TileManager tileM = new TileManager(this);
    public CollisionManager cm = new CollisionManager(this);
    public AssetManager am = new AssetManager(this);
    public UI ui = new UI(this);
    EnvironmentManager em = new EnvironmentManager(this);
    Thread gameThread;

    // Entities and Objects
    public Player player = new Player(this, key);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(108, 108, 108));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void setupGame() {
        am.setObject();
        em.setup();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1_000_000_000) timer = 0;
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (key.isDebugging) {
            drawStart = System.nanoTime();
        }

        tileM.draw(g2d);
        for (SuperObject so : obj) {
            if (so == null) continue;
            so.draw(g2d, this);
        }

        player.draw(g2d);
        em.draw(g2d);
        ui.draw(g2d);

        if (key.isDebugging) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2d.setColor(Color.WHITE);
            g2d.drawString("Draw Time:" + passed, 10, 400);
            System.out.println("Draw time - " + passed);
        }

        g2d.dispose();
    }
}