package com.whack.lak;

import java.util.ArrayList;

import processing.core.PApplet;

public class SpawnManager {

    PApplet p;
    Hole hole;
    Mole mole;
    ArrayList<Hole> holes;
    ArrayList<Mole> moles;

    public SpawnManager(PApplet _parent) {
        this.p = _parent;
    }

    public void display() {

        holes = new ArrayList<>();
        moles = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            float x = p.random(100, p.width - 100);
            float y = p.random(100, p.height - 100);
            
            holes.add(new Hole(p, x, y));
            moles.add(new Mole(p, x, y));
        }

        // Draw all holes
        for (Hole hole : holes) {
            hole.display();
        }

        // Draw all moles
        for (Mole mole : moles) {
            mole.display();
        }
    }

    
}
