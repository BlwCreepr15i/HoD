package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.Wormhole;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kH;

    public final int screenX;
    public final int screenY;
    int hasKey;

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
        hasKey = 0;
    }

    public void getPlayerImage() {

        bright = setup("player_1.png");
        dark = setup("player_2.png");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uT = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+imageName));
            image = uT.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {   e.printStackTrace();   }
        return image;
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
                gp.obj[i] = null;
                hasKey++;
                gp.ui.showMessage("You got a key!");
                break;
            case "wormhole":

                if (((Wormhole) gp.obj[i]).isActivated) {
                    gp.ui.showMessage("Press F while moving toward wormhole to interact.");

                    if (kH.fPressed) {
                        gp.ui.levelFinished = true; // something happens...
                        ((Wormhole) gp.obj[i]).isActivated = false;
                    }
                    break;
                } else {
                    if (hasKey <= 0 && gp.level <= 1) {
                        gp.ui.showMessage("You need a key!");
                        break;
                    } else if (hasKey <= 1 && gp.level >= 2) {
                        gp.ui.showMessage("You need TWO keys!");
                        break;
                    }
                }

                // If the player has enough key(s) AND wormhole is NOT activated:
                try {
                    // This changes the color/picture of the wormhole
                    gp.obj[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/wormhole_1.png"));
                } catch(IOException e) { }

                if (gp.level <= 1) hasKey--;
                if (gp.level >= 2) hasKey-=2;

                gp.ui.showMessage("You activated the wormhole using a key!");
                gp.ui.showMessage("\nPress F to interact.");
                ((Wormhole) gp.obj[i]).isActivated = true;
                break;
        }
    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = switch (brightness) {
            case "dark" -> dark;
            case "bright" -> bright;
            default -> null;
        };

        g2d.drawImage(image, screenX, screenY, null);



    }
}