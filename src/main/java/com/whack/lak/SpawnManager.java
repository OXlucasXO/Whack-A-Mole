package com.whack.lak;

import java.util.ArrayList;
import processing.core.PApplet;

public class SpawnManager {

    private PApplet p;
    private ArrayList<Hole> holes;
    public ArrayList<Mole> moles;
    public int currentVisibleMoleIndex = -1; // Made public
    public int moleVisibleTimer = 0; // Made public
    public int nextMoleDelayTimer = 60; // Made public
    private final int MOLE_VISIBLE_DURATION = 75;
    public final int MIN_DELAY_BETWEEN_MOLES = 20;
    public int score = 0; // Made public

    public SpawnManager(PApplet _parent) {
        this.p = _parent;
        holes = new ArrayList<>();
        moles = new ArrayList<>();
        spawnHolesAndMoles();
    }

    private void spawnHolesAndMoles() {
        int maxHoles = 12;
        float minDistance = 210;

        holes.clear();
        moles.clear();

        while (holes.size() < maxHoles) {
            float x = p.random(200, p.width - 200);
            float y = p.random(200, p.height - 200);
            boolean tooClose = false;

            for (Hole existingHole : holes) {
                if (PApplet.dist(x, y, existingHole.getX(), existingHole.getY()) < minDistance) {
                    tooClose = true;
                    break;
                }
            }

            if (!tooClose) {
                holes.add(new Hole(p, x, y));
                moles.add(new Mole(p, x, y));
            }
        }
        System.out.println("Successfully spawned " + maxHoles + " holes with organic placement.");
    }

    public void updateMoleLogic() {
        if (nextMoleDelayTimer > 0) {
            nextMoleDelayTimer--;
            return;
        }

        if (currentVisibleMoleIndex != -1) {
            moleVisibleTimer--;
            if (moleVisibleTimer <= 0) {
                currentVisibleMoleIndex = -1;
                nextMoleDelayTimer = (int) p.random(MIN_DELAY_BETWEEN_MOLES, MIN_DELAY_BETWEEN_MOLES * 3);
            }
        } else {
            if (holes.size() > 0) {
                currentVisibleMoleIndex = (int) p.random(holes.size());
                moleVisibleTimer = MOLE_VISIBLE_DURATION;
            }
        }

        for (int i = 0; i < moles.size(); i++) {
            if (i == currentVisibleMoleIndex) {
                moles.get(i).display();
            }
        }
    }

    public void display() {
        for (Hole h : holes) {
            h.display();
        }
    }

    public void resetGame() {
        spawnHolesAndMoles();
        currentVisibleMoleIndex = -1;
        moleVisibleTimer = 0;
        nextMoleDelayTimer = 60;
        score = 0;
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }
}