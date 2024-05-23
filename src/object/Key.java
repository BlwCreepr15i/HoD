package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperObject{

    GamePanel gp;

    public Key(GamePanel gp) {
        this.gp = gp;
        name = "key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key.png"));
            uT.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) { }

    }
}
