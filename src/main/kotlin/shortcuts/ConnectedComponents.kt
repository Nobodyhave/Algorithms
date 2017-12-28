package shortcuts

class ConnectedComponents(G: Graph) {
    private val marked = BooleanArray(G.size())
    private val id = IntArray(G.size())
    private val size = IntArray(G.size())
    private var count: Int = 0

    init {
        for (v in 0 until G.size()) {
            if (!marked[v]) {
                dfs(G, v)
                count++
            }
        }
    }

    private fun dfs(G: Graph, v: Int) {
        marked[v] = true
        id[v] = count
        size[count]++
        G.adj(v).stream().filter { w -> !marked[w] }.forEach { w -> dfs(G, w!!) }
    }

    fun id(v: Int): Int {
        return id[v]
    }

    fun size(v: Int): Int {
        return size[id[v]]
    }

    fun count(): Int {
        return count
    }

    fun connected(v: Int, w: Int): Boolean {
        return id(v) == id(w)
    }

    fun areConnected(v: Int, w: Int): Boolean {
        return id(v) == id(w)
    }
}