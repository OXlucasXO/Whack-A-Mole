package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    

    public void settings() {
        fullScreen(FX2D);
    }

    public void setup() {
        background(0);

        SpawnManager manager = new SpawnManager(this);
        manager.display();
    }

    public void draw() {
        if (keyPressed) {
            if (key == 'R' || key == 'r') {
            background(0);
            SpawnManager manager = new SpawnManager(this);
            manager.display();
            
            }
          }
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }


}
