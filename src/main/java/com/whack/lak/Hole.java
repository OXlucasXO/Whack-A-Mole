package com.whack.lak;

import processing.core.PApplet;

public class Hole {
    private PApplet p;
    private float x, y;
    private boolean clicked = false; // Tracks if the hole was clicked

    public Hole(PApplet p, float x, float y) {
        this.p = p;
        this.x = x;
        this.y = y;
    }

    // Check if mouse is over this hole.
    // Adjust the detection radius as needed.
    public boolean isMouseOver() {
        return PApplet.dist(p.mouseX, p.mouseY, x, y) < 50;
    }

    // Call this to mark the hole as clicked.
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void display() {
        // Draw the base hole
        p.fill(50);
        p.ellipse(x, y, 200, 200);
        
        // If the hole has been clicked, overlay it with 100% opaque red.
        if (clicked) {
            p.fill(255, 0, 0, 255); // red, fully opaque
            p.ellipse(x, y, 200, 200);
        }
        // Else, if the mouse is hovering, overlay with 50% opacity.
        else if (isMouseOver()) {
            p.fill(255, 255, 255, 128); // white overlay with 50% opacity
            p.ellipse(x, y, 200, 200);
        }
    }

    // Optional getter for x and y if needed in SpawnManager
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
