import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Commented out since idea and jdk21 dont integrate properly atm. TimeUnit is not recognised by intellij
public class PercolationStatsConcurrent {
    //
    // private static final double CONFIDENCE_95 = 1.96;
    // private static double[] totalSitesOpen;
    //
    // // perform independent trials on an n-by-n grid
    // public PercolationStatsConcurrent(int n, int trials) {
    //     if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
    //     totalSitesOpen = new double[trials];
    //
    //     long startTime = System.nanoTime();
    //     ExecutorService executorService = Executors.newFixedThreadPool(
    //             Runtime.getRuntime().availableProcessors());
    //     for (int x = 0; x < trials; x++) {
    //         int j = x;
    //         executorService.execute(() -> runPercolation(n, j));
    //     }
    //
    //     executorService.shutdown();
    //     try {
    //         if (!executorService.awaitTermination(600, TimeUnit.SECONDS)) {
    //             executorService.shutdownNow();
    //         }
    //     }
    //     catch (InterruptedException e) {
    //         executorService.shutdownNow();
    //         Thread.currentThread().interrupt();
    //     }
    //     long endTime = System.nanoTime();
    //     StdOut.println("total execution time: " + (endTime - startTime) / 1000000);
    //     //        for (double x:totalSitesOpen) StdOut.println("value is: " + x);
    // }
    //
    // public double mean() {
    //     return StdStats.mean(totalSitesOpen);
    // }
    //
    // public double stddev() {
    //     return StdStats.stddev(totalSitesOpen);
    // }
    //
    // public double confidenceLo() {
    //     return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(totalSitesOpen.length);
    // }
    //
    // public double confidenceHi() {
    //     return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(totalSitesOpen.length);
    // }
    //
    // public static void main(String[] args) {
    //     // exercise2_unions.PercolationStats experiment = new exercise2_unions.PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    //     PercolationStatsConcurrent experiment = new PercolationStatsConcurrent(200, 100);
    //     StdOut.println(experiment.mean());
    //     StdOut.println(experiment.stddev());
    //     StdOut.println("95% confidence interval = [" + experiment.confidenceLo() + ", "
    //                            + experiment.confidenceHi() + "]");
    // }
    //
    // private void runPercolation(int n, int x) {
    //     Percolation percolation = new Percolation(n);
    //     int row, col, actions = 0;
    //
    //     while (!percolation.percolates()) {
    //         actions++;
    //         // StdOut.println("actions: " + actions);
    //         do {
    //             row = StdRandom.uniformInt(n) + 1;
    //             col = StdRandom.uniformInt(n) + 1;
    //         } while (percolation.isOpen(row, col));
    //         // StdOut.println("row is: " + row + " and col is: " + col);
    //         percolation.open(row, col);
    //         // StdOut.println(percolation.numberOfOpenSites());
    //     }
    //
    //     // StdOut.println("total actions: " + actions);
    //     // StdOut.println("how many are open: " + (actions / (double) (n * n)));
    //     totalSitesOpen[x] = actions / (double) (n * n);
    // }
}
