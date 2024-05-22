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

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        key = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
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
            y -= speed;
        } else if (key.downPressed) {
            brightness = "bright";
            y += speed;
        } else if (key.leftPressed) {
            brightness = "dark";
            x -= speed;
        } else if (key.rightPressed) {
            brightness = "dark";
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = switch (brightness) {
            case "dark" -> dark;
            case "bright" -> bright;
            default -> null;
        };

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);



    }
}