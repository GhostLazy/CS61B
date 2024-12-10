package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

//import java.util.ArrayList;
//import java.util.List;

public class Board implements WorldState {
    private final int[][] tiles;
    private final int N;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, N);
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

//    private int[] getPosition() {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (tileAt(i, j) == 0) {
//                    return new int[] {i, j};
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Iterable<WorldState> neighbors() {
//        List<WorldState> neighbours = new ArrayList<>();
//        int zeroRow = getPosition()[0];
//        int zeroCol = getPosition()[1];
//        int[][] DIRs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//        for (int[] DIR : DIRs) {
//            int newRow = zeroRow + DIR[0];
//            int newCol = zeroCol + DIR[1];
//            if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
//                int[][] newTiles = new int[N][N];
//                for (int i = 0; i < N; i++) {
//                    System.arraycopy(tiles[i], 0, newTiles[i], 0, N);
//                }
//                newTiles[zeroRow][zeroCol] = tileAt(newRow, newCol);
//                newTiles[newRow][newCol] = 0;
//                neighbours.add(new Board(newTiles));
//            }
//        }
//        return neighbours;
//    }
    /**
     * Returns the neighbors of the current board
     *
     * @author <a href="http://joshh.ug/neighbors.html">...</a>
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        int BLANK = 0;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
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
        int N = size();
        s.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
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
