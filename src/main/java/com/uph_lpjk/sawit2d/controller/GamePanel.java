package com.uph_lpjk.sawit2d.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.uph_lpjk.sawit2d.entity.Entity;
import com.uph_lpjk.sawit2d.entity.Player;
import com.uph_lpjk.sawit2d.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final private int originalTileSize = 16;
    final private int scale = 3;

    final private int tileSize = originalTileSize*scale; // 48 * 48
    final private int maxScreenCol = 20;
    final private int maxScreenRow = 12;
    final private int screenWidth = (tileSize * maxScreenCol); // 960 px
    final private int screenHeight = (tileSize * maxScreenRow); // 576 px

    // WORLD SETTINGS
    final private int maxWorldCol = 50;
    final private int maxWorldRow = 50;
    final private int worldWidth = tileSize * maxWorldCol;
    final private int worldHeight = tileSize * maxWorldRow;

    // FOR FULL SCREEN
    private int screenWidth2 = screenWidth;
    private int screenHeight2 = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D g2;
    private boolean fullScreenOn = false;

    // FPS
    final private int FPS = 60;

    Thread gamThread;

    // INIT CONTROLLER
    final private KeyHandler keyH = new KeyHandler(this);

    // ENTITY AND OBJECT
    final private Player player = new Player(this, keyH);
    final private Entity obj[] = new Entity[20];
    
    final private CollisionChecker cChecker = new CollisionChecker(this);
    final private TileManager tileM = new TileManager(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // GETTER & SETTER

    // GAMEPANEL

    public int getTileSize() {
        return this.tileSize;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int getMaxWorldCol() {
        return this.maxWorldCol;
    }

    public int getMaxWorldRow() {
        return this.maxWorldRow;
    }

    public int getWorldWidth() {
        return this.worldWidth;
    }

    public int getWorldHeight() {
        return this.worldHeight;
    }

    // PLAYER

    public int getPlayerWorldX() {
        return this.player.getWorldX();
    }

    public int getPlayerWorldY() {
        return this.player.getWorldY();
    }

    public int getPlayerScreenX() {
        return this.player.getScreenX();
    }

    public int getPlayerScreenY() {
        return this.player.getScreenY();
    }

    // TILE MANAGER

    public void loadMap() {
        this.tileM.loadMap("sawit_land");
    }

    public int getMapTileNum(int col, int row) {
        return this.tileM.getMapTileNum(col, row);
    }

    public boolean getTileCollision(int i) {
        return this.tileM.getTileCollision(i);
    }

    // COLLISION CHCEKER

    public void checkTile(Entity entity) {
        this.cChecker.checkTile(entity);
    }

    public void startGameThread() {
        gamThread = new Thread(this);
        gamThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long timer = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while( gamThread!=null ) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // DEBUG
        long drawStart = 0;

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);

        // DEBUG
        if(this.keyH.getShowDebugText() == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + this.player.getWorldX(), x, y); y+=lineHeight;
            g2.drawString("WorldY: " + this.player.getWorldY(), x, y); y+=lineHeight;
            g2.drawString("Col: " + (this.player.getWorldX() + this.player.getSolidAreaX())/tileSize, x, y); y+=lineHeight;
            g2.drawString("Row: " + (this.player.getWorldY() + this.player.getSolidAreaY())/tileSize, x, y); y+=lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);; y+=lineHeight;
        }
        g2.dispose();
    }

}
