import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
	private final double[] results;
	private final int n;
	private final int trials;
	private final double mean;
	private final double stddev;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
		this.n = n;
		this.trials = trials;
		results = new double[trials];
		for (int i = 0; i < trials; i++) {
			results[i] = percolPercentage();
		}
		this.mean = mean();
		this.stddev = stddev();
	}
	public double mean() {
		return StdStats.mean(results);
	}
	public double stddev() {
		return StdStats.stddev(results);
	}
	public double confidenceLo() {
		return mean - 1.96 * stddev / Math.sqrt(trials);
	}
	public double confidenceHi() {
		return mean + 1.96 * stddev / Math.sqrt(trials);
	}

	private double percolPercentage() {
		Percolation perc = new Percolation(n);
		while (!perc.percolates()) {
			int i = StdRandom.uniform(1, n+1);
			int j = StdRandom.uniform(1, n+1);
			if (perc.isOpen(i, j)) continue;
			perc.open(i, j);
		}
		double square = n*n;
		return perc.numberOfOpenSites()/square;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.printf("mean                    = %f%n", ps.mean);
        StdOut.printf("stddev                  = %f%n", ps.stddev);
        StdOut.printf("95%% confidence interval = [%f, %f%n] ", ps.confidenceLo(), ps.confidenceHi());
	}
}