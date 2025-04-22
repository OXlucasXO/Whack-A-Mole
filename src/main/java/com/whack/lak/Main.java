package com.whack.lak;

import processing.core.PApplet;

public class Main extends PApplet {

    SpawnManager spawnManager;
    MouseHandler mouseHandler;
    ScoreTimerManager scoreTimerManager;

    @Override
    public void settings() {
        fullScreen(PApplet.FX2D);
    }

    @Override
    public void setup() {
        // Initialize Managers
        scoreTimerManager = new ScoreTimerManager(this);
        spawnManager = new SpawnManager(this, scoreTimerManager);
        mouseHandler = new MouseHandler(this, spawnManager, scoreTimerManager);

        // Initial setup calls
        spawnManager.spawnHolesAndMoles();

        background(124, 252, 0, 100);
    }

    @Override
    public void draw() {
        // Only draw if the loop is running
        // (noLoop() might have been called by ScoreTimerManager)
        // background() should still be called even if paused to show the last frame correctly.
        background(124, 252, 0, 100); // Clear background

        spawnManager.display(); // Display holes & update/display moles
        scoreTimerManager.update(); // Update timer/score display & check game over

        // Reset logic is MOVED to keyPressed()
        // if (keyPressed && (key == 'r' || key == 'R')) {
        //    resetGame();
        // }
    }

    @Override
    public void mouseClicked() {
        // Delegate only if game is running (i.e., loop is active)
        // Check 'looping' which is a boolean field in PApplet
        if (looping && mouseHandler != null) {
             mouseHandler.handleMouseClicked();
        }
        // Optional: You could allow clicking after game over to restart too
        // else if (!looping && (mouseX > someButtonAreaX && ...)) { // Check if clicking a restart button
        //     resetGame();
        // }
    }

    // --- ADDED/MODIFIED METHOD ---
    /**
     * Processing's keyPressed() method. Called once whenever a key is pressed.
     * This works even when noLoop() has been called.
     */
    @Override
    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            resetGame();
        }
        // You could add other key handlers here if needed (e.g., pause, exit)
    }
    // --- END ADDED/MODIFIED METHOD ---


    // Encapsulate reset logic
    private void resetGame() {
        spawnManager.resetGame();
        scoreTimerManager.ResetScore(); // This MUST call p.loop() internally to restart drawing!
                                       // (Which your ScoreTimerManager.ResetScore() does)
    }


    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}