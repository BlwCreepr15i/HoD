package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wormhole extends SuperObject {

    public boolean isActivated;

    public Wormhole() {
        name = "wormhole";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/wormhole_2.png"));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        collision = true;
        isActivated = false;
    }
}
