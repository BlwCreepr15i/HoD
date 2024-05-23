package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        kH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 8, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 5;
        speed = 4;
        brightness = "dark";
        direction = "up";
    }

    public void getPlayerImage() {

        try {

            bright = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_1.png"));
            dark = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_2.png"));

        } catch (IOException e) {
            System.out.println("Image Issue!");
//            e.printStackTrace();
        }
    }

    public void update() {

        if (!(kH.upPressed || kH.downPressed || kH.leftPressed || kH.rightPressed)) return;

        if (kH.upPressed) {
            brightness = "bright";
            direction = "up";
        } else if (kH.downPressed) {
            brightness = "bright";
            direction = "down";
        } else if (kH.leftPressed) {
            brightness = "dark";
            direction = "left";
        } else { // key.rightPressed
            brightness = "dark";
            direction = "right";
        }

        // Check tile collision
        inCollision = false;
        gp.cm.checkTile(this);

        int objIndex = gp.cm.checkObject(this, true);
        pickUpObject(objIndex);

        if (!inCollision) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
    }

    public void pickUpObject(int i) {

        if (i == 999) return;

        String objName = gp.obj[i].name;

        switch(objName) {
            case "key":
                hasKey++;
                gp.obj[i] = null;
                break;
            case "wormhole":
                if (hasKey > 0) {
                    try {
                        gp.obj[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/wormhole_1.png"));
                    } catch(IOException e) { }
                    hasKey--;
                }
                break;
        }
    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = switch (brightness) {
            case "dark" -> dark;
            case "bright" -> bright;
            default -> null;
        };

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);



    }
}