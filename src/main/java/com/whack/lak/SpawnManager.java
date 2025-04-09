package com.whack.lak;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class SpawnManager {

    private final PApplet p;
    private final ArrayList<Hole> holes = new ArrayList<>();
    public final ArrayList<Mole> moles = new ArrayList<>();

    public int currentVisibleMoleIndex = -1;
    public int moleVisibleTimer = 0;
    public int nextMoleDelayTimer = 60;
    public int score = 0;

    private final int MOLE_VISIBLE_DURATION = 75;
    public final int MIN_DELAY_BETWEEN_MOLES = 20;
    private final int MAX_HOLES = 6;
    private final float MIN_HOLE_DISTANCE = 210;

    private final PImage moleImage;
    private final PImage holeImage;

    private final Config config;

    public SpawnManager(PApplet _parent) {
        this.p = _parent;
        this.config = new Config(p);
        this.moleImage = config.getMoleAsset();
        this.holeImage = config.getHoleAsset();
    }

    public void spawnHolesAndMoles() {
        // holes.clear();
        // moles.clear();

        while (holes.size() < MAX_HOLES) {
            float x = p.random(200, p.width - 200);
            float y = p.random(200, p.height - 200);

            boolean tooClose = false;
            for (Hole hole : holes) {
                if (PApplet.dist(x, y, hole.getX(), hole.getY()) < MIN_HOLE_DISTANCE) {
                    tooClose = true;
                    break;
                }
            }

            if (!tooClose) {
                holes.add(new Hole(p, holeImage, x, y));
                moles.add(new Mole(p, moleImage, x, y));
            }
        }
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

        } else if (!holes.isEmpty()) {
            currentVisibleMoleIndex = (int) p.random(holes.size());
            moleVisibleTimer = MOLE_VISIBLE_DURATION;
        }

        if (currentVisibleMoleIndex != -1) {
            moles.get(currentVisibleMoleIndex).display();
        }
    }

    public void display() {
        for (Hole hole : holes) {
            hole.display();
        }
        this.updateMoleLogic();

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
