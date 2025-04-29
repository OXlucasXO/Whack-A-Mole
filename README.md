# Whack-A-Mole
An epic survival experience. with high octane action and heart wrenching backstories. what did the moles do to you find out and play Whac-A-Mole

classDiagram
    class Main {
        -SpawnManager spawnManager
        -MouseHandler mouseHandler
        -ScoreTimerManager scoreTimerManager
        +settings()
        +setup()
        +draw()
        +mouseClicked()
        +keyPressed()
        -resetGame()
        +main(String[] args)
    }

    class SpawnManager {
        -PApplet p
        -ArrayList<Hole> holes
        +ArrayList<Mole> moles
        -ScoreTimerManager scoreTimerManager
        +int currentVisibleMoleIndex
        +int moleVisibleTimer
        +int nextMoleDelayTimer
        -int MOLE_VISIBLE_DURATION
        +int MIN_DELAY_BETWEEN_MOLES
        -int MAX_HOLES
        -float MIN_HOLE_DISTANCE
        -PImage moleImage
        -PImage holeImage
        -Config config
        +SpawnManager(PApplet, ScoreTimerManager)
        +spawnHolesAndMoles()
        +updateMoleLogic()
        +display()
        +resetGame()
        +getHoles() ArrayList<Hole>
    }

    class MouseHandler {
        -SpawnManager spawnManager
        -ScoreTimerManager scoreTimerManager
        +MouseHandler(PApplet, SpawnManager, ScoreTimerManager)
        +handleMouseClicked()
    }

    class ScoreTimerManager {
        +int score
        +int startTime
        +int timeLimit
        -PFont scoreFont
        +PApplet p
        +final int TIME_ADD
        +final int TIME_SUB
        +ScoreTimerManager(PApplet)
        +update()
        -displayGameOver()
        +updateScore()
        +ResetScore()
        +addTime()
        +subtractTime()
    }

    class Mole {
        -PImage moleImage
        -PApplet p
        -float x, y
        +Mole(PApplet, PImage, float, float)
        +display()
    }

    class AssetManager {
    }

    class Config {
        -String moleImage
        -String holeImage
        -String scoreFont
        -PApplet p
        +Config(PApplet)
        +getMoleAsset() PImage
        +getHoleAsset() PImage
        +getScoreFont() PFont
    }

    class GamePanel {
    }

    class Hole {
        -PApplet p
        -float x
        -float y
        -float diameter
        -boolean clicked
        -int clickTimer
        -int clickDuration
        -int defaultColor
        -int redColor
        -int whiteOverlayColor
        +Hole(PApplet, PImage, float, float)
        +display()
        +isMouseOver() boolean
        +setClicked(boolean)
        +getX() float
        +getY() float
    }

    class LevelManager {
    }

    Main *-- SpawnManager : contains
    Main *-- MouseHandler : contains
    Main *-- ScoreTimerManager : contains
    SpawnManager *-- Config : uses
    SpawnManager *-- Hole : manages
    SpawnManager *-- Mole : manages
    SpawnManager -- ScoreTimerManager : interacts with
    MouseHandler -- SpawnManager : interacts with
    MouseHandler -- ScoreTimerManager : interacts with
    ScoreTimerManager -- PApplet : depends on
    Mole -- PApplet : depends on
    Mole -- PImage : depends on
    Config -- PApplet : depends on
    Config -- PImage : depends on
    Config -- PFont : depends on
    Hole -- PApplet : depends on
    Hole -- PImage : depends on
    SpawnManager -- ArrayList : uses