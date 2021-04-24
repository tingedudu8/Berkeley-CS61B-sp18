package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private PercolationFactory pf;
    private double[] thresholds;
    private int T;
    private int N;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater then 0 but given N = " + N + "."
            );
        }
        if (T <= 0) {
            throw new IllegalArgumentException(
                    "T should be greater than 0 but given T = " + T + "."
            );
        }

        this.N = N;
        this.T = T;
        this.pf = pf;
        thresholds = new double[T];
        simulate();
    }

    private void simulate() {
        for (int t = 0; t < T; t++) {
            Percolation system = pf.make(N);
            while (!system.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!system.isOpen(row, col)) {
                    system.open(row, col);
                }
            }
            double threshold = (double) system.numberOfOpenSites() / (N * N);
            thresholds[t] = threshold;
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

}
