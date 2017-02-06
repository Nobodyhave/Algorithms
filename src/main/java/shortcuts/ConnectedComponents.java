package shortcuts;

public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public ConnectedComponents(Graph G) {
        marked = new boolean[G.size()];
        id = new int[G.size()];
        size = new int[G.size()];
        for (int v = 0; v < G.size(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        G.adj(v).stream().filter(w -> !marked[w]).forEach(w -> dfs(G, w));
    }

    public int id(int v) {
        return id[v];
    }

    public int size(int v) {
        return size[id[v]];
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    public boolean areConnected(int v, int w) {
        return id(v) == id(w);
    }
}