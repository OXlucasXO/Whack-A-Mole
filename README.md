
# Guack-a-mole
An epic survival experience. with high octane action and heart wrenching backstories. what did the moles do to you find out and play Whac-A-Mole


## UML Diagram

[UML Diagram](https://mermaid.live/edit#pako:eNqlV-1u4jgUfRXLv7pbqKAwUKLVSimwCxJpq4GZndllhVxigtXEiWxnWqbqu6_thNZ2kn5o-UGScz99fe518gi3aYihB7cx4nxCUMRQsqZA_jQCAkQoeCwQ9WsvM3RPA0RRhBngxoOhE6Q5xzNEw1jqJMaD6WebMrwiCWbPzlzkRfuUYyEIjfjJLzaYZxYSMnRvATr4OCbbOxxagjt8uGGYcxtuSwiLP1GCbS-yCCdLwWQG__wLEIt4KX1aU7NYVm3Mot34WRZjATID8xlDhwXh4rdZGuPfwV7-cyPoizzQ8sSWf7CAhAqwzRnDVHwlnNzGWHmd0xA_OFoqTqmifTliih-EMp3gGB0chbZSCK4X083X-XJ-Ka-TL5_91fz6ynESzK82k-nC_765nK7-mk6vNspq6Xryv21mDr6LU1TYK8lmMl-u_Kvx1Kz1PJFr18vQd1XRvkY0TumORGCrL0a25paelNvYApXaW7RUJmpPuU9DVSqbtXkWIqGrv0gjsrX5S3gmy2ph9ZSMsJgVroFDpFpmWh350Xb-GNPMUEbFDNdv1G-vbYOaznXbrZLXo8MznaeLCcSEsnJwIaEFSYgwCfNHevSi7gyLmo4-3RGKYqB8rebBdONPJs3C5ZdLk2XuSo6FqxLHGlclXxQ7rn9Iq6q69mzhnxWfqjAKQ5WAPWDzW8HQVpiCCrFibI-6V9qvZgoW_fzQAgeLQjF-oU7hsQW0anl5pWmcBH0544XDD0el7H2rLfSwr11FKdo3i2r4Urf20yJu3VbL7lY10LnLDr9xAh27_zX58piEkr8k4yxdMecGURw3lGZW2d7mPawghwoSEhlPWMPlNpUhEJXxdKs7R4BG604ZLZjkDAmSUkcW4h3KYzFO49Q1Yzisg-_3ROgOkjxy5Kez_0PGYrpwPcyKDgXlgu33mOOgK4UOF75JMx3Jhr_bsLN1C_wDx03E1691v7bb9iuLp84_IUXc0bKOj0at6jx2Va1oyqRsPQ9I_006moMeSDTUpBS8pdSQn9x9rEYcB_dE7MsFmaut1uhdJu8MVdWTxsce8ySTM0xDDo4U16t8n0YxhqsKZcVfdWLovMONPiOrGrM3c529lau7gc9vOiVhYAtGjITQEyzHLSirKN_U5SPUdF9DscfymIeevA0Ru1vDNX2SNhmif6dpcjRjaR7tobdDMZdPxbFZfgk9q8i0MBunORXQ-6Q9QO8RPkDvvDc4Gw66o17n06B_MRqe91rwIHW6Z4PhRWc46A9Hve6w039qwZ865vnZcDTqdkej_qjX71ycd6QBDolIWVB-ianL038W0CKQ)

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


## Requirements



## How to run

### ***follow the sequence Below***

1)  create a new Folder in desired location
2)  Name the folder What ever you want
3)  rigiht click on `Guack-a-mole.zip`
3)  extract `Guack-a-mole.zip` into the craeted folder
4)  open the folder in VSCode
5)  run `Main.java`
6)  to exit the game press `Esc` key

##  Recomendation
### *Use a mouse to play*


## Known Issues
*   When you run once, then you cannot stop to play
*   Sometimes the game gets stuck at launch as the `spawnHolesAndMoles()` class gets stuck in a while loop if there is nt enough space to place a hole
*   Pressing `R` to reset the game doesn't reset the background completely


*   

