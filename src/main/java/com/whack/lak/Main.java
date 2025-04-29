package com.whack.lak;
import com.whack.lak.logic.MouseHandler;
import com.whack.lak.logic.ScoreTimerManager;
import com.whack.lak.logic.SpawnManager;
import com.whack.lak.ui.Config;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * * Main class for the Whack-a-Mole game.
 * @author "Lakshya Timsina Chhetri"
 * @author "San ja nar"
 * @author "htun thiha myo"
 * @author "vantay VirajaySak"
 * @version "3.2.0"
 * @since 2025-03-30
 */
public class Main extends PApplet {

    SpawnManager spawnManager;
    MouseHandler mouseHandler;
    ScoreTimerManager scoreTimerManager;
    private PImage backgroundImage;
    private Config config;
    private final int topBarHeight = 100; // Define the height of the top bar


    @Override
    public void settings() {
        fullScreen(FX2D);
    }

    @Override
    public void setup() {
        // Initialize Managers
        scoreTimerManager = new ScoreTimerManager(this);
        spawnManager = new SpawnManager(this, scoreTimerManager);
        mouseHandler = new MouseHandler(this, spawnManager, scoreTimerManager);
        config = new Config(this);
        backgroundImage = config.getBgAsset();

        // Initial setup calls
        spawnManager.spawnHolesAndMoles();
    }

    @Override
    public void draw() {
        // Only draw if the loop is running
        if (looping) {
            // Draw background image - This clears the previous frame
            if (backgroundImage != null) {
                image(backgroundImage, 0, 0, width, height);
            } else {
                background(150, 132, 161, 100); // Fallback to color background
            }

            // Draw black bar at the top
            fill(0); // Set fill color to black
            noStroke(); // Remove stroke
            rect(0, 0, width, topBarHeight); // Draw the black rectangle

            spawnManager.display(); // Display holes & update/display moles
            scoreTimerManager.update(); // Update timer/score display & check game over
        }
    }

    @Override
    public void mouseClicked() {
        if (looping && mouseHandler != null) {
             mouseHandler.handleMouseClicked();
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            resetGame();
        }
    }

    private void resetGame() {
        spawnManager.resetGame();
        scoreTimerManager.ResetScore();
        draw(); // Call draw() to refresh the screen
        // Removed explicit background drawing here, relying on draw()
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.Main");
    }
}