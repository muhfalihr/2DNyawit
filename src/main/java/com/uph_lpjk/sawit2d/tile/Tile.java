package com.uph_lpjk.sawit2d.tile;

import java.awt.image.BufferedImage;

public class Tile {
    protected BufferedImage image;
    protected boolean collision = false;

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean getCollision() {
        return this.collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
