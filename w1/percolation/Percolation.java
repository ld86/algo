public class Percolation {
    private WeightedQuickUnionUF UF;
    private int N;
    private boolean[] Blocks;

    public Percolation(int n) {
        UF = new WeightedQuickUnionUF(n * n + 2);
        N = n;
        Blocks = new boolean[n * n];

        for (int i = 0; i < n; ++i) {
            Blocks[i] = false;
        }

        for (int i = 1; i < n + 1; ++i) {
            UF.union(0, convert(1,i) + 1);
            UF.union(n * n + 1, convert(n,i) + 1);
        }
    }

    private int convert(int i, int j) {
        return (i - 1) * N + (j - 1);
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) {
            return;
        }

        Blocks[convert(i, j)] = true;
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if (a != b && a != -b) {
                    if (isOpen(i + a, j + b)) {
                        UF.union(1 + convert(i, j), 1 + convert(i + a, j + b));
                    }
                }
            }
        }
    }

    public boolean isOpen(int i, int j) {
        if (i < 1 || i > N - 1) {
            return false;
        }
        if (j < 1 || j > N - 1) {
            return false;
        }
        return Blocks[convert(i, j)];
    }

    public boolean isFull(int i, int j) {
        return UF.connected(0, convert(i, j) + 1);
    }

    public boolean percolates() {
        return UF.connected(0, N * N + 1);
    }

    public void print() {
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < N + 1; ++j) {
                if (Blocks[convert(i, j)]) {
                    StdOut.print(isFull(i,j) ? "o" : " ");
                } else {
                    StdOut.print("x");
                }
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.print();
        StdOut.println(p.percolates());
        p.open(2, 2);
        p.print();
        StdOut.println(p.percolates());
        p.open(3, 2);
        p.print();
        StdOut.println(p.percolates());
    }
}
