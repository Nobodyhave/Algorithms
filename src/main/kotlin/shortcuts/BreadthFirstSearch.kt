package shortcuts

import java.util.*

class BreadthFirstSearch(G: Graph, s: Int) {
    private val marked = BooleanArray(G.size())
    private val edgeTo = IntArray(G.size())
    private val distTo = IntArray(G.size())

    init {
        bfs(G, s)
    }

    private fun bfs(G: Graph, s: Int) {
        val q = LinkedList<Int>()
        for (v in 0 until G.size())
            distTo[v] = Int.MAX_VALUE
        distTo[s] = 0
        marked[s] = true
        q.add(s)

        while (!q.isEmpty()) {
            val v = q.poll()
            G.adj(v).stream().filter { w -> !marked[w] }.forEach { w ->
                edgeTo[w] = v
                distTo[w] = distTo[v] + 1
                marked[w] = true
                q.add(w)
            }
        }
    }

    fun hasPathTo(v: Int): Boolean {
        return marked[v]
    }

    fun distTo(v: Int): Int {
        return distTo[v]
    }

    fun pathTo(v: Int): Stack<Int>? {
        if (!hasPathTo(v)) return null
        val path = Stack<Int>()
        var x: Int
        x = v
        while (distTo[x] != 0) {
            path.push(x)
            x = edgeTo[x]
        }
        path.push(x)
        return path
    }
}

