package com.whack.lak.demo;

import processing.core.PApplet;
import processing.core.PVector; // Import PVector for position handling

/**
 * WhackAMole Game
 * A simple Whack-a-Mole game created using the Processing library in Java.
 * Click on the moles as they appear to score points before the time runs out.
 */
public class WhackAMole extends PApplet {

    // --- Game Configuration ---
    final int NUM_HOLES = 9;        // Total number of holes
    final int GRID_COLS = 3;        // Number of columns in the grid
    final int GRID_ROWS = 3;        // Number of rows in the grid
    final int HOLE_DIAMETER = 100;  // Diameter of the holes
    final int MOLE_DIAMETER = 80;   // Diameter of the moles (slightly smaller than holes)
    final int MOLE_VISIBLE_DURATION = 75; // Frames a mole stays visible (e.g., 75 frames / 60 fps = 1.25 seconds)
    final int MIN_DELAY_BETWEEN_MOLES = 20; // Minimum frames before the next mole can appear
    final int GAME_DURATION_MS = 30000; // Total game time in milliseconds (30 seconds)

    // --- Game State Variables ---
    PVector[] holePositions;       // Array to store the center coordinates of each hole
    int currentMoleIndex = -1;     // Index of the hole where the mole is currently visible (-1 means no mole)
    int moleVisibleTimer = 0;      // Countdown timer for how long the current mole remains visible
    int nextMoleDelayTimer = 0;    // Countdown timer until the next mole is allowed to appear
    int score = 0;                 // Player's score
    int gameStartTime;             // Timestamp (in millis) when the game started
    boolean gameOver = false;      // Flag indicating if the game has ended

    // --- Colors ---
    int clrBackground = color(139, 69, 19); // Dirt brown
    int clrHole = color(101, 67, 33);       // Darker brown for holes
    int clrMole = color(210, 180, 140);     // Tan / light brown for the mole
    int clrText = color(255);               // White for text
    int clrMoleEyes = color(0);             // Black for mole's eyes

    /**
     * settings() runs once before setup() to define window characteristics.
     * Preferred way to set size in newer Processing versions.
     */
    @Override
    public void settings() {
        size(600, 700); // Set the window size (width, height)
    }

    /**
     * setup() runs once at the beginning to initialize the game environment.
     */
    @Override
    public void setup() {
        frameRate(60); // Set the desired frame rate
        textAlign(CENTER, CENTER); // Set text alignment for score and messages
        ellipseMode(CENTER); // Draw ellipses from their center point

        // Initialize hole positions based on grid and window size
        initializeHolePositions();

        // Start the game timer
        startGame();
    }

