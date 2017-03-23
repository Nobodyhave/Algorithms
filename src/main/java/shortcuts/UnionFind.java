/******************************************************************************
 * Compilation:  javac WeightedQuickUnionUF.java
 * Execution:  java WeightedQuickUnionUF < input.txt
 * Dependencies: StdIn.java StdOut.java
 * <p>
 * Weighted quick-union (without path compression).
 ******************************************************************************/

package shortcuts;

public class UnionFind {
    private final int[] parent;
    private final int[] size;
    private int count;

    public UnionFind(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        final int rootP = find(p);
        final int rootQ = find(q);
        if (rootP == rootQ) return;

        // Make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}
