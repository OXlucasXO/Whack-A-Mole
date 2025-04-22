package com.whack.lak;

import processing.core.PApplet;

public class MouseHandler {
    private SpawnManager spawnManager;
    private ScoreTimerManager scoreTimerManager;

    public MouseHandler(PApplet _p, SpawnManager _spawnManager, ScoreTimerManager _scoreTimerManager) {
        this.spawnManager = _spawnManager;
        this.scoreTimerManager = _scoreTimerManager;
    }

    public void handleMouseClicked() {
        for (int i = 0; i < spawnManager.getHoles().size(); i++) {
            Hole h = spawnManager.getHoles().get(i);
            // Make sure Hole has the necessary PApplet reference for mouseX, mouseY checks
            // Or pass mouseX, mouseY from Main if Hole.isMouseOver needs them.
            // Assuming Hole.isMouseOver() correctly uses p.mouseX, p.mouseY internally.
            if (h.isMouseOver()) { // Check if mouse is over this hole
                h.setClicked(true); // Mark hole clicked (for animation maybe)

                if (i == spawnManager.currentVisibleMoleIndex) {
                    // Successful hit!
                    spawnManager.currentVisibleMoleIndex = -1; // Hide mole immediately
                    spawnManager.moleVisibleTimer = 0; // Reset visibility timer
                    // Make next mole appear faster
                    spawnManager.nextMoleDelayTimer = spawnManager.MIN_DELAY_BETWEEN_MOLES / 2;

                    scoreTimerManager.updateScore(); // Increment score
                    scoreTimerManager.addTime();   // <<< ADDED: Add time for successful hit

                } else {
                    // Optional: Penalize clicking an empty hole?
                    // scoreTimerManager.subtractTime(); // Example penalty
                }
                break; // Exit loop once a click is handled
            }
        }
    }
}

// --- IMPORTANT ASSUMPTION ---
// The Hole class needs a method like this (or similar logic):
/*
class Hole {
    PApplet p;
    float x, y, radius; // Or width/height if rectangular collision

    // Constructor ...

    boolean isMouseOver() {
        // Example for circular holes
        float d = PApplet.dist(p.mouseX, p.mouseY, x, y);
        return d < radius;
        // Example for rectangular holes (assuming x,y is top-left)
        // return p.mouseX > x && p.mouseX < x + width && p.mouseY > y && p.mouseY < y + height;
        // Example for centered rectangular holes (assuming x,y is center)
        // float halfW = width / 2;
        // float halfH = height / 2;
        // return p.mouseX > x - halfW && p.mouseX < x + halfW && p.mouseY > y - halfH && p.mouseY < y + halfH;
    }

    void setClicked(boolean clicked) {
        // Logic for click feedback/animation
    }

    // ... other Hole methods (display, getX, getY)
}
*/