# CS611-Assignment 2
## Dots & Boxes Game (N-Puzzle)

---------------------------------------------------------------------------
- **Name**: [Jingfeng Li]
            [Ziyang Wang]
- **Email**: [ljf628@bu.edu]
- **Student ID**: [U73840242]

## Files
---------------------------------------------------------------------------
### `app/`
- **`Main.java`** — Game starting entry.

---

### `core/`

####  Game Base & Player Logic
- **`GameEngine.java`** — Common interface defining the `playLoop()` method for all games.  
- **`GameBoard.java`** — Abstract base for grid-based boards; provides dimensions and rendering methods.  
- **`Player.java`** — Defines player attributes including name, id, and type (human or Bot).  
- **`Bot.java`** — Abstract Bot class; uses strategy pattern for varying difficulty levels.  

####  `core/strategy/`
- **`EdgeChoiceStrategy.java`** — Strategy interface for edge selection palying logic used by bots.  
- **`EasyStrategy.java`** — Random move selection; represents the **EasyBot** behavior.  
- **`HardStrategy.java`** — Based on designing an algorithm that allowing bot can select A more profitable approach.

#### `core/ui/`
- **`TextUtil.java`** — Text rendering and beautification of terminal display
---

### `dots/`
- **`DotsGame.java`** — Implementing the game logic for **Dots & Boxes**.
- **`DotsBoard.java`** — Representing the dot grid and tracks claimed edges and completed boxes.  
- **`Edge.java`** — Representing an edge connecting two dots and recording ownership and position.

---

### `sliding/`
- **`SlidingGame.java`** — Game loop for **Sliding Puzzle**.
- **`SlidingBoard.java`** — Implementing the NxN tile board, supporting shuffling, movement, and completion checks.

---
## Notes
---------------------------------------------------------------------------
- This project demonstrates modular object-oriented design using **inheritance**, **composition**, and the **strategy pattern**.  
- Both games share a unified interface (`GameEngine`), showing **polymorphism** across different board types.  
- **EasyBot** and **HardBot** are implemented through interchangeable strategies to adjust difficulty dynamically.  
- **TextUtil** ensures clear terminal display with colorful codes output.

---

## How to compile and run
---------------------------------------------------------------------------
1. Openning the terminal
2. Running the following instructions:
   cd /C:/Users/14661/Desktop/bu/oodjava/hw2 (Enter the directory the code is stored);
   javac Game.java Board.java Player.java (Compiling java files);
   java Game (Executing java programme).
## Input/Output Example
---------------------------------------------------------------------------

```text
Input your name:
Sprial
Welcome player: Sprial

Please input rows (2-6):
3
Please input cols (2-6):
3

2 8 6
1 4 3
5 7 _

Please,input number you want to move:
4
Invalid move, please try again.

Please,input number you want to move:
7

2 8 6
1 4 3
5 _ 7

...（Omission process）

Please,input number you want to move:
6

1 2 3
4 5 6
7 8 _

You have completed, now the board is:
1 2 3
4 5 6
7 8 _

Congratulations! Sprial, you solved the puzzle in 46 steps, taking 627 seconds.
Your score is: 14.0

Want to play again? Please enter (y/n):
y

Input your name:
