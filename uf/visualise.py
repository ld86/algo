import sys
from ete2 import Tree

def get_roots(nodes):
    roots = []
    for i,e in enumerate(nodes):
        if i == e:
            roots.append(e)
    return roots

def get_inverted(nodes):
    inverted = {}
    for i,e in enumerate(nodes):
        if i != e:
            if e in inverted:
                inverted[e].append(i)
            else:
                inverted[e] = [i]
    return inverted

def get_tree(root, forest):
    if root not in forest:
        return str(root)
    level = []
    for child in forest[root]:
        level.append(get_tree(child, forest))
    return "(" + ",".join(level) + ")"


def main():
    nodes = map(int, sys.stdin.readline().split())
    forest = get_inverted(nodes)
    trees = []
    for root in get_roots(nodes):
        trees.append(get_tree(root, forest))
    trees = "(" + ",".join(trees) + ")"
    t = Tree(trees + ";")
    t.show()

    print(trees)
    print(forest)
    print(get_roots(nodes))
    print(t)


if __name__ == "__main__":
    main()
