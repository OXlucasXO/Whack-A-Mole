package com.whack.lak;

import processing.core.PApplet;

public class Hole {
    private PApplet p;
    private float x;
    private float y;
    private float diameter = 200; // You might want to make this a parameter
    private boolean clicked = false;
    private int clickTimer = 0;
    private int clickDuration = 10; // Number of frames to show red (adjust this value)
    private int defaultColor;
    private int redColor;
    private int whiteOverlayColor; // Color for the semi-transparent white overlay

    public Hole(PApplet _p, float _x, float _y) {
        this.p = _p;
        this.x = _x;
        this.y = _y;
        this.defaultColor = p.color(101, 67, 33); // Darker brown for holes (same as before)
        this.redColor = p.color(255, 0, 0);
        this.whiteOverlayColor = p.color(255, 255, 255, 100); // White with 100 alpha (adjust for transparency)
    }

    public void display() {
        p.noStroke();
        if (clickTimer > 0) {
            p.fill(redColor);
            clickTimer--;
        } else {
            p.fill(defaultColor);
            clicked = false; // Optionally reset the clicked flag if needed
        }
        p.ellipse(x, y, diameter, diameter);

        // Draw semi-transparent white overlay if mouse is over
        if (isMouseOver()) {
            p.fill(whiteOverlayColor);
            p.ellipse(x, y, diameter, diameter);
        }
    }

    public boolean isMouseOver() {
        return PApplet.dist(p.mouseX, p.mouseY, x, y) < diameter / 2;
    }

    public void setClicked(boolean _clicked) {
        if (!clicked) {
            this.clicked = true;
            this.clickTimer = this.clickDuration; // Start the timer when clicked
        } else {
            this.clicked = false;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}