package com.uph_lpjk.sawit2d.controller;

import com.uph_lpjk.sawit2d.entity.Entity;

public class CollisionChecker {

    final private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX();
        int entityRightWorldX = entity.getWorldX() + entity.getSolidAreaX() + entity.getSolidAreaWidth();

        int entityTopWorldY = entity.getWorldY() + entity.getSolidAreaY();
        int entityBottmWorldY = entity.getWorldY() + entity.getSolidAreaY() + entity.getSolidAreaHeight();

        int entityLeftCol = entityLeftWorldX/this.gp.getTileSize();
        int entityRightCol = entityRightWorldX/this.gp.getTileSize();
        int entityTopRow = entityTopWorldY/this.gp.getTileSize();
        int entityBottomRow = entityBottmWorldY/this.gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up": 
                entityTopRow = (entityTopWorldY - entity.getSpeed())/this.gp.getTileSize();
                if (entityTopRow < 0) {
                    entity.setCollisionOn(true);
                    break;
                }
                tileNum1 = this.gp.getMapTileNum(entityLeftCol, entityTopRow);      
                tileNum2 = this.gp.getMapTileNum(entityRightCol, entityTopRow);
                if(this.gp.getTileCollision(tileNum1) == true || this.gp.getTileCollision(tileNum2) == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottmWorldY + entity.getSpeed())/this.gp.getTileSize();
                if (entityBottomRow >= gp.getMaxWorldRow()) {
                    entity.setCollisionOn(true);
                    break;
                }
                tileNum1 = this.gp.getMapTileNum(entityLeftCol, entityBottomRow);      
                tileNum2 = this.gp.getMapTileNum(entityRightCol, entityBottomRow);
                if(this.gp.getTileCollision(tileNum1) == true || this.gp.getTileCollision(tileNum2) == true) {
                    entity.setCollisionOn(true);
                }          
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/this.gp.getTileSize();
                if (entityLeftCol < 0) {
                    entity.setCollisionOn(true);
                    break;
                }
                tileNum1 = this.gp.getMapTileNum(entityLeftCol, entityTopRow);      
                tileNum2 = this.gp.getMapTileNum(entityLeftCol, entityBottomRow);
                if(this.gp.getTileCollision(tileNum1) == true || this.gp.getTileCollision(tileNum2) == true) {
                    entity.setCollisionOn(true);
                }                
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/this.gp.getTileSize();
                if (entityRightCol >= gp.getMaxWorldCol()) {
                    entity.setCollisionOn(true);
                    break;
                }
                tileNum1 = this.gp.getMapTileNum(entityRightCol, entityTopRow);      
                tileNum2 = this.gp.getMapTileNum(entityRightCol, entityBottomRow);
                if(this.gp.getTileCollision(tileNum1) == true || this.gp.getTileCollision(tileNum2) == true) {
                    entity.setCollisionOn(true);
                }                
                break;
        }
    }
}
