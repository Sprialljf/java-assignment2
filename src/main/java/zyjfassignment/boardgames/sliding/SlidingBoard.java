
package zyjfassignment.boardgames.sliding;

import zyjfassignment.boardgames.core.GridBoard;
import zyjfassignment.boardgames.core.ui.TextUtil;
import java.util.*;

public class SlidingBoard extends GridBoard {
    private final int N;
    private final int[][] a;
    private int zr, zc; // zero position

    public SlidingBoard(int n) {
        super(n, n);
        this.N = n;
        this.a = new int[N][N];
        initSolved();
        shuffle(100);
    }

    private void initSolved() {
        int v = 1;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = (i == N - 1 && j == N - 1) ? 0 : v++;
        zr = N - 1; zc = N - 1;
    }

    private void shuffle(int steps) {
        Random rng = new Random();
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        for (int s=0; s<steps; s++) {
            int k = rng.nextInt(4);
            int nr = zr + dr[k], nc = zc + dc[k];
            if (nr>=0&&nr<N&&nc>=0&&nc<N) { swap(zr,zc,nr,nc); zr=nr; zc=nc; }
        }
    }

    private void swap(int r1,int c1,int r2,int c2){
        int t=a[r1][c1]; a[r1][c1]=a[r2][c2]; a[r2][c2]=t;
    }

    public boolean move(char dir){
        int nr=zr, nc=zc;
        if (dir=='w') nr++;       // move tile down (blank up)
        else if (dir=='s') nr--;
        else if (dir=='a') nc++;
        else if (dir=='d') nc--;
        else return false;
        if (nr<0||nr>=N||nc<0||nc>=N) return false;
        swap(zr,zc,nr,nc); zr=nr; zc=nc; return true;
    }

    public boolean solved(){
        int v=1;
        for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
            int expect = (i==N-1&&j==N-1)?0:v++;
            if (a[i][j]!=expect) return false;
        }
        return true;
    }

    @Override public void print(){
        int cellW=4;
        for (int i=0;i<N;i++){
            StringBuilder sb=new StringBuilder();
            for (int j=0;j<N;j++){
                String s = a[i][j]==0 ? " " : String.valueOf(a[i][j]);
                sb.append(TextUtil.padCenter(s, cellW));
            }
            System.out.println(sb);
        }
    }
}
