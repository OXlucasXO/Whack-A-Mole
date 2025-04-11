package com.whack.lak;
import processing.core.PApplet;
import processing.core.PImage;

public class Mole {
    private PImage moleImage; // Variable to store the mole image
    private PApplet p;
    private float x, y; // Position of the mole

    // Constructor
    public Mole(PApplet _p, PImage _image,float _x, float _y) {
        this.p = _p;
        this.x = _x;
        this.y = _y;
        this.moleImage = _image; // Load image from /data folder
    }

    // Method to draw the mole
    public void display() {
        if (moleImage != null) {
            p.imageMode(PApplet.CENTER); // Set image drawing mode to center
            p.image(moleImage, x, y, 80, 80); // Draw mole at correct position
        } else {
            p.fill(255, 0, 0); // Red if image not found
            p.ellipse(x, y, 80, 80);
        }
    }
}