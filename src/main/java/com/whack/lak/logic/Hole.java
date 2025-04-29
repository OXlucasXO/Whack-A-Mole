package com.whack.lak.logic;

import com.whack.lak.ui.Config;
import processing.core.PApplet;
import processing.core.PImage;

public class Hole {
    private PApplet p;
    private float x;
    private float y;
    private float diameter = 200;
    private boolean clicked = false;
    private int clickTimer = 0;
    private int clickDuration = 10; // Number of frames to show the hit/miss sprite

    private PImage holeImage;
    private PImage holeOverlayImage;
    private PImage holeHitImage; // Declare hit image variable
    private PImage holeMissImage; // Declare miss image variable
    private int clickResult = 0; // 0: no click, 1: hit, 2: miss


    public Hole(PApplet _p, PImage _holeImage, float _x, float _y) {
        this.p = _p;
        this.x = _x;
        this.y = _y;
        this.holeImage = _holeImage;
        Config config = new Config(p);
        this.holeOverlayImage = config.getHoleOverlayAsset();
        this.holeHitImage = config.getHoleHitAsset(); // Load hit image
        this.holeMissImage = config.getHoleMissAsset(); // Load miss image
    }

    public void display() {
        // Draw the hole image
        if (holeImage != null) {
            p.imageMode(PApplet.CENTER);
            p.image(holeImage, x, y, diameter, diameter);
        } else {
            p.noStroke();
            p.fill(53, 59, 60);
            p.ellipse(x, y, diameter, diameter);
        }

        // Draw hit or miss sprite if clicked
        if (clickTimer > 0) {
            PImage resultImage = null;
            if (clickResult == 1 && holeHitImage != null) {
                resultImage = holeHitImage;
            } else if (clickResult == 2 && holeMissImage != null) {
                resultImage = holeMissImage;
            }

            if (resultImage != null) {
                p.imageMode(PApplet.CENTER);
                // No tint for hit/miss sprites for now, can add if needed
                p.image(resultImage, x, y, diameter, diameter);
            }
            clickTimer--;
        } else {
            clicked = false;
            clickResult = 0; // Reset click result
        }

        // Draw hole overlay image if mouse is over and not clicked
        if (isMouseOver() && !clicked) { // Only show overlay if not currently displaying hit/miss
            if (holeOverlayImage != null) {
                p.imageMode(PApplet.CENTER);
                p.tint(255, 50); // Set tint for 50 transparency
                p.image(holeOverlayImage, x, y, diameter, diameter);
                p.noTint();
            } else {
                p.noStroke();
                p.fill(222, 185, 133, 60);
                p.ellipse(x, y, diameter, diameter);
            }
        }
    }

    public boolean isMouseOver() {
        return PApplet.dist(p.mouseX, p.mouseY, x, y) < diameter / 2;
    }

    public void setClicked(int result) { // Modified to accept click result
        if (!clicked) {
            this.clicked = true;
            this.clickTimer = this.clickDuration;
            this.clickResult = result; // Set the click result (1 for hit, 2 for miss)
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}