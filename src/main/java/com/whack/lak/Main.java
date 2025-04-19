package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

<<<<<<< HEAD
    
=======
    SpawnManager spawnManager;
    MouseHandler mouseHandler; // Declare MouseHandler
>>>>>>> parent of 9b33c5d (combined ScoreTimerManager and SpawnManager)

    public void settings() {
        fullScreen(FX2D);
    }

    ScoreTimerManager scoreTimerManager;
    SpawnManager spawnManager;

    public void setup() {
        

       spawnManager = new SpawnManager(this);

<<<<<<< HEAD
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
        
=======
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
>>>>>>> parent of 9b33c5d (combined ScoreTimerManager and SpawnManager)
    }

    public void mousePressed() {
        scoreTimerManager.detectMouse();
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }


}

// implement exit screen