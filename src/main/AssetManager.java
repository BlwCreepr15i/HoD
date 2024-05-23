package main;

import object.Key;
import object.Wormhole;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Wormhole(gp);
        gp.obj[1] = new Key(gp);
        gp.obj[2] = new Key(gp);

        if (gp.level <= 1) {
            gp.obj[0].worldX = (2 - 1) * gp.tileSize;
            gp.obj[0].worldY = (21 - 1) * gp.tileSize;

            gp.obj[1].worldX = (8 - 1) * gp.tileSize;
            gp.obj[1].worldY = (3 - 1) * gp.tileSize;

            gp.obj[2].worldX = (15 - 1) * gp.tileSize;
            gp.obj[2].worldY = (21 - 1) * gp.tileSize;

        } else {
            gp.obj[0].worldX = (7 - 1) * gp.tileSize;
            gp.obj[0].worldY = (6 - 1) * gp.tileSize;

            gp.obj[1].worldX = (3 - 1) * gp.tileSize;
            gp.obj[1].worldY = (3 - 1) * gp.tileSize;

            gp.obj[2].worldX = (12 - 1) * gp.tileSize;
            gp.obj[2].worldY = (6 - 1) * gp.tileSize;
        }
    }
}
