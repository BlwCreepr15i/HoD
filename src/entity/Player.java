package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler key;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        key = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 8, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 5;
        speed = 4;
        brightness = "dark";
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
        if (key.upPressed) {
            brightness = "bright";
            worldY -= speed;
        } else if (key.downPressed) {
            brightness = "bright";
            worldY += speed;
        } else if (key.leftPressed) {
            brightness = "dark";
            worldX -= speed;
        } else if (key.rightPressed) {
            brightness = "dark";
            worldX += speed;
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