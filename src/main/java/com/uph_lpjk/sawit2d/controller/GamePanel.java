package com.uph_lpjk.sawit2d.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final public int tileSize = (originalTileSize*scale);
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth = (tileSize*maxScreenCol);
    final public int screenHeight = (tileSize*maxScreenRow);

    // WORLD SETTINGS
    final public int maxWorldCol = 50;
    final public int maxWorldRow = 50;
    final public int worldWidth = tileSize*maxWorldCol;
    final public int worldHeight = tileSize*maxWorldRow;

    // FPS
    final int FPS = 60;

    Thread gameThread;

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public void setupGame() {

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
    }
}
