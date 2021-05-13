package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private int N;
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                this.tiles[r][c] = tiles[r][c];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("Invalid index given: i == " + i + " j == " + j);
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    /*@source http://joshh.ug/neighbors.html*/
    /*Returns the neighbors of the current board*/
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
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
        int estimateDis = 0;
        int expectedVal = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (expectedVal == N * N) {
                    break;
                }
                if (tileAt(i, j) != expectedVal) {
                    estimateDis += 1;
                }
                expectedVal += 1;
            }
        }
        return estimateDis;
    }

    public int manhattan() {
        int estimateDis = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int actualVal = tileAt(r, c);
                if (actualVal == 0) {
                    continue;
                }
                int expectedRowVal = (actualVal - 1) / N;
                int expectedColVal = (actualVal - 1) % N;
                estimateDis += Math.abs(expectedRowVal - r);
                estimateDis += Math.abs(expectedColVal - c);
            }
        }
        return estimateDis;
    }

    /*@note Should simply return the result of manhattan() when
    submitted to Autograder*/
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
//        return hamming();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board yBoard = (Board) y;
        if (N != yBoard.N) {
            return false;
        }
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                if (this.tileAt(r, c) != yBoard.tileAt(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }


}
