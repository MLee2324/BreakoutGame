# Java Breakout Game

This project is a Java implementation of the classic **Breakout** arcade game using **AWT** and **Swing**. The player controls a paddle with the mouse to bounce a ball, break bricks, score points, and avoid losing all lives.

The project emphasizes event-driven programming, basic physics, collision detection, and custom rendering with Java’s graphics APIs.

---

## Features

* Mouse-controlled paddle
* Ball physics with wall and paddle collisions
* Brick collision detection and removal
* Score and lives tracking
* Win and game-over screens
* Restart button to reset the game
* Smooth animation using a Swing Timer

---

## How the Game Works

1. The paddle follows the horizontal mouse position
2. Clicking the mouse starts the ball movement
3. The ball bounces off walls, the paddle, and bricks
4. When a brick is hit, it is removed and the score increases
5. If the ball falls below the paddle, a life is lost
6. The game ends when:

   * All bricks are destroyed (Win)
   * All lives are lost (Game Over)

---

## Controls

* **Mouse Move**: Move the paddle left and right
* **Mouse Click**: Start the ball
* **Restart Button**: Reset the game after win or loss

---

## Project Structure

```
project-root/
│
├── Breakout.java        # Main game logic, rendering, and input handling
```

---

## How to Run

1. Ensure you have Java installed (JDK 8 or higher)
2. Compile the program:

```bash
javac Breakout.java
```

3. Run the game:

```bash
java Breakout
```

A window titled **"Breakout"** will appear.

---

## Technical Details

* Rendering is done using `Graphics2D` with antialiasing enabled
* Animation is driven by a `javax.swing.Timer`
* Bricks are stored as coordinate pairs in an `ArrayList`
* Collision detection is handled using bounding-box checks
* Paddle collision affects ball angle based on hit position

---

## Limitations

* Single-level gameplay
* Fixed screen size (800x800)
* No sound effects or background music
* Simple collision response model

---

## Possible Improvements

* Multiple levels with increasing difficulty
* Sound effects and background music
* Keyboard controls as an alternative to mouse input
* Power-ups and special bricks
* Improved physics and collision handling

---

## Author

Built as a personal Java graphics and game development project.

---

## Notes

This project focuses on understanding Java’s graphics pipeline and interactive game loops rather than building a production-level game engine.
