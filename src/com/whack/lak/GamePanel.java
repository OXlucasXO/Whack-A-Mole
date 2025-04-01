import processing.core.PApplet;

public class GamePanel extends PApplet {

    public void settings() {
        size(640, 360); // Set window size
    }

    public void setup() {
        background(200);
    }

    public void draw() {
        fill(255, 0, 0);
        ellipse(mouseX, mouseY, 50, 50); // Draw a circle following the mouse
    }

    public static void main(String[] args) {
        PApplet.main("com.whack.lak.GamePanel");
    }
}