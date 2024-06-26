package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        if (gp.level == 1) {
            loadMap("maps/world01.txt");
        } if (gp.level == 2) {
            loadMap("maps/world02.txt");
        }
    }

    public void getTileImage() {
        setup(0, "grass.png", false);
        setup(1, "stone_bricks.png", true);
        setup(2, "purple_trap.png", false);
        tile[2].hasEffect = true;

    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uT = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/"+imageName));
            tile[index].image = uT.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) { /* nothing here*/ }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            // e.getStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        int worldCol = 0, worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2d.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
