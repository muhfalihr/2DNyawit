package com.uph_lpjk.sawit2d.controller;

import com.uph_lpjk.sawit2d.object.ObjAxe;
import com.uph_lpjk.sawit2d.object.ObjGold;

public class AssetSetter {
    
    final private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;
        this.gp.setObject(i, 
            new ObjAxe(this.gp), 
            this.gp.getTileSize()*41, 
            this.gp.getTileSize()*21);
        i++;
        this.gp.setObject(i, 
            new ObjGold(this.gp), 
            this.gp.getTileSize()*41, 
            this.gp.getTileSize()*15);
        i++;
    }
}
