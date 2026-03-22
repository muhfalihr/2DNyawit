package com.uph_lpjk.sawit2d.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.uph_lpjk.sawit2d.controller.GamePanel;
import com.uph_lpjk.sawit2d.controller.UtilityTools;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    }

    public void getTileImage() {

    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTools uTool = new UtilityTools();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("" + imageName + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
                String line = br.readLine();

                while(col<gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col==gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;

        }
    }
}
