package main;

import entity.Entity;
import object.SuperObject;

public class CollisionManager {

    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity ent) {

        int entLWorldX = ent.worldX + ent.solidArea.x;
        int entRWorldX = ent.worldX + ent.solidArea.x + ent.solidArea.width;
        int entTWorldY = ent.worldY + ent.solidArea.y;
        int entBWorldY = ent.worldY + ent.solidArea.y + ent.solidArea.height;

        int entLCol = entLWorldX / gp.tileSize;
        int entRCol = entRWorldX / gp.tileSize;
        int entTRow = entTWorldY / gp.tileSize;
        int entBRow = entBWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(ent.direction) {
            case "up":
                entTRow = (entTWorldY - ent.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLCol][entTRow];
                tileNum2 = gp.tileM.mapTileNum[entRCol][entTRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    ent.inCollision = true;
                }
                if (gp.tileM.tile[tileNum1].hasEffect || gp.tileM.tile[tileNum2].hasEffect) {
                    gp.player.speed = (int) (Math.random()*-10)+5; // act as a special slow-down tile
                } else {
                    gp.player.speed = 4;
                }
                break;
            case "down":
                entBRow = (entBWorldY + ent.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLCol][entBRow];
                tileNum2 = gp.tileM.mapTileNum[entRCol][entBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    ent.inCollision = true;
                }
                if (gp.tileM.tile[tileNum1].hasEffect || gp.tileM.tile[tileNum2].hasEffect) {
                    gp.player.speed = (int) (Math.random()*-10)+5;
                } else {
                    gp.player.speed = 4;
                }
                break;
            case "left":
                entLCol = (entLWorldX - ent.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entLCol][entTRow];
                tileNum2 = gp.tileM.mapTileNum[entLCol][entBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    ent.inCollision = true;
                }
                if (gp.tileM.tile[tileNum1].hasEffect || gp.tileM.tile[tileNum2].hasEffect) {
                    gp.player.speed = (int) (Math.random()*-10)+5;
                } else {
                    gp.player.speed = 4;
                }
                break;
            case "right":
                entRCol = (entRWorldX + ent.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entRCol][entTRow];
                tileNum2 = gp.tileM.mapTileNum[entRCol][entBRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    ent.inCollision = true;
                }
                if (gp.tileM.tile[tileNum1].hasEffect || gp.tileM.tile[tileNum2].hasEffect) {
                    gp.player.speed = (int) (Math.random()*-10)+5;
                } else {
                    gp.player.speed = 4;
                }
                break;
        }
    }

    public int checkObject(Entity ent, boolean player) {
        int index = 999;
        for (int i=0; i<gp.obj.length; i++) {
            SuperObject so = gp.obj[i];
            if (so == null) continue;

            ent.solidArea.x += ent.worldX;
            ent.solidArea.y += ent.worldY;

            so.solidArea.x += so.worldX;
            so.solidArea.y += so.worldY;

            switch(ent.direction) {
                case "up":
                    ent.solidArea.y -= ent.speed;
                    if (ent.solidArea.intersects(so.solidArea)) {
                        // collision!
                        if (so.collision) ent.inCollision = true;
                        if (player) index = i;
                    }
                    break;
                case "down":
                    ent.solidArea.y += ent.speed;
                    if (ent.solidArea.intersects(so.solidArea)) {
                        // collision!
                        if (so.collision) ent.inCollision = true;
                        if (player) index = i;
                    }
                    break;
                case "left":
                    ent.solidArea.x -= ent.speed;
                    if (ent.solidArea.intersects(so.solidArea)) {
                        // collision!
                        if (so.collision) ent.inCollision = true;
                        if (player) index = i;
                    }
                    break;
                case "right":
                    ent.solidArea.x += ent.speed;
                    if (ent.solidArea.intersects(so.solidArea)) {
                        // collision!
                        if (so.collision) ent.inCollision = true;
                        if (player) index = i;
                    }
                    break;
            }
            ent.solidArea.x = ent.solidAreaDefaultX;
            ent.solidArea.y = ent.solidAreaDefaultY;
            so.solidArea.x = so.solidAreaDefaultX;
            so.solidArea.y = so.solidAreaDefaultY;
        }
        return index;
    }
}
