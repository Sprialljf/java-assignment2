//
//package zyjfassignment.boardgames.sliding;
//
//import zyjfassignment.boardgames.core.GridBoard;
//import zyjfassignment.boardgames.core.ui.TextUtil;
//import java.util.*;
//
//public class SlidingBoard extends GridBoard {
//    private final int N;
//    private final int[][] a;
//    private int zr, zc; // zero position
//
//    public SlidingBoard(int n) {
//        super(n, n);
//        this.N = n;
//        this.a = new int[N][N];
//        initSolved();
//        shuffle(100);
//    }
//
//    private void initSolved() {
//        int v = 1;
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                a[i][j] = (i == N - 1 && j == N - 1) ? 0 : v++;
//        zr = N - 1; zc = N - 1;
//    }
//
//    private void shuffle(int steps) {
//        Random rng = new Random();
//        int[] dr = {-1,1,0,0};
//        int[] dc = {0,0,-1,1};
//        for (int s=0; s<steps; s++) {
//            int k = rng.nextInt(4);
//            int nr = zr + dr[k], nc = zc + dc[k];
//            if (nr>=0&&nr<N&&nc>=0&&nc<N) { swap(zr,zc,nr,nc); zr=nr; zc=nc; }
//        }
//    }
//
//    private void swap(int r1,int c1,int r2,int c2){
//        int t=a[r1][c1]; a[r1][c1]=a[r2][c2]; a[r2][c2]=t;
//    }
//
//    public boolean move(char dir){
//        int nr=zr, nc=zc;
//        if (dir=='w') nr++;       // move tile down (blank up)
//        else if (dir=='s') nr--;
//        else if (dir=='a') nc++;
//        else if (dir=='d') nc--;
//        else return false;
//        if (nr<0||nr>=N||nc<0||nc>=N) return false;
//        swap(zr,zc,nr,nc); zr=nr; zc=nc; return true;
//    }
//
//    public boolean solved(){
//        int v=1;
//        for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
//            int expect = (i==N-1&&j==N-1)?0:v++;
//            if (a[i][j]!=expect) return false;
//        }
//        return true;
//    }
//
//    @Override public void print(){
//        int cellW=4;
//        for (int i=0;i<N;i++){
//            StringBuilder sb=new StringBuilder();
//            for (int j=0;j<N;j++){
//                String s = a[i][j]==0 ? " " : String.valueOf(a[i][j]);
//                sb.append(TextUtil.padCenter(s, cellW));
//            }
//            System.out.println(sb);
//        }
//    }
//}
package zyjfassignment.boardgames.sliding;

import zyjfassignment.boardgames.core.GridBoard;
import zyjfassignment.boardgames.core.ui.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sliding puzzle board supporting generic m x n (n >= 2).
 * Tiles are 0..(m*n-1) where 0 is the blank.
 * Solved state is [1,2,...,m*n-1,0] row-major.
 */
public class SlidingBoard extends GridBoard {
    private final int[][] a;   // board values
    private int zr, zc;        // blank position (value 0)
    private final Random rng = new Random();

    /** Keep Main compatible: new SlidingBoard(4) -> 4x4 */
    public SlidingBoard(int n) {
        super(n, n);
        this.a = new int[R][C];
        initSolved();
    }

    /** New: rectangular m x n constructor (n >= 2). */
    public SlidingBoard(int rows, int cols) {
        super(rows, cols);
        this.a = new int[R][C];
        initSolved();
    }

    private void initSolved() {
        int v = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                a[i][j] = (i == R - 1 && j == C - 1) ? 0 : v++;
            }
        }
        zr = R - 1;
        zc = C - 1;
    }

    /** Return whether the board is solved [1..N-1,0]. */
    public boolean solved() {
        int expect = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int should = (i == R - 1 && j == C - 1) ? 0 : expect++;
                if (a[i][j] != should) return false;
            }
        }
        return true;
    }

    /** Move by WASD controlling the BLANK (0). Returns true if moved. */
    public boolean move(char ch) {
        ch = Character.toLowerCase(ch);
        int dr = 0, dc = 0;
        switch (ch) {
            case 'w': dr = -1; dc = 0; break;
            case 's': dr =  1; dc = 0; break;
            case 'a': dr =  0; dc = -1; break;
            case 'd': dr =  0; dc =  1; break;
            default: return false;
        }
        int nr = zr + dr, nc = zc + dc;
        if (nr < 0 || nr >= R || nc < 0 || nc >= C) return false;
        swap(zr, zc, nr, nc);
        zr = nr; zc = nc;
        return true;
    }

    /** Shuffle by random valid blank moves; preserves solvability for any m x n. */
    public void shuffle(int steps) {
        if (steps < 0) steps = 0;
        initSolved();
        int lastDir = -1; // 0:up,1:down,2:left,3:right
        for (int s = 0; s < steps; s++) {
            int br = zr, bc = zc;
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            List<Integer> cand = new ArrayList<>(4);
            for (int d = 0; d < 4; d++) {
                int nr = br + dr[d], nc = bc + dc[d];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                    if (lastDir != -1 && (d ^ 1) == lastDir) continue; // avoid immediate backtrack
                    cand.add(d);
                }
            }
            if (cand.isEmpty()) { // allow backtrack if no alt
                for (int d = 0; d < 4; d++) {
                    int nr = br + dr[d], nc = bc + dc[d];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) cand.add(d);
                }
            }
            int d = cand.get(rng.nextInt(cand.size()));
            int nr = br + dr[d], nc = bc + dc[d];
            swap(br, bc, nr, nc);
            zr = nr; zc = nc;
            lastDir = d;
        }
    }

    private void swap(int r1, int c1, int r2, int c2) {
        int t = a[r1][c1];
        a[r1][c1] = a[r2][c2];
        a[r2][c2] = t;
    }

    @Override
    public void print() {
        // auto cell width by number of tiles
        int maxNum = R * C - 1;
        int cellW = Math.max(2, String.valueOf(maxNum).length() + 1); // +1 for spacing
        for (int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < C; j++) {
                String s = (a[i][j] == 0) ? " " : String.valueOf(a[i][j]);
                sb.append(TextUtil.padCenter(s, cellW));
            }
            System.out.println(sb);
        }
    }
}

