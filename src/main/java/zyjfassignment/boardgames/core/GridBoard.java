
package zyjfassignment.boardgames.core;

public abstract class GridBoard {
    protected final int R, C;

    public GridBoard(int rows, int cols) {
        if (rows < 2 || cols < 2) throw new IllegalArgumentException("rows/cols must be >= 2");
        this.R = rows; this.C = cols;
    }

    public int rows() { return R; }
    public int cols() { return C; }

    public abstract void print();
}
