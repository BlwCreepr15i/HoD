package main;

import object.Key;
import object.Wormhole;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Wormhole();
        gp.obj[0].worldX = (2-1) * gp.tileSize;
        gp.obj[0].worldY = (21-1) * gp.tileSize;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = (8-1) * gp.tileSize;
        gp.obj[1].worldY = (3-1) * gp.tileSize;

        gp.obj[2] = new Key();
        gp.obj[2].worldX = (15-1) * gp.tileSize;
        gp.obj[2].worldY = (21-1) * gp.tileSize;
    }
}
