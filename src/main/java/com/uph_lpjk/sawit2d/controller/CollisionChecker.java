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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < this.gp.getObjectLength(); i++) {
            if(this.gp.getObject(i) != null) {
                // Get entity's solid area position
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY());

                // Get the object's solid area position
                this.gp.setObjectSolidAreaX(i, this.gp.getObjectWorldX(i) + this.gp.getObjectSolidAreaX(i));
                this.gp.setObjectSolidAreaY(i, this.gp.getObjectWorldY(i) + this.gp.getObjectSolidAreaY(i));

                switch (entity.getDirection()) {
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                    break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                    break;
                }

                if(entity.getSolidArea().intersects(this.gp.getObjectSolidArea(i))) {
                    if(this.gp.getObject(i) != entity) {
                        if(this.gp.getObject(i).isCollision() == true) {
                            entity.setCollisionOn(true);
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                }
                
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                this.gp.setObjectSolidAreaX(i, this.gp.getObjectSolidAreaDefaultX(i));
                this.gp.setObjectSolidAreaY(i, this.gp.getObjectSolidAreaDefaultY(i));
            }
        }
        return index;
    }
}
