package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] x;
    private int times;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        x = new double[T];
        times = T;
        for(int i=0; i<T; i++){
            Percolation p = pf.make(N);
            while(!p.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if(!p.isOpen(row,col)){
                    p.open(row, col);
                }
            }
            int cnt = p.numberOfOpenSites();
            x[i] = (double) cnt / (N*N);
        }
    }  // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(x);
    }                                          // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(x);
    }                                        // sample standard deviation of percolation threshold

    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu-1.96*sigma/Math.sqrt(times);
    }                                 // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu+1.96*sigma/Math.sqrt(times);
    }                                // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 30, pf);
        System.out.println("mean: " + ps.mean());
        System.out.println("stddev: " + ps.stddev());
        System.out.println("confidenceLow: " + ps.confidenceLow());
        System.out.println("confidenceHigh: " + ps.confidenceHigh());
    }
}
