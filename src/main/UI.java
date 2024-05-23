package main;

import object.Wormhole;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font monospace_30;
    BufferedImage wormholeImage;
    public boolean msgOn = false;
    public String msg = "";
    private int msgCnt = 0;
    private int cnt = 0;
    public boolean levelFinished;
    private boolean firstCalled;

    double playTime;
    DecimalFormat dF = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        monospace_30 = new Font("Monospace", Font.PLAIN, 30);
        Wormhole wh = new Wormhole(gp);
        wormholeImage = wh.image;
        firstCalled = true;
        levelFinished = false;
    }

    public void showMessage(String text) {
        msg = text;
        msgOn = true;
    }

    public void draw(Graphics2D g2d) {

        if (levelFinished) { // if level 1 is finished...
            // Display an informative message
            String text;
            int textLength;
            int x, y;

            if (gp.level == 1) {
                text = "";
                showMessage("You entered the wormhole! Advances to level 2.");
            } else if (gp.level > 1) {
                text = "You have completed the game, yay.";
            } else { // ? (won't happen)
                text = "Hello World";
                gp.level = 1;
            }
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenWidth/2 - (gp.tileSize*3);
            g2d.setFont(monospace_30);
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, x, y);


            if (gp.level > 1) {
                text = "Your time: " + dF.format(playTime);
                textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenWidth/2 + (gp.tileSize*2);
                g2d.drawString(text, x, y);
                gp.gameThread = null;
                return;
            }

            levelFinished = false;
            gp.level++;

            gp.am.setObject();
            gp.tileM = new TileManager(gp);
            gp.player.setDefaultValues();
            gp.player.worldX = gp.tileSize * 3;
            gp.player.worldY = gp.tileSize * 21;

        }


        g2d.setFont(monospace_30);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(wormholeImage, (int) (1.8*gp.tileSize), gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("Find ", 20, 50);

        if (firstCalled) {
            g2d.setColor(Color.RED);
            g2d.setFont(g2d.getFont().deriveFont(20F));
            g2d.drawString("Welcome to level 1.", gp.tileSize/2, gp.tileSize*4);
            if (++cnt>240) {
                firstCalled = false;
                cnt=0;
            }
        }

        // Playtime
        playTime += (double) 1/60;
        g2d.setColor(Color.WHITE);
        g2d.drawString("Time: "+dF.format(playTime), gp.tileSize*11, gp.tileSize);

        // Messages
        if (msgOn) {
            g2d.setFont(g2d.getFont().deriveFont(25F));
            g2d.drawString(msg, gp.tileSize/2, gp.tileSize*5);

            msgCnt++;

            if (msgCnt > 120) { // 2 seconds
                msgCnt = 0;
                msgOn = false;
            }
        }

    }
}
