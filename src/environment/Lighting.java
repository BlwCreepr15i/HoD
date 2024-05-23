package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp, int cSize) {

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) darknessFilter.getGraphics();

//        Area screenArea = new Area(new Rectangle2D.Double(0,0, gp.screenWidth, gp.screenHeight));
        int centerX = gp.player.screenX + (gp.tileSize/2);
        int centerY = gp.player.screenY + (gp.tileSize/2);

//        double x = centerX - (cSize/2);
//        double y = centerY - (cSize/2);
        // circle
//        Shape cShape = new Ellipse2D.Double(x, y, cSize, cSize);
//        Area lightArea = new Area(cShape);
//        screenArea.subtract(lightArea);

        // gradient color
        Color[] color = new Color[5];
        float[] fraction = new float[5];

        color[0] = new Color(0,0,0,0F);
        color[1] = new Color(0,0,0,0.25F);
        color[2] = new Color(0,0,0,0.5F);
        color[3] = new Color(0,0,0,0.75F);
        color[4] = new Color(0,0,0,0.97F);

        fraction[0] = 0F;
        fraction[1] = 0.25F;
        fraction[2] = 0.5F;
        fraction[3] = 0.75F;
        fraction[4] = 1F;

        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (cSize/2), fraction, color);
        g2d.setPaint(gPaint);

//        g2d.fill(lightArea);

        // Draw screen rectangle
//        g2d.setColor(new Color(0,0,0,0.94F));
//        g2d.fill(screenArea);
        g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2d.dispose();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(darknessFilter, 0, 0, null);
    }
}
