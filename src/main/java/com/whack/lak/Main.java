package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    

    public void settings() {
        fullScreen(FX2D);
    }

    ScoreTimerManager scoreTimerManager;
    SpawnManager spawnManager;

    public void setup() {
        

       spawnManager = new SpawnManager(this);

        scoreTimerManager = new ScoreTimerManager(this);
        
    }

    public void draw() {
        if (keyPressed) {
            if (key == 'R' || key == 'r') {
            // background(0);
            // SpawnManager manager = new SpawnManager(this);
            // manager.display();
            
            }

          }
        background(255);
        scoreTimerManager.update();
        spawnManager.update();
        
    }

    public void mousePressed() {
        scoreTimerManager.detectMouse();
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }


}

// implement exit screen