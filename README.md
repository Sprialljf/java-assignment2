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
- **`Main.java`** — Unified launcher that allows the user to choose between **Dots & Boxes** and **Sliding Puzzle**.  
  Handles initialization, player setup (human vs human or human vs AI), and the main control flow.

---

### `core/`

####  Game Base & Player Logic
- **`GameEngine.java`** — Common interface defining the `playLoop()` method for all games.  
- **`GameBoard.java`** — Abstract base for grid-based boards; provides dimensions and rendering helpers.  
- **`Player.java`** — Defines player attributes including name, id, and type (human or AI).  
- **`Bot.java`** — Abstract AI player class; uses strategy pattern for varying difficulty levels.  

####  `core/strategy/`
- **`EdgeChoiceStrategy.java`** — Strategy interface for edge selection logic used by AI bots.  
- **`EasyStrategy.java`** — Random move selection; represents the **EasyBot** behavior.  
- **`HardStrategy.java`** — Heuristic-based decision-making; prioritizes completing boxes and avoiding risky edges.  

#### `core/ui/`
- **`TextUtil.java`** — Utility class for terminal text formatting:
  - ANSI color output (red, blue, green, yellow)
  - Centered and padded strings
  - Helper methods for removing ANSI codes

---

### `dots/`
- **`DotsGame.java`** — Implements the full game logic for **Dots & Boxes**, managing turns, edge claiming, and score tracking.  
- **`DotsBoard.java`** — Represents the dot grid and tracks claimed edges and completed boxes.  
- **`Edge.java`** — Represents an edge (horizontal or vertical) connecting two dots; records ownership and position.

---

### `sliding/`
- **`SlidingGame.java`** — Game loop for **Sliding Puzzle**; handles user moves and win condition checking.  
- **`SlidingBoard.java`** — Implements the NxN tile board, supporting shuffling, movement, and completion checks.

---
## Notes
---------------------------------------------------------------------------
- The board size is customized by users, but I give a pecific range (users can adjust the size from 2*2 to 6*6)of it to avoid some exaggerating situation.
- The board is can get well end due to I research the relevant information online to ensure board has a solution(ifSolution). 
- Game class has the functions of recording game timing, game moving step number and calculating the final score according to timing and steps(Since when the lower timing and step numbers are,the higher score will be. I roughly use the number "10000" calculating the final score.) 

## How to compile and run
---------------------------------------------------------------------------
1. Openning the terminal
2. Running the following instructions:
   cd /C:/Users/14661/Desktop/bu/oodjava/hw1 (Enter the directory the code is stored);
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
