package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage bright, dark;
    public String brightness;
    public Rectangle solidArea;
    public boolean inCollision = false;


}