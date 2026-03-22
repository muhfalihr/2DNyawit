package com.uph_lpjk.sawit2d.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    // DEBUG
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            // titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState) {
            // playState(code);
        }
        // PUASE STATE
        else if(gp.gameState == gp.pauseState) {
            // pauseState(code);
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            // dialogueState(code);
        }
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
            // chatacterState(code);
        }
    }

    public void titleState(int code) {
        // 
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        // if(code == KeyEvent.VK_F) {
        //     shotKeyPressed = true;
        // }

        // DEBUG
        if(code == KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }
        if(code==KeyEvent.VK_R) {
            gp.tileM.loadMap();
        }
    }

    public void pauseState(int code) {
        if(code==KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if(code==KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
}
