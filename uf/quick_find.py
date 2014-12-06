import sys

class QuickFind:

    def __init__(self, n):
        self.a = [i for i in range(n)]

    def union(self, p, q):
        pid = self.a[p]
        qid = self.a[q]
        for i, _ in enumerate(self.a):
            if self.a[i] == pid:
                self.a[i] = qid

    def find(self, p, q):
        return self.a[p] == self.a[q]

def main():
    pairs = [map(int, p.split('-')) for p in sys.stdin.readline().split()]
    qf = QuickFind(10)
    for p in pairs:
        qf.union(p[0], p[1])
    print(" ".join(map(str, qf.a)))


if __name__ == "__main__":
    main()
