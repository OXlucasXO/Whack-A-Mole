package com.whack.lak;

import processing.core.PApplet;

public class Hole {
    private PApplet p;
    private float x, y;

    public Hole(PApplet p, float x, float y) {
        this.p = p;
        this.x = x;
        this.y = y;
    }

    public void display() {
        // Draw the hole
        p.fill(50);
        p.ellipse(x, y, 100, 100);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
