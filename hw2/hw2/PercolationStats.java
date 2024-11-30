package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private final int T;
    private final double[] scores;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        this.scores = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            scores[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(scores);
    }

    public double stddev() {
        return StdStats.stddev(scores);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.pow(T, 0.5);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.pow(T, 0.5);
    }
}
