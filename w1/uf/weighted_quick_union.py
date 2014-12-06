import sys

class WeightedQuickUnion:

    def __init__(self, n):
        self.a = [i for i in range(n)]
        self.sz = [1 for i in range(n)]

    def root(self, p):
        while p != self.a[p]:
            p = self.a[p]
        return p

    def union(self, p, q):
        print(self.a)
        print(self.sz)
        print(p,q)
        proot = self.root(p)
        qroot = self.root(q)
        if self.sz[proot] >= self.sz[qroot]:
            self.sz[proot] += self.sz[qroot]
            self.a[qroot] = proot
        else:
            self.sz[qroot] += self.sz[proot]
            self.a[proot] = qroot

    def find(self, p, q):
        return self.root(p) == self.root(q)

def main():
    pairs = [map(int, p.split('-')) for p in sys.stdin.readline().split()]
    qf = WeightedQuickUnion(10)
    for p in pairs:
        qf.union(p[0], p[1])
    print(" ".join(map(str, qf.a)))


if __name__ == "__main__":
    main()
