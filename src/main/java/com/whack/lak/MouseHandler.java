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
            if (h.isMouseOver()) { // Call isMouseOver without parameters
                h.setClicked(true);
                if (i == spawnManager.currentVisibleMoleIndex) {
                    spawnManager.currentVisibleMoleIndex = -1;
                    spawnManager.moleVisibleTimer = 0;
                    spawnManager.nextMoleDelayTimer = spawnManager.MIN_DELAY_BETWEEN_MOLES / 2;

                    scoreTimerManager.updateScore();


                }
                break;
            }
        }
    }
}