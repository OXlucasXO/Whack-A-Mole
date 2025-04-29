package com.whack.lak;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Config {
    private final String moleImage = "assets/img/mole.png" ;
    private final String holeImage = "assets/img/hole.png" ;
    private final String bgImage = "assets/img/bg.png" ;
    private final String scoreFont = "assets/font/game.TTF";
    private PApplet p;

    public Config(PApplet _p) {
        this.p = _p;
    }

    public PImage getMoleAsset() {
        return p.loadImage(moleImage);
    }

    public PImage getHoleAsset() {
        return p.loadImage(moleImage);
    }
    public PFont getScoreFont() {
        return p.loadFont(scoreFont);
    }
}
