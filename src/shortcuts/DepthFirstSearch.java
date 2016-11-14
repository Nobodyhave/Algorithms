package shortcuts;

import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstSearch(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.size()];
        marked = new boolean[G.size()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        G.adj(v).stream().filter(w -> !marked[w]).forEach(w -> {
            edgeTo[w] = v;
            dfs(G, w);
        });
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
