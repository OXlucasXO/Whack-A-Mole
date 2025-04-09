package com.whack.lak;

import processing.core.PApplet;
import processing.core.PImage;

public class Config {
    private final String moleImage = "assets/img/mole.png" ;
    private final String holeImage = "assets/img/mole.png" ;
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

}
