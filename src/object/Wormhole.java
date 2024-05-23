package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wormhole extends SuperObject {

    GamePanel gp;
    public boolean isActivated;

    public Wormhole(GamePanel gp) {
        this.gp = gp;
        name = "wormhole";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/wormhole_2.png"));
            uT.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        collision = true;
        isActivated = false;
    }
}
