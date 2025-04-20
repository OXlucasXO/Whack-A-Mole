package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    SpawnManager spawnManager;
    MouseHandler mouseHandler; // Declare MouseHandler

    public void settings() {
        fullScreen(FX2D);
    }

    ScoreTimerManager scoreTimerManager;
    SpawnManager spawnManager;

    public void setup() {
        

       spawnManager = new SpawnManager(this);

        circle(200, 200, 100);
        spawnManager.display();

        mouseHandler = new MouseHandler(this, spawnManager); // Initialize MouseHandler
    }

    public void draw() {
        background(124,252,0,100);
        spawnManager.display();

        if (keyPressed && (key == 'r' || key == 'R')) {
            background(124,252,0,100);
            spawnManager.resetGame();
        }
    }

    public void mousePressed() {
        scoreTimerManager.detectMouse();
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }


}

// implement exit screen