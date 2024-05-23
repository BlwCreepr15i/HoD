package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int w, int h) {
        BufferedImage scaledImage = new BufferedImage(w, h, 2);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, w, h, null);
        g2d.dispose();

        return scaledImage;
    }
}
