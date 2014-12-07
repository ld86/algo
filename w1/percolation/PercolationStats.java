import java.util.Random;
import java.lang.Math;

public class PercolationStats {

    private double[] Stats;
    int N;
    int T;
    Random random = new Random();

    public PercolationStats(int n, int t) {
        Stats = new double[t];
        N = n;
        T = t;

        for (int i = 0; i < t; ++i) {
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                p.open(random(), random());
            }
            int open = 0;
            for (int a = 1; a < N + 1; ++a) {
                for (int b = 1; b < N + 1; ++b) {
                    if (p.isOpen(a, b)) {
                        open++;
                    }
                }
            }
            Stats[i] = (double)open / (double)(N * N);
        }
    }

    private int random() {
        return Math.abs(random.nextInt()) % N + 1;
    }

    private double mean() {
        double sum = 0;
        for (int i = 0; i < T; ++i) {
            sum += Stats[i];
        }
        return sum / T;
    }

    private double stddev() {
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < T; ++i) {
            sum += (Stats[i] - mean) * (Stats[i] - mean);
        }
        return sum / (T - 1);
    }

    public double confidenceLo() {
        return mean() - (1.96 * Math.sqrt(stddev()))/Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean() + (1.96 * Math.sqrt(stddev()))/Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }
}
