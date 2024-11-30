package hw2;

public class DFSPercolation {

    private final int[][] grid;
    private final int sideLength;
    private int numberOfOpenSites;

    public DFSPercolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        sideLength = N;
        numberOfOpenSites = 0;
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void dfs(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength || grid[row][col] != 1) {
            return;
        }
        grid[row][col] = 2;
        dfs(row - 1, col);
        dfs(row + 1, col);
        dfs(row, col - 1);
        dfs(row, col + 1);
    }

    public void open(int row, int col) {
        validateRange(row, col);
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            numberOfOpenSites += 1;
        }
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }
            }
        }
        if (!isOpen(row, col)) {
            return false;
        }
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
        for (int i = 0; i < sideLength; i++) {
            if (grid[0][i] == 1) {
                dfs(0, i);
            }
        }
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
