
package zyjfassignment.boardgames.dots;

import zyjfassignment.boardgames.core.Bot;
import zyjfassignment.boardgames.core.GameEngine;
import zyjfassignment.boardgames.core.Player;

import java.util.Scanner;

public class DotsGame implements GameEngine {
    private final DotsBoard board;
    private final Player p1;
    private final Player p2;

    public DotsGame(DotsBoard board, Player p1, Player p2) {
        this.board = board; this.p1 = p1; this.p2 = p2;
    }

    @Override public String name() { return "Dots & Boxes"; }

    /**
     * Main loop for a 2-player match (supports human vs human on a single terminal).
     * - On each turn, render board and ask current player for an edge id.
     * - If a box is completed, the same player moves again.
     * - Game ends when all edges are claimed.
     */
    @Override public void playLoop(Scanner in) {
        int turn = 1; // 1 or 2
        while (true) {
            System.out.println();
            board.print();
            System.out.printf("Score  %s: %d   %s: %d%n",
                    p1, board.scoreForPlayer(1), p2, board.scoreForPlayer(2));

            if (board.isFull()) {
                System.out.println("Game over.");
                int s1 = board.scoreForPlayer(1), s2 = board.scoreForPlayer(2);
                if (s1 == s2) System.out.println("Draw!");
                else System.out.println((s1 > s2 ? p1 : p2) + " wins!");
                return;
            }

            Player cur = (turn == 1 ? p1 : p2);
            System.out.print(cur + " choose an edge id (q to quit): ");
            int eid = -1;
            if (cur.isHuman()) {
                String s = in.nextLine().trim();
                if (s.equalsIgnoreCase("q")) return;
                try { eid = Integer.parseInt(s); } catch (Exception e) { System.out.println("Please input a valid integer edge id."); continue; }
            } else {
                eid = ((Bot)cur).chooseEdge(board);
                System.out.println(eid);
            }

            int made = board.claimEdge(eid, cur.id());
            if (made < 0) {
                System.out.println("Illegal or already-claimed edge.");
                continue;
            }
            if (made == 0) turn = 3 - turn; // no box: switch player; got box: move again
        }
    }
}
