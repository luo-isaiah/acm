import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private static final double CONFIDENCE_95 = 1.96;
    /** open site percent */
    private final double[] x;
    /** experiment times */
    private final int expTimes;
    
    private Double mean;
    private Double stddev;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        
        x = new double[trials];
        expTimes = trials;
        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
               int row = StdRandom.uniform(1, n+1);
               int col = StdRandom.uniform(1, n+1);
               if (!perc.isOpen(row, col)) {
                   perc.open(row, col);
               }
           }
            x[i] = perc.numberOfOpenSites() / (double) (n*n);
        }
    }
    
    public double mean() {
        if (mean == null) {
            mean = StdStats.mean(x);
        }
        return mean;
    }
    
    public double stddev() {
        if (stddev == null) {
            stddev = StdStats.stddev(x);
        }
        return stddev;
    }
    
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(expTimes);
    }
    
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(expTimes);
    }
    
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(10, 100);
         System.out.println(ps.mean());
         System.out.println(ps.stddev());
         System.out.println(ps.confidenceLo());
         System.out.println(ps.confidenceHi());
    }
}