package hw2;

public class DFSPercolation {

    // grid of percolation implemented by 2D-array
    private final int[][] grid;
    // side length of the grid
    private final int sideLength;
    // number of opened sites
    private int numberOfOpenSites;

    // initialize percolation supported by DFS
    public DFSPercolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        // all initial sites have value 0 signifying blocked
        grid = new int[N][N];
        sideLength = N;
        numberOfOpenSites = 0;
    }

    // throw an error if location provided is out of range
    private void validateRange(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength) {
            throw new IndexOutOfBoundsException();
        }
    }

    // label site and neighbor sites and so on (recursively)
    private void dfs(int row, int col) {
        // stop recursion if location is out of range or the site is blocked / have been labeled
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength || grid[row][col] != 1) {
            return;
        }
        // turn the value into 2 in case of infinite loop
        grid[row][col] = 2;
        // label neighbor sites recursively
        dfs(row - 1, col);
        dfs(row + 1, col);
        dfs(row, col - 1);
        dfs(row, col + 1);
    }

    // open the site by turning the value into 1
    public void open(int row, int col) {
        validateRange(row, col);
        // pass if the site have been opened
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            numberOfOpenSites += 1;
        }
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        // 0: blocked, 1: opened but not labeled, 2: opened and labeled
        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);
        // clear all labels of DFS to avoid failing to label
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }
            }
        }
        // return false if the site is blocked yet
        if (!isOpen(row, col)) {
            return false;
        }
        // check if the site is connected with any of top sites
        dfs(row, col);
        for (int i = 0; i < sideLength; i++) {
            if (grid[0][i] == 2) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // label all sites neighbored to any of top sites (directly or indirectly)
        for (int i = 0; i < sideLength; i++) {
            if (grid[0][i] == 1) {
                dfs(0, i);
            }
        }
        // check if any of bottom sites is neighbored to any of top sites (directly or indirectly)
        for (int i = 0; i < sideLength; i++) {
            if (isFull(sideLength - 1, i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
