package shortcuts;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.size()];
        distTo = new int[G.size()];
        edgeTo = new int[G.size()];
        bfs(G, s);
    }

    public BreadthFirstSearch(Graph G, List<Integer> sources) {
        marked = new boolean[G.size()];
        distTo = new int[G.size()];
        edgeTo = new int[G.size()];
        for (int v = 0; v < G.size(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }


    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < G.size(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();
            G.adj(v).stream().filter(w -> !marked[w]).forEach(w -> {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                marked[w] = true;
                q.add(w);
            });
        }
    }

    private void bfs(Graph G, List<Integer> sources) {
        Queue<Integer> q = new LinkedList<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.add(s);
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            G.adj(v).stream().filter(w -> !marked[w]).forEach(w -> {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                marked[w] = true;
                q.add(w);
            });
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}

