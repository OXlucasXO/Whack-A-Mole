package com.whack.lak;

import processing.core.PApplet;

public class ScoreTimerManager {

    int score = 0;
    int startTime;
    int timeLimit = 30000; // 10 seconds initial limit
    PApplet p;
    final int TIME_ADD = 500; // 0.5 seconds
    final int TIME_SUB = 1000; // 1 second
    public ScoreTimerManager(PApplet _PApplet) {
        this.p = _PApplet;
        this.startTime = p.millis(); // Record the start time
    }

    public void update() {
        // Timer logic
        int elapsedTime = p.millis() - startTime;
        // Ensure displayed time doesn't go below 0
        double timeLeft = Math.max(0, (timeLimit - elapsedTime) / 1000);

        p.fill(0);
        p.textSize(32);
        p.text("Time Left: " + timeLeft + "s", 20, 40);
        p.text("Score: " + score, 20, 80);

        // Stop the game when time runs out (elapsed time exceeds the original limit)
        // Note: Even if we add time, the game should still eventually end based on the original limit conceptually,
        // unless the player is extremely fast. The core check remains against the initial limit duration.
        // If you wanted the limit itself to change, you'd modify timeLimit instead of startTime.
        // Sticking to adjusting startTime as it directly modifies effective "time remaining".
        if (elapsedTime >= timeLimit) { // Use >= for clearer end condition
            // Ensure we don't accidentally add time *after* the game should have ended
            if (p.millis() - startTime >= timeLimit) { // Double-check after potential adjustments
                 displayGameOver();
                 p.noLoop(); // stop draw() from running
             }
        }
    }

    private void displayGameOver() {
        p.textSize(48);
        p.fill(255, 0, 0);
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.text("Time's Up!", p.width / 2, p.height / 2);
        p.textAlign(PApplet.LEFT, PApplet.BASELINE); // Reset alignment
    }


    // Method to increase the score
    public void updateScore() {
        score++;
    }

    // Method to reset score and timer
    public void ResetScore() {
        score = 0;
        startTime = p.millis(); // Reset the start time
        p.loop(); // Restart the draw loop
    }

    // --- NEW METHODS ---

    /**
     * Adds time to the timer by adjusting the start time backwards.
     */
    public void addTime() {
        // Check if game is already over to prevent adding time post-game
        if (p.millis() - startTime < timeLimit) {
           startTime += TIME_ADD; // Making start time earlier adds effective time
        }
    }

    /**
     * Subtracts time from the timer by adjusting the start time forwards.
     */
    public void subtractTime() {
       // Check if game is already over to prevent subtracting time post-game
       // and potentially causing immediate game over on the next frame if very close.
       if (p.millis() - startTime < timeLimit) {
           startTime -= TIME_SUB; // Making start time later subtracts effective time
       }
       // Optional: Immediately check if this subtraction caused game over
       // if (p.millis() - startTime >= timeLimit) {
       //     displayGameOver();
       //     p.noLoop();
       // }
    }
    // --- END NEW METHODS ---
}