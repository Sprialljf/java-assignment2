
package zyjfassignment.boardgames.sliding;

import zyjfassignment.boardgames.core.GameEngine;

import java.util.Scanner;

public class SlidingGame implements GameEngine {
    private final SlidingBoard board;
    public SlidingGame(SlidingBoard board){ this.board=board; }
    @Override public String name(){ return "Sliding Puzzle"; }

    @Override public void playLoop(Scanner in){
        while(true){
            System.out.println();
            board.print();
            if (board.solved()) { System.out.println("Solved!"); return; }
            System.out.print("Move (w/a/s/d, q to quit): ");
            String s = in.nextLine().trim();
            if (s.equalsIgnoreCase("q")) return;
            if (s.isEmpty()) continue;
            if (!board.move(s.charAt(0))) System.out.println("Illegal move.");
        }
    }
}
