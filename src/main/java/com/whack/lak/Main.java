package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    public void settings() {
        size(800, 600); // Set window size
    }

    public void setup() {
        background(0); // Black background
    }

    public void draw() {
        fill(255, 0, 0); // Red color
        ellipse(width / 2, height / 2, 100, 100); // Draw a circle
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}
