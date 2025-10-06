
package zyjfassignment.boardgames.app;

import zyjfassignment.boardgames.core.*;
import zyjfassignment.boardgames.core.strategy.*;
import zyjfassignment.boardgames.dots.*;
import zyjfassignment.boardgames.sliding.*;
import zyjfassignment.boardgames.core.Bot;
import zyjfassignment.boardgames.core.GameEngine;
import zyjfassignment.boardgames.core.Player;
import zyjfassignment.boardgames.core.strategy.EasyStrategy;
import zyjfassignment.boardgames.core.strategy.EdgeChoiceStrategy;
import zyjfassignment.boardgames.core.strategy.HardStrategy;
import zyjfassignment.boardgames.dots.DotsBoard;
import zyjfassignment.boardgames.dots.DotsGame;
import zyjfassignment.boardgames.sliding.SlidingBoard;
import zyjfassignment.boardgames.sliding.SlidingGame;

import java.util.Scanner;

public class Main {
    private static int readInt(Scanner in, int def) {
        while (true) {
            String s = in.nextLine().trim();
            if (s.isEmpty()) return def;
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.print("Please enter an integer: "); }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a game: 1) Dots & Boxes  2) Sliding Puzzle");
        int game = readInt(in, 1);

        if (game == 2) {
            SlidingBoard board = new SlidingBoard(4);
            GameEngine g = new SlidingGame(board);
            g.playLoop(in);
            return;
        }

        // Dots & Boxes
        System.out.print("Rows and columns (e.g., 7 7, default 7 7): ");
        int R = 7, C = 7;
        try {
            String[] rc = in.nextLine().trim().split("\s+");
            if (rc.length == 2) { R = Integer.parseInt(rc[0]); C = Integer.parseInt(rc[1]); }
        } catch (Exception ignore) {}
        DotsBoard board = new DotsBoard(R, C);

        System.out.println("Mode: 1) Human vs Human  2) Human vs AI (Easy)  3) Human vs AI (Hard)");
        int mode = readInt(in, 1);

        Player p1 = new Player("Player1", 1, true);
        Player p2;
        if (mode == 1) {
            p2 = new Player("Player2", 2, true);
        } else {
            EdgeChoiceStrategy strat = (mode == 2) ? new EasyStrategy() : new HardStrategy();
            p2 = new Bot("AI", 2, strat);
        }

        GameEngine g = new DotsGame(board, p1, p2);
        g.playLoop(in);
    }
}
