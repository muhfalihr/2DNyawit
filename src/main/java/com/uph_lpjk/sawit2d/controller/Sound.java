package com.uph_lpjk.sawit2d.controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    
    private Clip clip;
    private Clip[] clips = new Clip[30];
    private FloatControl fc;
    private int volumeScale;
    final private URL soundUrl[] = new URL[30];

    public Sound() {
        this.soundUrl[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
        this.soundUrl[1] = getClass().getResource("/sounds/coin.wav");
        this.soundUrl[2] = getClass().getResource("/sounds/powerup.wav");
        this.soundUrl[3] = getClass().getResource("/sounds/unlock.wav");
        this.soundUrl[4] = getClass().getResource("/sounds/fanfare.wav");
        this.soundUrl[5] = getClass().getResource("/sounds/hitmonster.wav");
        this.soundUrl[6] = getClass().getResource("/sounds/receivedamage.wav");
        this.soundUrl[7] = getClass().getResource("/sounds/swingwhooshweapon1.wav");
        this.soundUrl[8] = getClass().getResource("/sounds/levelup.wav");
        this.soundUrl[9] = getClass().getResource("/sounds/cursor.wav");
        this.soundUrl[10] = getClass().getResource("/sounds/burning.wav");
        this.soundUrl[11] = getClass().getResource("/sounds/cuttree.wav");

        // PRE-LOAD ALL CLIPS
        for(int i = 0; i < soundUrl.length; i++) {
            if(soundUrl[i] != null) {
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundUrl[i]);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(ais);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setFile(int i) {
        this.clip = clips[i];
        if (this.clip != null) {
            try {
                this.fc = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void play() {
        if (this.clip != null) {
            this.clip.setFramePosition(0);
            this.clip.start();
        }
    }

    public void loop() {
        if (this.clip != null) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (this.clip != null) {
            this.clip.stop();
        }
    }
}
