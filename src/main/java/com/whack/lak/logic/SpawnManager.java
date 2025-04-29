package com.whack.lak.logic;

/** 
 * * Main class for the Whack-a-Mole game.
 *  
 *   @author "Lakshya Timsina Chhetri"
 *   @version "3.2.0"
 *   @since 2025-03-30
*/

import java.util.ArrayList;

import com.whack.lak.ui.Config;

import processing.core.PApplet;
import processing.core.PImage;

public class SpawnManager {

    private final PApplet p;
    private final ArrayList<Hole> holes = new ArrayList<>();
    public final ArrayList<Mole> moles = new ArrayList<>();

    // Add reference to ScoreTimerManager
    private final ScoreTimerManager scoreTimerManager; // <<< ADDED

    public int currentVisibleMoleIndex = -1;
    public int moleVisibleTimer = 0;
    public int nextMoleDelayTimer = 60;
    // public int score = 0; // This is redundant, score is handled by ScoreTimerManager

    private final int MOLE_VISIBLE_DURATION = 35;
    public final int MIN_DELAY_BETWEEN_MOLES = 20;
    private final int MAX_HOLES = 9;
    private final float MIN_HOLE_DISTANCE = 210;

    private final PImage moleImage;
    private final PImage holeImage;

    private final Config config;

    // Update constructor to accept ScoreTimerManager
    public SpawnManager(PApplet _parent, ScoreTimerManager _scoreTimerManager) { // <<< MODIFIED
        this.p = _parent;
        this.scoreTimerManager = _scoreTimerManager; // <<< ADDED: Store the reference
        this.config = new Config(p);
        this.moleImage = config.getMoleAsset();
        this.holeImage = config.getHoleAsset();
    }

    public void spawnHolesAndMoles() {
        // Clear lists first in case this is called during reset
        holes.clear();
        moles.clear();
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
                // Ensure Hole constructor receives PApplet if needed for isMouseOver
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
                // Mole timed out without being hit
                scoreTimerManager.subtractTime(); // <<< ADDED: Subtract time penalty
                currentVisibleMoleIndex = -1;
                nextMoleDelayTimer = (int) p.random(MIN_DELAY_BETWEEN_MOLES, MIN_DELAY_BETWEEN_MOLES * 3);
            }

        } else if (!holes.isEmpty()) { // If no mole is visible and delay is over
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
       
        spawnHolesAndMoles(); // Clears and respawns holes and moles
        currentVisibleMoleIndex = -1;
        moleVisibleTimer = 0; // Reset timer
        nextMoleDelayTimer = 60; // Reset delay
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    
}