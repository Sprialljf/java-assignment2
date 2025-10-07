//
//package zyjfassignment.boardgames.sliding;
//
//import zyjfassignment.boardgames.core.GameEngine;
//
//import java.util.Scanner;
//
//public class SlidingGame implements GameEngine {
//    private final SlidingBoard board;
//    public SlidingGame(SlidingBoard board){ this.board=board; }
//    @Override public String name(){ return "Sliding Puzzle"; }
//
//    @Override public void playLoop(Scanner in){
//        while(true){
//            System.out.println();
//            board.print();
//            if (board.solved()) { System.out.println("Solved!"); return; }
//            System.out.print("Move (w/a/s/d, q to quit): ");
//            String s = in.nextLine().trim();
//            if (s.equalsIgnoreCase("q")) return;
//            if (s.isEmpty()) continue;
//            if (!board.move(s.charAt(0))) System.out.println("Illegal move.");
//        }
//    }
//}
package zyjfassignment.boardgames.sliding;

import zyjfassignment.boardgames.core.GameEngine;

import java.util.Scanner;

/**
 * Implements GameEngine. Keeps Main unchanged:
 *   SlidingBoard board = new SlidingBoard(4);
 *   GameEngine g = new SlidingGame(board);
 *   g.playLoop(in);
 *
 * At the start of playLoop, we ask for m, n (n>=2) and shuffle steps.
 * If they differ from the passed-in board size, we create a NEW SlidingBoard(m, n)
 * internally (so we don't need to modify Main or GridBoard).
 */
public class SlidingGame implements GameEngine {
    private SlidingBoard board; // not final, so we can replace with m x n board

    public SlidingGame(SlidingBoard board) {
        this.board = board; // initially 4x4 per Main; may be replaced in playLoop
    }

    @Override
    public String name() {
        return "Sliding Puzzle";
    }

    private static int readInt(Scanner in, String prompt, int def, int min) {
        System.out.print(prompt + " (default " + def + "): ");
        while (true) {
            String s = in.nextLine().trim();
            if (s.isEmpty()) return def;
            try {
                int v = Integer.parseInt(s);
                if (v < min) {
                    System.out.print("Must be >= " + min + ". Try again: ");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.print("Please enter an integer: ");
            }
        }
    }

    @Override
    public void playLoop(Scanner in) {
        // 1) Ask custom size and shuffle steps
        int m = readInt(in, "Rows m", 4, 2);
        int n = readInt(in, "Cols n (>=2)", 4, 2);
        int steps = readInt(in, "Shuffle steps", 200, 0);

        // 2) If board dims differ, replace with a new board (no need to change Main)
        if (m != board.rows() || n != board.cols()) {
            board = new SlidingBoard(m, n);
        }
        board.shuffle(steps);

        // 3) Game loop: WASD to move the BLANK
        System.out.println();
        System.out.println("Sliding Puzzle " + m + "x" + n + " — use WASD to move the blank, q to quit.");
        while (true) {
            System.out.println();
            board.print();
            if (board.solved()) {
                System.out.println("Solved! 🎉");
                return;
            }
            System.out.print("Move (w/a/s/d, q to quit): ");
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("q")) return;
            if (s.isEmpty()) continue;
            if (!board.move(s.charAt(0))) {
                System.out.println("Illegal move.");
            }
        }
    }
}

