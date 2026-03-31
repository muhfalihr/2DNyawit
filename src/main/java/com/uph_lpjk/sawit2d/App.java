package com.uph_lpjk.sawit2d;

import javax.swing.JFrame;

import com.uph_lpjk.sawit2d.controller.GamePanel;

public class App 
{
    public static void main( String[] args ) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Nyawit");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
