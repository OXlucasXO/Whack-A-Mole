package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    Hole hole;

    public void settings() {
        fullScreen();
        size(480, 360, FX2D); // Set window size
    }

    public void setup() {
        background(0); // Black background
        
        hole = new Hole(this);
    }

    public void draw() {
        hole.draw();
    }



    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}
