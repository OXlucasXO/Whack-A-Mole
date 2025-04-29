package com.whack.lak.ui;

/**
 * * Main class for the Whack-a-Mole game.
 *
 * @author "htun thiha myo"
 * @version "3.2.0"
 * @since 2025-03-30
 */
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Config {
    private final String moleImage = "assets/img/mole.png" ;
    private final String holeImage = "assets/img/hole.png" ;
    private final String holeHitImage = "assets/img/holeHit.png" ; // Hit image path
    private final String holeMissImage = "assets/img/holeMiss.png" ; // Miss image path
    private final String holeOverlayImage = "assets/img/holeOverlay.png" ; // Hole overlay image path
    private final String bgImage = "assets/img/bg.png" ;
    private final String scoreFont = "assets/font/font.TTF"; // Corrected font file name
    private PApplet p;

    public Config(PApplet _p) {
        this.p = _p;
    }

    public PImage getMoleAsset() {
        return p.loadImage(moleImage);
    }

    public PImage getHoleAsset() {
        return p.loadImage(holeImage);
    }

    public PImage getBgAsset() {
        return p.loadImage(bgImage);
    }

    public PImage getHoleOverlayAsset() {
        return p.loadImage(holeOverlayImage);
    }

    public PImage getHoleHitAsset() {
        return p.loadImage(holeHitImage); // Method to load hole hit image
    }

    public PImage getHoleMissAsset() {
        return p.loadImage(holeMissImage); // Method to load hole miss image
    }

    public PFont getScoreFont() {
        // Use createFont() for .TTF files
        return p.createFont(scoreFont, 32); // Specify the font size here
    }
}