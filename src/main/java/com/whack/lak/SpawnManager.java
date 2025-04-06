package com.whack.lak;

import java.util.ArrayList;
import processing.core.PApplet;

public class SpawnManager {

    private PApplet p;
    private ArrayList<Hole> holes;
    private ArrayList<Mole> moles; // if you're also managing moles here

    public SpawnManager(PApplet _parent) {
        this.p = _parent;
        holes = new ArrayList<>();
        moles = new ArrayList<>();
        spawnHolesAndMoles();
    }

    private void spawnHolesAndMoles() {
        int maxHoles = 12;
        float minDistance = 220; // Adjust as needed

        while (holes.size() < maxHoles) {
            float x = p.random(200, p.width - 200);
            float y = p.random(200, p.height - 200);
            boolean tooClose = false;

            // Check for overlapping holes
            for (Hole existingHole : holes) {
                if (PApplet.dist(x, y, existingHole.getX(), existingHole.getY()) < minDistance) {
                    tooClose = true;
                    break;
                }
            }

            if (!tooClose) {
                holes.add(new Hole(p, x, y));
                moles.add(new Mole(p, x, y)); // if you're using moles separately
            }
        }
    }

    public void display() {
        for (Hole h : holes) {
            h.display();
        }
        // If you need to display moles separately, do that here too.
        for (Mole m : moles) {
            m.display();
        }
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }
}
