package com.uph_lpjk.sawit2d.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.uph_lpjk.sawit2d.entity.Entity;
import com.uph_lpjk.sawit2d.object.ObjGold;


public class UserInterface {
    
    final private GamePanel gp;
    
    protected BufferedImage gold;
    
    private Graphics2D g2;
    private Font maruMonica, purisaBold;
    
    private boolean messageOn = false;
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<Integer> messageCounter = new ArrayList<>();

    private boolean gameFinished = false;
    private String currentDialogue = "";
    private int commandNum = 0;
    private int titleScreenState = 0;
    private int slotCol = 0;
    private int slotRow = 0;
    private int subState = 0;
    
    public UserInterface(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/Purisa Bold.ttf");
            this.purisaBold = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity objGold = new ObjGold(gp);
        this.gold = objGold.getImage();
    }

    public void addMessage(String text) {
        this.messages.add(text);
        this.messageCounter.add(0);
    }

    public void drawMessage() {
        int messageX = this.gp.getTileSize();
        int messageY = this.gp.getTileSize() * 4;

        this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < this.messages.size(); i++) {
            if(this.messages.get(i) != null) {
                this.g2.setColor(Color.black);
                this.g2.drawString(messages.get(i), messageX + 2, messageY + 2);

                this.g2.setColor(Color.white);
                this.g2.drawString(messages.get(i), messageX, messageY);

                int counter = this.messageCounter.get(i) + 1;
                this.messageCounter.set(i, counter);
                messageY += 50;

                if(this.messageCounter.get(i) > 180) {
                    this.messages.remove(i);
                    this.messageCounter.remove(i);
                    i--; // Perbaiki indeks agar tidak melompati elemen berikutnya
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // SET FONT
        this.g2.setFont(this.maruMonica);
        // this.g2.setFont(purisaBold);

        this.g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.g2.setColor(Color.white);

        switch (this.gp.getGameState()) {
            case TITLE: break;
            case PLAY: 
                drawPlayerGold();
                drawMessage();
                break;
            case PAUSE: drawPauseScreen(); break;
        }
    }

    private void drawPlayerGold() {
        int imageX = (this.gp.getTileSize() / 2);
        int imageY = (this.gp.getTileSize() / 2);
        
        int imageWidth = this.gp.getTileSize();
        int imageHeight = this.gp.getTileSize();

        this.g2.drawImage(gold, imageX, imageY, imageWidth, imageHeight, null);
        
        int textX = imageX + imageWidth + 2;
        int textY = imageY + (imageHeight / 2) + 15;
        String text = "$" + this.gp.getPlayerGold();

        this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 40F));
        this.g2.setColor(Color.black);
        this.g2.drawString(text, textX+2, textY+2);
        this.g2.setColor(Color.white);
        this.g2.drawString(text, textX, textY);
    }

    private void drawPauseScreen() {
        this.g2.setFont(this.g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = this.gp.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    public int getItemIndexOnSlot() {
        int itemIndex = this.slotCol + (this.slotRow * 5);
        return itemIndex;
    }

    public int getXforCenteredText(String text) {
        int length = (int)this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
        int x = this.gp.getScreenWidth() / 2 - length / 2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int)this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
