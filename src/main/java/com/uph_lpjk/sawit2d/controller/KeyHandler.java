package com.uph_lpjk.sawit2d.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    final private GamePanel gp;
    private boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    private boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean getUpPressed() {
        return this.upPressed;
    }

    public boolean getDownPressed() {
        return this.downPressed;
    }

    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    public boolean getRightPressed() {
        return this.rightPressed;
    }

    public boolean getEnterPressed() {
        return this.enterPressed;
    }

    public boolean getShowDebugText() {
        return this.showDebugText;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W) {
            this.upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            this.downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            this.leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            this.rightPressed = true;
        }
        if(code == KeyEvent.VK_T) {
            if(this.showDebugText == false) {
                this.showDebugText = true;
            } else if(this.showDebugText == true) {
                this.showDebugText = false;
            }
        }
        if(code==KeyEvent.VK_R) {
            this.gp.loadMap();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W) {
            this.upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            this.downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            this.leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            this.rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
