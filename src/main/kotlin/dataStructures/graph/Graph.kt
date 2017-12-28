package dataStructures.graph

class Graph(private val V: Int) {
    private var edgeCount: Int = 0
    private val adj = Array(V, { ArrayList<Int>() })

    fun vertexCount(): Int {
        return V
    }

    fun edgeCount(): Int {
        return edgeCount
    }

    fun addEdge(v: Int, w: Int) {
        edgeCount++
        adj[v].add(w)
        adj[w].add(v)
    }

    fun adj(v: Int): Iterable<Int> {
        return adj[v]
    }

    fun degree(v: Int): Int {
        return adj[v].size
    }
}
