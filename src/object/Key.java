package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperObject{

    public Key() {
        name = "key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key.png"));
        } catch (IOException e) { }

    }
}
