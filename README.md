# Whack-A-Mole
An epic survival experience. with high octane action and heart wrenching backstories. what did the moles do to you find out and play Whac-A-Mole

classDiagram
    Game <|-- UI
    Game <|-- Logic
    Game <|-- Score
    Game <|-- Interaction
    class UI {
        +Score
        +timer
    }
