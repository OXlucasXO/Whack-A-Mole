package com.whack.lak;
import processing.core.PApplet;

public class Hole {
    PApplet p;

    public Hole(PApplet _parent) {
        this.p = _parent;
    }    

    public void setup() {
    //    p.pixelDensity(p.displayDensity());
    //     p.smooth();
    }

    public void draw() {
        p.fill(255, 0, 0); // Red color
        p.ellipse(p.width / 2, p.height / 2, 200, 200); // Draw a circle
        p.ellipse(p.width / 4, p.height / 4, 200, 200); // Draw a circle
    }
}