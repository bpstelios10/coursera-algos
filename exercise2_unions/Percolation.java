package exercise2_unions;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private int numberOfOpenSites = 0;

    private final WeightedQuickUnionUF union;
    private final WeightedQuickUnionUF unionWithBottomSite;
    private final boolean[] openSites;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        union = new WeightedQuickUnionUF((n * n) + 1);
        unionWithBottomSite = new WeightedQuickUnionUF((n * n) + 2);
        openSites = new boolean[n * n];

        for (int x = 1; x <= n; x++) {
            union.union(0, x);
            unionWithBottomSite.union(0, x);
            unionWithBottomSite.union(n * n + 1, n * n - x + 1);
        }
    }

    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;

        numberOfOpenSites++;
        openSites[siteIndex(row, col) - 1] = true;
        linkOpenNeighbours(row, col);
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();

        return openSites[siteIndex(row, col) - 1];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
        return isOpen(row, col) && union.find(siteIndex(row, col)) == union.find(0);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return unionWithBottomSite.find(size * size + 1) == unionWithBottomSite.find(0)
                && numberOfOpenSites > 0;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        StdOut.println(percolation.isFull(1, 3));
        percolation.open(2, 3);
        StdOut.println(percolation.isFull(2, 3));
        percolation.open(3, 3);
        StdOut.println(percolation.isFull(3, 3));

        percolation.open(3, 1);
        StdOut.println(percolation.isFull(3, 1));
        percolation.open(3, 2);
        StdOut.println(percolation.isFull(3, 1));
        percolation.open(3, 1);
        StdOut.println(percolation.isFull(3, 1));
    }

    private int siteIndex(int x, int y) {
        return (x - 1) * size + y;
    }

    private void linkOpenNeighbours(int row, int col) {
        int siteIndex = siteIndex(row, col);

        if (row != 1 && isOpen(row - 1, col)) {
            union.union(siteIndex, siteIndex - size);
            unionWithBottomSite.union(siteIndex, siteIndex - size);
        }
        if (row != size && isOpen(row + 1, col)) {
            union.union(siteIndex, siteIndex + size);
            unionWithBottomSite.union(siteIndex, siteIndex + size);
        }
        if (col != 1 && isOpen(row, col - 1)) {
            union.union(siteIndex, siteIndex - 1);
            unionWithBottomSite.union(siteIndex, siteIndex - 1);
        }
        if (col != size && isOpen(row, col + 1)) {
            union.union(siteIndex, siteIndex + 1);
            unionWithBottomSite.union(siteIndex, siteIndex + 1);
        }
    }
}
