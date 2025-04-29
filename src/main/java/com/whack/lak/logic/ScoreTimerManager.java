package com.whack.lak.logic;

import com.whack.lak.ui.Config; // Import Config class
import processing.core.PApplet;
import processing.core.PFont; // Import PFont
import processing.core.PImage;

public class ScoreTimerManager {
    int score = 0;
    int startTime;
    int timeLimit = 30000; // 30 seconds initial limit
    PApplet p;
    final int TIME_ADD = 500; // 0.5 seconds
    final int TIME_SUB = 1000; // 1 second

    private PFont scoreFont; // Declare PFont variable
    private Config config; // Declare Config variable

    public ScoreTimerManager(PApplet _PApplet) {
        this.p = _PApplet;
        this.startTime = p.millis(); // Record the start time
        this.config = new Config(p); // Initialize Config
        this.scoreFont = config.getScoreFont(); // Load the font
    }

    public void update() {
        // Timer logic
        int elapsedTime = p.millis() - startTime;
        // Ensure displayed time doesn't go below 0
        double timeLeft = Math.max(0, (timeLimit - elapsedTime) / 1000.0); // Use 1000.0 for double division

        p.fill(255); // Set text color to white
        p.textFont(scoreFont); // Set the loaded font
        p.textSize(32);

        p.text("Time Left: " + String.format("%.1f", timeLeft) + "s", 20, 40); // Format to 1 decimal place
        p.text("Score: " + score, 20, 80);


        if (elapsedTime >= timeLimit) { // Use >= for clearer end condition
            // Ensure we don't accidentally add time *after* the game should have ended
            if (p.millis() - startTime >= timeLimit) { // Double-check after potential adjustments
                 displayGameOver();
                 p.noLoop(); // stop draw() from running
             }
        }
    }

    private void displayGameOver() {
        p.textFont(scoreFont); // Set the font for game over text
        p.textSize(172);
        p.fill(255, 100, 100); // Set text color to red
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


    public void addTime() {
        // Check if game is already over to prevent adding time post-game
        if (p.millis() - startTime < timeLimit) {
           startTime += TIME_ADD; // Making start time earlier adds effective time
        }
    }


    public void subtractTime() {

       if (p.millis() - startTime < timeLimit) {
           startTime -= TIME_SUB; // Making start time later subtracts effective time
       }

    }
}