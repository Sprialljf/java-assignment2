# CS611-Assignment 1
## Sliding Puzzle Game (N-Puzzle)

---------------------------------------------------------------------------
- **Name**: [Jingfeng Li]
- **Email**: [ljf628@bu.edu]
- **Student ID**: [U73840242]

## Files
---------------------------------------------------------------------------
- `Board.java`:  
  This section is used for creating a board that can be customized in size. And the board can be shuffled to a solvable state. Meanwhile, during the game, there will be vertifying valid movement. 

- `Player.java`:  
  This section creates a player including the name, recording step number.

- `Game.java`:  
  It mainly includes game start, running loop and resetting game. By the way, it also gets input and output of words of game, and includes get game timing, calculating the last score.  

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