    /**
     * Calculates and stores the center positions for each hole in a grid layout.
     */
    void initializeHolePositions() {
        holePositions = new PVector[NUM_HOLES];
        int spacingX = width / (GRID_COLS + 1); // Horizontal spacing between holes
        int spacingY = (height - 150) / (GRID_ROWS + 1); // Vertical spacing (leave space at top/bottom)
        int topOffset = 120; // Offset from the top edge for UI space

        int index = 0;
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                float x = spacingX * (c + 1);
                float y = topOffset + spacingY * (r + 1);
                holePositions[index] = new PVector(x, y);
                index++;
            }
        }
    }

    /**
     * Resets game variables to start a new game.
     */
    void startGame() {
        score = 0;
        gameOver = false;
        currentMoleIndex = -1; // No mole visible initially
        moleVisibleTimer = 0;
        nextMoleDelayTimer = MIN_DELAY_BETWEEN_MOLES * 2; // Initial delay before first mole
        gameStartTime = millis(); // Record the start time
    }

    /**
     * draw() runs repeatedly (based on frameRate) and handles game logic and rendering.
     */
    @Override
    public void draw() {
        background(clrBackground); // Clear the background each frame

        if (gameOver) {
            displayGameOverScreen();
        } else {
            // --- Game is Active ---
            updateGameTimers();
            handleMoleLogic();
            drawHoles();
            drawMole(); // Draw the mole if it's visible
            drawUI();   // Draw score and remaining time
        }
    }

    /**
     * Updates game timers and checks if the game duration has expired.
     */
    void updateGameTimers() {
        // Check if the game time limit has been reached
        if (millis() - gameStartTime >= GAME_DURATION_MS) {
            gameOver = true;
        }

        // Countdown the timer for the next mole appearance
        if (nextMoleDelayTimer > 0) {
            nextMoleDelayTimer--;
        }

        // Countdown the timer for the currently visible mole
        if (currentMoleIndex != -1 && moleVisibleTimer > 0) {
            moleVisibleTimer--;
            if (moleVisibleTimer <= 0) {
                // Mole timed out (escaped)
                currentMoleIndex = -1; // Hide the mole
                nextMoleDelayTimer = MIN_DELAY_BETWEEN_MOLES; // Set delay before next one
            }
        }
    }

    /**
     * Manages when and where moles appear.
     */
    void handleMoleLogic() {
        // If no mole is currently visible AND the delay timer is up
        if (currentMoleIndex == -1 && nextMoleDelayTimer <= 0) {
            // Time to show a new mole in a random hole
            currentMoleIndex = (int) random(NUM_HOLES); // Pick a random hole index
            moleVisibleTimer = MOLE_VISIBLE_DURATION;   // Reset the mole's visible timer
        }
    }

    /**
     * Draws the holes on the screen.
     */
    void drawHoles() {
        fill(clrHole);
        noStroke(); // No outline for the holes
        for (PVector pos : holePositions) {
            ellipse(pos.x, pos.y, HOLE_DIAMETER, HOLE_DIAMETER);
        }
    }

    /**
     * Draws the mole if it is currently visible.
     */
    void drawMole() {
        if (currentMoleIndex != -1) {
            PVector molePos = holePositions[currentMoleIndex];

            // Draw the mole body
            fill(clrMole);
            noStroke();
            ellipse(molePos.x, molePos.y, MOLE_DIAMETER, MOLE_DIAMETER);

            // Draw simple eyes
            fill(clrMoleEyes);
            float eyeOffsetX = MOLE_DIAMETER * 0.2f;
            float eyeOffsetY = MOLE_DIAMETER * 0.1f;
            float eyeSize = MOLE_DIAMETER * 0.15f;
            ellipse(molePos.x - eyeOffsetX, molePos.y - eyeOffsetY, eyeSize, eyeSize); // Left eye
            ellipse(molePos.x + eyeOffsetX, molePos.y - eyeOffsetY, eyeSize, eyeSize); // Right eye
        }
    }

    /**
     * Draws the User Interface elements (Score and Time).
     */
    void drawUI() {
        fill(clrText);
        textSize(32);

        // Display Score
        textAlign(LEFT, TOP);
        text("Score: " + score, 20, 20);

        // Display Time Remaining
        int elapsedTime = millis() - gameStartTime;
        int timeRemainingSeconds = max(0, (GAME_DURATION_MS - elapsedTime) / 1000); // Calculate remaining seconds
        textAlign(RIGHT, TOP);
        text("Time: " + timeRemainingSeconds, width - 20, 20);
    }

    /**
     * Displays the game over message and final score.
     */
    void displayGameOverScreen() {
        fill(clrText);
        textSize(64);
        textAlign(CENTER, CENTER);
        text("Game Over!", width / 2f, height / 2f - 40); // Use float division for centering

        textSize(32);
        text("Final Score: " + score, width / 2f, height / 2f + 20);
        text("Click to Restart", width / 2f, height / 2f + 70);
    }

    /**
     * mousePressed() is called automatically by Processing whenever a mouse button is pressed.
     */
    @Override
    public void mousePressed() {
        if (gameOver) {
            // If the game is over, clicking restarts the game
            startGame();
        } else {
            // --- Game is active, check for mole hit ---
            if (currentMoleIndex != -1) {
                PVector molePos = holePositions[currentMoleIndex];
                // Calculate distance between mouse click and mole center
                float distance = dist(mouseX, mouseY, molePos.x, molePos.y);

                // Check if the click was within the mole's radius
                if (distance < MOLE_DIAMETER / 2.0f) {
                    // --- Hit! ---
                    score++;                  // Increase score
                    currentMoleIndex = -1;    // Hide the mole immediately
                    moleVisibleTimer = 0;     // Reset visible timer
                    nextMoleDelayTimer = MIN_DELAY_BETWEEN_MOLES / 2; // Slightly shorter delay after a hit
                    // Optional: Add a sound effect or visual cue for hitting the mole here
                }
            }
        }
    }

    /**
     * The main method required to run this class as a PApplet.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // If running from an environment where this class is in a package (e.g., com.whack.lak),
        // use the fully qualified name. Otherwise (e.g., directly in Processing IDE
        // where the sketch name is WhackAMole.pde), use the simple name "WhackAMole".
        // PApplet.main("WhackAMole"); // Use this if NOT in a package (standard Processing IDE way)
        PApplet.main("com.whack.lak.demo.WhackAMole"); // Use this based on the error stack trace provided
    }
}
