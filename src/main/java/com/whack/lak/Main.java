package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    SpawnManager spawnManager;
    MouseHandler mouseHandler; // Declare MouseHandler
    ScoreTimerManager scoreTimerManager;

    public void settings() {
        fullScreen(FX2D); // or size(800, 600) if you're testing in windowed mode
    }

    public void setup() {
        background(124,252,0,100);
        
        spawnManager = new SpawnManager(this);
        spawnManager.spawnHolesAndMoles();

        scoreTimerManager = new ScoreTimerManager(this);

        mouseHandler = new MouseHandler(this, spawnManager, scoreTimerManager); // Initialize MouseHandler
    }

    public void draw() {
        background(124,252,0,100);
        spawnManager.display();
        scoreTimerManager.update();

        if (keyPressed && (key == 'r' || key == 'R')) {
            spawnManager.resetGame();
        }
    }

    public void mouseClicked() {
        mouseHandler.handleMouseClicked(); // Delegate mouse click event
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}