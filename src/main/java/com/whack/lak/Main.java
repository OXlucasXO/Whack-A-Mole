package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    // ✅ Declare it here so all methods can use it
    SpawnManager spawnManager;

    public void settings() {
        fullScreen(FX2D); // or size(800, 600) if you're testing in windowed mode
    }

    public void setup() {
        background(0);
        spawnManager = new SpawnManager(this); // initialize the spawn manager
    }

    public void draw() {
        background(0);
        spawnManager.display(); // draw holes and moles

        // Optional: press 'r' to regenerate new random holes and moles
        if (keyPressed && (key == 'r' || key == 'R')) {
            spawnManager = new SpawnManager(this);
        }
    }

        // This method is called automatically when the mouse is clicked.
        public void mouseClicked() {
            // Loop through each hole in the SpawnManager.
            // Make sure your SpawnManager class has a getHoles() method returning the ArrayList<Hole>.
            for (Hole h : spawnManager.getHoles()) {
                if (h.isMouseOver()) {
                    h.setClicked(true);
                }
            }
        }
    

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}
