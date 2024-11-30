package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// @Source https://github.com/morty6688/cs61b-sp18/blob/master/hw2/hw2/Percolation.java
public class Percolation {

    private final boolean[][] grid;
    private final int N;
    private int numberOfOpenSites;
    private int topSite;
    private int bottomSite;
    private WeightedQuickUnionUF sites;
    // sites without bottomSite
    private WeightedQuickUnionUF sites2;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }

        this.N = N;
        numberOfOpenSites = 0;
        topSite = N * N;
        bottomSite = N * N + 1;

        sites = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            sites.union(topSite, xyTo1D(0, i));
        }
        for (int i = 0; i < N; i++) {
            sites.union(bottomSite, xyTo1D(N - 1, i));
        }

        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites2.union(topSite, xyTo1D(0, i));
        }
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void unionOpenNeighbor(int row, int col, int newRow, int newCol) {
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            return;
        }
        if (grid[newRow][newCol]) {
            sites.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
            sites2.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }

    public void open(int row, int col) {
        validateRange(row, col);
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        numberOfOpenSites += 1;
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row, col - 1);
        unionOpenNeighbor(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);

        if (!isOpen(row, col)) {
            return false;
        }
        return sites2.connected(xyTo1D(row, col), topSite);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        if (numberOfOpenSites == 0) {
            return false;
        }
        return sites.connected(topSite, bottomSite);
    }

    public static void main(String[] args) {
    }
}
