package com.whack.lak.logic;

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

            if (h.isMouseOver()) { // Check if mouse is over this hole
                // h.setClicked(true); // Remove this line

                if (i == spawnManager.currentVisibleMoleIndex) {
                    // Successful hit!
                    h.setClicked(1); // Pass 1 for a hit
                    spawnManager.currentVisibleMoleIndex = -1; // Hide mole immediately
                    spawnManager.moleVisibleTimer = 0; // Reset visibility timer
                    // Make next mole appear faster
                    spawnManager.nextMoleDelayTimer = spawnManager.MIN_DELAY_BETWEEN_MOLES / 2;

                    scoreTimerManager.updateScore(); // Increment score
                    scoreTimerManager.addTime();   // Add time for successful hit

                } else {
                    // Miss
                    h.setClicked(2); // Pass 2 for a miss
                    scoreTimerManager.subtractTime(); // Subtract time penalty
                }
                break;
            }
        }
    }
}