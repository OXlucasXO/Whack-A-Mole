package com.whack.lak;

<<<<<<< HEAD
import processing.core.PApplet;

public class ScoreTimerManager  {

    int score = 0;
    int startTime;
    int timeLimit = 10000; // 10 seconds
    PApplet p;

    public ScoreTimerManager(PApplet _PApplet) {
        this.p = _PApplet;
        this.startTime = p.millis(); // Record the start time

    }

    public void update() {

    
      // Timer logic
      
      int timeLeft = (timeLimit - (p.millis() - startTime)) / 1000;
      p.fill(0);
      p.textSize(32);
      p.text("Time Left: " + timeLeft + "s", 20, 40);
      p.text("Score: " + score, 20, 80);
    
      // Stop the game when time runs out
      if (p.millis() - startTime > timeLimit) {
        p.textSize(48);
        p.fill(255, 0, 0);
        p.text("Time's Up!", p.width/2 - 120, p.height/2);
        p.noLoop(); // stop draw() from running
      }
    }
    
    // Simulate score increase (remove this in final)
    public void detectMouse() {
      if (p.millis() - startTime < timeLimit) {
        score++; // Just for testing; replace with actual mole logic later
      }
    }     
    

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.ScoreTimerManager");
    }
    
}
=======
public class ScoreTimerManager {
    public static void main(String[] args) {
>>>>>>> parent of 9b33c5d (combined ScoreTimerManager and SpawnManager)
        
    }
}
