package com.uph_lpjk.sawit2d.object;

import com.uph_lpjk.sawit2d.controller.GamePanel;
import com.uph_lpjk.sawit2d.entity.Entity;

public class ObjAxe extends Entity {
    public ObjAxe(GamePanel gp) {
        super(gp);

        setType(type_axe);
        setName("Woodcutter's Axe");
        down1 = setup("/objects/weapons/axe", gp.getTileSize(), gp.getTileSize());
        setAttackValue(2);
        attackArea.width = 30;
        attackArea.height = 30;
        setDescription("[" + getName() + "]\nA bit rusty but still\ncan cut some trees.");
    }
}
