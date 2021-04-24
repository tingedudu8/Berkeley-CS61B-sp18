package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private boolean percolated = false;
    private boolean[] openIndex;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF secgrid;
    private int num = 0;
    private int sentinelT;
    private int sentinelB;


    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater than 0 but given N = " + N + "."
            );
        }
        this.N = N;
        openIndex = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            openIndex[i] = false;
        }
        // only virtual top site
        grid = new WeightedQuickUnionUF(N * N + 1);
        sentinelT = N * N;
        // virtual top site and bottom site
        secgrid = new WeightedQuickUnionUF(N * N + 2);
        sentinelB = N * N + 1;
    }

    private boolean validIndex(int row, int col) {
        if (row >= 0 && row <= N - 1 && col >= 0 && col <= N - 1) {
            return true;
        }
        return false;
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }


    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (!validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid argument given: row = " + row + " col = " + col + "."
            );
        } else if (!openIndex[getIndex(row, col)]) {
            openIndex[getIndex(row, col)] = true;
            num += 1;
            if (row == 0) {
                grid.union(getIndex(row, col), sentinelT);
                secgrid.union(getIndex(row, col), sentinelT);
            }
            if (validIndex(row - 1, col) && openIndex[getIndex(row - 1, col)]) {
                grid.union(getIndex(row, col), getIndex(row - 1, col));
                secgrid.union(getIndex(row, col), getIndex(row - 1, col));
            }
            if (validIndex(row + 1, col) && openIndex[getIndex(row + 1, col)]) {
                grid.union(getIndex(row, col), getIndex(row + 1, col));
                secgrid.union(getIndex(row, col), getIndex(row + 1, col));
            }
            if (validIndex(row, col - 1) && openIndex[getIndex(row, col - 1)]) {
                grid.union(getIndex(row, col), getIndex(row, col - 1));
                secgrid.union(getIndex(row, col), getIndex(row, col - 1));
            }
            if (validIndex(row, col + 1) && openIndex[getIndex(row, col + 1)]) {
                grid.union(getIndex(row, col), getIndex(row, col + 1));
                secgrid.union(getIndex(row, col), getIndex(row, col + 1));
            }
            if (row == N - 1) {
                secgrid.union(getIndex(row, col), sentinelB);
            }
            if (secgrid.connected(sentinelT, sentinelB)) {
                percolated = true;
            }
        }
    }
    public boolean isOpen(int row, int col) {
        if (!validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + "."
            );
        }
        // is the site (row, col) open?
        return openIndex[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        if (!validIndex(row, col)) {
            throw new IndexOutOfBoundsException(
                    "Invalid arguments given: row = " + row + " col = " + col + "."
            );
        }
        if (grid.connected(getIndex(row, col), sentinelT)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // number of open sites
        return num;
    }

    public boolean percolates() {
        // does the system percolate?
        return percolated;
    }

    public static void main(String[] args) {
        // use for unit testing (not required)
        // see TestPercolation.java
        // @source https://github.com/aviatesk/cs61b-sp18/blob/master/hw2/hw2/TestPercolation.java
    }
}
