package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState {
    private final int[][] tiles;
    private final int N;
    private int blankRow;
    private int blankCol;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, N);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighbours = new ArrayList<>();
        int[][] tempTiles = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempTiles[i][j] = tileAt(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(i - blankRow) + Math.abs(j - blankCol) == 1) {
                    int target = tileAt(i, j);
                    tempTiles[blankRow][blankCol] = target;
                    tempTiles[i][j] = 0;
                    neighbours.add(new Board(tempTiles));
                    tempTiles[i][j] = target;
                    tempTiles[blankRow][blankCol] = 0;
                }
            }
        }
        return neighbours;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                if (tileAt(i, j) - 1 != i * N + j) {
                    res += 1;
                }
            }
        }
        return res;
    }

    public int manhattan() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                int goal = tileAt(i, j) - 1;
                res += Math.abs(i - goal / N) + Math.abs(j - goal % N);
            }
        }
        return res;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board b = (Board) y;
        if (this.N != b.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
