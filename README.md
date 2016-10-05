- [**Algorithms**](#--algorithms--)
  * [Sorting](#sorting)
    + [Selection sort](#selection-sort)
    + [Insertion sort](#insertion-sort)
    + [Shell sort](#shell-sort)
    + [Merge sort top-down](#merge-sort-top-down)
    + [Merge sort bottom-up](#merge-sort-bottom-up)
    + [Heap sort](#heap-sort)
    + [Quick sort](#quick-sort)
    + [Quick sort(3-way](#quick-sort-3-way)
    + [LSD sort](#lsd-sort)
    + [MSD sort](#msd-sort)
    + [Quick sort 3-way strings](#quick-sort-3-way-strings)
  * [String search](#string-search)
    + [Knuth-Morris-Pratt](#knuth-morris-pratt)
    + [Boyer-Moore](#boyer-moore)
    + [Rabin-Karp](#rabin-karp)
    + [Regular expressions](#regular-expressions)
  * [Graph](#graph)
    + [Depth-first search](#depth-first-search)
    + [Breadth-first search](#breadth-first-search)
    + [Connected components](#connected-components)
    + [Directed depth-first search](#directed-depth-first-search)
    + [Topological sort](#topological-sort)
    + [Strongly connected components Kosaraju-Sharir](#strongly-connected-components-kosaraju-sharir)
    + [Lazy Prim minimum spanning tree](#lazy-prim-minimum-spanning-tree)
    + [Prim minimum spanning tree](#prim-minimum-spanning-tree)
    + [Kruskal minimum spanning tree](#kruskal-minimum-spanning-tree)
    + [Dijkstra shortest paths](#dijkstra-shortest-paths)
    + [Acyclic shortest paths](#acyclic-shortest-paths)
    + [Bellman-Ford shortest paths](#bellman-ford-shortest-paths)
    + [Ford-Fulkerson network flow](#ford-fulkerson-network-flow)
  * [Data compression](#data-compression)
    + [Run length encoding](#run-length-encoding)
    + [Huffman](#huffman)
    + [LZW](#lzw)
- [**Data structures**](#--data-structures--)
  * [Union find](#union-find)
    + [Quick find](#quick-find)
    + [Quick union](#quick-union)
    + [Weighted union find](#weighted-union-find)
  * [Heap](#heap)
    + [Priority queue](#priority-queue)
  * [Tree](#tree)
    + [Binary search tree](#binary-search-tree)
    + [Red-black binary search tree](#red-black-binary-search-tree)
    + [B-Tree](#b-tree)
  * [Trie](#trie)
    + [R-way trie](#r-way-trie)
    + [Ternary trie](#ternary-trie)
  * [Hash table](#hash-table)
    + [Separate chaining hash table](#separate-chaining-hash-table)
    + [Linear probing hash table](#linear-probing-hash-table)
  * [Graph](#graph-1)
    + [Undirected graph](#undirected-graph)
    + [Directed graph](#directed-graph)
    + [Edge](#edge)
    + [Edge-weighted graph](#edge-weighted-graph)
    + [Directed edge](#directed-edge)
    + [Directed edge-weighted graph](#directed-edge-weighted-graph)
  * [Other](#other)
    + [Suffix array](#suffix-array)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
# **Algorithms** #
## Sorting ##
In this section various sorting algorithms can be found. For each algorithms you can find main parameters, brief description and link to implementation.
### Selection sort ###
**Description:** Find the smallest item in the array and exchange it with the first entry (itself if the first entry is already the smallest). Then, find the next smallest item and exchange it with the second entry. Continue in this  way until the entire array is sorted. 

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Selection.java)

**Stable:** no

**In place:** yes

**Time complexity:** O(N<sup>2</sup>)

**Space complexity:** O(1)
### Insertion sort ###
**Description:** For each i from 0 to N-1, exchange a[i] with the entries that are smaller in a[0] through a[i-1]. As the index i travels from left to right, the entries to its left are in sorted order in the array, so the array is fully sorted when i reaches the right end.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Insertion.java)

**Stable:** yes

**In place:** yes

**Time complexity:** between O(N) and O(N<sup>2</sup>), depends on order of items

**Space complexity:** O(1)
### Shell sort ###
**Description:** For each h, to use insertion sort independently on each of the h subsequences. Because the subsequences are independent, we can use an even simpler approach: when h-sorting the array, we insert each item among the previous items in its h-subsequence by exchanging it with those that have larger keys (moving them each one position to the right in the subsequence). We accomplish this task by using the insertion-sort code, but modified to decrement by h instead of 1 when moving through the array. This observation reduces the shellsort implementation to an insertion-sort-like pass through the array for each increment.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Shell.java)

**Stable:** no

**In place:** yes

**Time complexity:** O(NlogN)?, O(N<sup>6/5</sup>)?

**Space complexity:** O(1)
### Merge sort (top-down) ###
**Description:** To sort a subarray a[lo..hi] we divide it into two parts: a[lo..mid] and a[mid+1..hi], sort them independently (via recursive calls), and merge the resulting ordered subarrays to produce the result.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Merge.java)

**Stable:** yes

**In place:** no

**Time complexity:** O(NlogN)

**Space complexity:** O(N)
### Merge sort (bottom-up) ###
**Description:** Start by doing a pass of 1-by-1 merges (considering individual items as subarrays of size 1), then a pass of 2-by-2 merges (merge subarrays of size 2 to make subarrays of size 4), then 4-by-4 merges, and so forth. The second subarray may be smaller than the first in the last merge on each pass (which is no problem for merge()), but otherwise all merges involve subarrays of equal size, doubling the sorted subarray size for the next pass.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/MergeBU.java)

**Stable:** yes

**In place:** no

**Time complexity:** O(NlogN)

**Space complexity:** O(N)
### Heap sort ###
**Description:** A clever method that is much more efficient is to proceed from right to left, using sink() to make subheaps as we go. Every position in the array is the root of a small subheap; sink() works for such subheaps, as well. If the two children of a node are heaps, then calling sink() on that node makes the subtree rooted at the parent a heap. This process establishes the heap order inductively. The scan starts halfway back through the array because we can skip the subheaps of size 1. The scan ends at position 1, when we finish building the heap with one call to sink().
Most of the work during heapsort is done during the second phase, where we remove the largest remaining item from the heap and put it into the array position vacated as the heap shrinks. This process is a bit like selection sort (taking the items in decreasing order instead of in increasing order), but it uses many fewer compares because the heap provides a much more efficient way to find the largest item in the unsorted part of the array.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Heap.java)

**Stable:** no

**In place:** yes

**Time complexity:** O(NlogN)

**Space complexity:** O(1)
### Quick sort ###
**Description:** First, we arbitrarily choose a[lo] to be the partitioning item—the one that will go into its final position. Next, we scan from the left end of the array until we find an entry greater than (or equal to) the partitioning item, and we scan from the right end of the array until we find an entry less than (or equal to) the partitioning item. The two items that stopped the scans are out of place in the final partitioned array, so we exchange them. Continuing in this way, we ensure that no array entries to the left of the left index i are greater than the partitioning item, and no array entries to the right of the right index j are less than the partitioning item. When the scan indices cross, all that we need to do to complete the partitioning process is to exchange the partitioning item a[lo] with the rightmost entry of the left subarray (a[j]) and return its index j. Then procedure is recursively repeated for left and right subarrays.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Quick.java)

**Stable:** no

**In place:** yes

**Time complexity:** O(NlogN), probabilistic guarantee

**Space complexity:** O(lgN)
### Quick sort (3-way) ###
**Description:** This sort partitions to put keys equal to the partitioning element in place and thus does not have to include those keys in the subarrays for the recursive calls. It is far more efficient than the standard quick sort implementation for arrays with large numbers of duplicate keys.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/general/Quick3way.java)

**Stable:** no

**In place:** yes

**Time complexity:** between O(N) and O(NlogN), probabilistic, depends on distribution of input keys

**Space complexity:** O(lgN)
### LSD sort ###
**Description:** Sorting such strings can be done with key-indexed counting. If the strings are each of length W, we sort the strings W times with key-indexed counting, using each of the positions as the key, proceeding from right to left. 
The first step is to count the frequency of occurrence of each key value, using an int array count[]. For each  item, we use the key to access an entry in count[] and increment that entry. If the key value is r, we increment count[r+1].
Next, we use count[] to compute, for each key value, the starting index positions in the sorted order of items with that key. In general, to get the starting index for items with any given key value we sum the frequency counts of smaller values. For each key value r, the sum of the counts for key values less than r+1 is equal to the sum of the counts for key values less than r plus count[r], so it is easy to proceed from left to right to transform
count[] into an index table that we can use to sort the data.
With the count[] array transformed into an index table, we accomplish the actual sort by moving the items to an auxiliary array aux[]. We move each item to the position in aux[] indicated by the count[] entry corresponding to its key, and then increment that entry to maintain the following invariant for count[]: for each key value r, count[r] is the index of the position in aux[] where the next item with key value r (if any) should be placed.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/string/LSD.java)

**Stable:** yes

**In place:** no

**Time complexity:** O(NW), W - max length

**Space complexity:** O(N)
### MSD sort ###
**Description:** To implement a general-purpose string sort, where strings are not necessarily all the same length, we consider the characters in left-to-right order. We know that strings that start with a should appear before strings that start with b, and so forth. The natural way to implement this idea is a recursive method known as most-significant-digit-first (MSD) string sort. We use key-indexed counting to sort the strings according to their first character, then (recursively) sort the subarrays corresponding to each character (excluding the first character, which we know to be the same for each string in each subarray). Like quicksort, MSD string sort partitions the array into subarrays that can be sorted independently to complete the job, but it partitions the array into one subarray for each possible value of the first character, instead of the two or three partitions in quicksort.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/string/MSD.java)

**Stable:** yes

**In place:** no

**Time complexity:** between O(N) and O(Nw), w - average length

**Space complexity:** O(N + WR), W - max length, R - alphabet
### Quick sort (3-way strings) ###
**Description:** We can also adapt quicksort to MSD string sorting by using 3-way partitioning on the leading character of the keys, moving to the next character on only the middle subarray (keys with leading character equal to the partitioning character). This method is not difficult to implement: we just add an argument to the recursive method in MSD algorithm, that keeps track of the current character, adapt the 3- way partitioning code to use that character, and appropriately modify the recursive calls.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/sort/string/Quick3string.java)

**Stable:** no

**In place:** yes

**Time complexity:** between O(N) and O(Nw), w - average length

**Space complexity:** O(W + logN), W - max length
## String search ##
### Knuth-Morris-Pratt ###
**Description:** In KMP substring search, we never back up the text pointer i, and we use an array dfa[][] to record how far to back up the pattern pointer j when a mismatch is detected. For every character c, dfa[c][j] is the pattern position to compare against the next text position after comparing c with pat.charAt(j).
Once we have computed the dfa[][] array, we have the substring search method : when i and j point to mismatching characters (testing for a pattern match beginning at position i-j+1 in the text string), then the next possible position for a pattern match is beginning at position i-dfa[txt.charAt(i)][j]. But by construction, the first dfa[txt.charAt(i)][j] characters at that position match the first dfa[txt.charAt(i)][j] characters of the pattern, so there is no need to back up the i pointer: we can simply set j to dfa[txt.charAt(i)][j] and increment i, which is precisely what we do when i and j point to matching characters.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/string_search/KMP.java)

**Backup:** no

**Correct:** yes

**Operation count:** 2N guarantee,  1.1N typical

**Space complexity:** MR, R - alphabet, M - pattern length
### Boyer-Moore ###
**Description:** When backup in the text string is not a problem, we can develop a significantly faster substring-searching method by scanning the pattern from right to left when trying to match it against the text. For example, when searching for the substring BAABBAA , if we find matches on the seventh and sixth characters but not on the fifth, then we can immediately slide the pattern seven positions to the right, and check the 14th character in the text next, because our partial match found XAA where X is not B , which does not appear elsewhere in the pattern.
To implement the mismatched character heuristic, we use an array right[] that gives, for each character in the alphabet, the index of its rightmost occurrence in the pattern (or -1 if the character is not in the pattern). This value tells us precisely how far to skip if that character appears in the text and causes a mismatch during the string search.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/string_search/BoyerMoore.java)

**Backup:** yes

**Correct:** yes

**Operation count:** 3N guarantee,  N/M typical, M - pattern length

**Space complexity:** R, R - alphabet
### Rabin-Karp ###
**Description:** The method developed by M. O. Rabin and R. A. Karp is a completely different approach to substring search that is based on hashing. We compute a hash function for the pattern and then look for a match by using the same hash function for each possible M-character substring of the text. If we find a text substring with the same hash value as the pattern, we can check for a match.
The Rabin-Karp method is based on efficiently computing the hash function for position i+1 in the text, given its value for position i. It follows directly from a simple mathematical formulation.
Monte Carlo - only hashes are compared. Hash collisions are ignored.
Las Vegas - hash collisions are checked.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/string_search/RabinKarp.java)

**Backup:** no (Monte Carlo), yes (Las Vegas)

**Correct:** yes (Las Vegas),  yes probabilistic (Monte Carlo)

**Operation count:** 7N (Monte Carlo), 7N probabilistic (Las Vegas)

**Space complexity:** 1
### Regular expressions ###
**Description:** To handle regular expressions, we consider a more powerful abstract machine. Because of the or operation, the automaton cannot determine whether or not the pattern could occur at a given point by examining just one character; indeed, because of closure, it cannot even determine how many characters might need to be examined before a mismatch is discovered. To overcome these problems, we will endow the automaton with the power of nondeterminism: when faced with more than one way to try to match the pattern, the machine can “guess’’ the right one! This power might seem to you to be impossible to realize, but we will see that it is easy to write a program to build a nondeterministic finite-state automaton (NFA) and to efficiently simulate its operation.
The idea of an automaton that can guess the state transitions it needs to get to the accept state is like writing a program that can guess the right answer to a problem: it seems ridiculous. On reflection, you will see that the task is conceptually not at all difficult: we make sure that we check all possible sequences of state transitions, so if there is one that gets to the accept state, we will find it.
We keep the RE itself in an array re[] of char values that defines the match transitions (if re[i] is in the alphabet, then there is a match transition from i to i+1). The natural representation for the e-transitions is a digraph—they are directed edges (red edges in our diagrams) connecting vertices between 0 and M (one for each state). Accordingly, we represent all the e-transitions as a digraph G.
To simulate an NFA, we keep track of the set of states that could possibly be encountered while the automaton is examining the current input character. The key computation is the familiar multiple-source reachability . To initialize this set, we find the set of states reachable via e-transitions from state 0. For each such state, we check whether a match transition for the first input character is possible. This check gives us the set of possible states for the NFA just after matching the first input character. To this set, we add all states that could be reached via e-transitions from one of the states in the set. Given the set of possible states for the NFA just after matching the first character in the input, the solution to the multiple-source reachability problem in the e-transition digraph gives the set of states that could lead to match transitions for the second character in the input.
Iterating this process until all text characters are exhausted leads to one of two outcomes: possible states set contains accept state or not.The first of these outcomes indicates that there is some sequence of transitions that takes the NFA to the accept state, so we report success. The second of these outcomes indicates that the NFA always stalls on that input, so we report failure.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/string_search/NFA.java)

**Time complexity:** O(NM), M - pattern length

**Space complexity:** O(M)
## Graph ##
### Depth-first search ###
**Description:** To search a graph, invoke a recursive method that visits vertices. To visit a vertex mark it as having been visited and visit (recursively) all the vertices that are adjacent to it and that have not yet been marked. The recursive method marks the given vertex and calls itself for any unmarked vertices on its adjacency list. If the graph is connected, every adjacency-list entry is checked.
Adding instance variable edgeTo[] of int values that serves the purpose of the ball of string in Tremaux exploration: it gives a way to find a path back to s for every vertex connected to s. Instead of just keeping track of the path from the current vertex back to the start, we remember a path from each vertex to the start. To accomplish this, we remember the edge v-w that takes us to each vertex w for the first time, by setting edgeTo[w] to v. In other words, v-w is the last edge on the known path from s to w. The result of the search is a tree rooted at the source; edgeTo[] is a parent-link representation of that tree.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/search/DepthFirstPaths.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(V) 
### Breadth-first search ###
**Description:** This algorithm is useful for finding shortest paths. To find a shortest path from s to v, we start at s and check for v among all the vertices that we can reach by following one edge, then we check for v among all the vertices that we can reach from s by following two edges, and so forth. BFS is analogous to a group of searchers exploring by fanning out in all directions, each unrolling his or her own ball of string. When more than one passage needs to be explored, we imagine that the searchers split up to explore all of them; when two groups of searchers meet up, they join forces (using the ball of string held by the one getting there first). In BFS, we want to explore the vertices in order of their distance from the source. It turns out that this order is easily arranged: use a (FIFO) queue instead of a (LIFO) stack. We choose, of the passages yet to be explored, the one that was least recently encountered.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/search/BreadthFirstPaths.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(V)
### Connected components ###
**Description:** CC uses our marked[] array to find a vertex to serve as the starting point for a depth first search in each component. The first call to the recursive DFS is for vertex 0 - it marks all vertices connected to 0. Then the for loop in the constructor looks for an unmarked vertex and calls the recursive dfs() to mark all vertices connected to that vertex. Moreover, it maintains a vertex-indexed array id[] that associates the same int value to every vertex in each component. This array makes the implementation of connected() simple.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/connected_components/CC.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(V)  
### Directed depth-first search ###
**Description:** This algorithm is similar to usual depth first search. The only difference is that we use directed graph instead of usual graph, which means that when we are adding edges to it, we are adding only v->w, without adding w->v edge. Also here we have constructor, which allows us to test multiple-source reachability.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/search/DirectedDFS.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(V) 
### Topological sort ###
**Description:** Solving the directed cycle detection problem thus answers the following question: Is a given digraph a DAG ? Developing a depth-first-search-based solution to this problem is not difficult, based on the fact that the recursive call stack maintained by the system represents the “current” directed path under consideration (like the string back to the entrance in Tremaux maze exploration). If we ever find a directed edge v->w to a vertex w that is on that stack, we have found a cycle, since the stack is evidence of a directed path from w to v, and the edge v->w completes the cycle. Moreover, the absence of any such back edges implies that the graph is acyclic.
Topological sort is based on the idea that depth-first search visits each vertex exactly once. If we save the vertex given as argument to the recursive dfs() in a data structure, then iterate through that data structure, we see all the graph vertices, in order determined by the nature of the data structure and by whether we do the save before or after the recursive calls. Three vertex orderings are of interest in typical applications:

■ Preorder : Put the vertex on a queue before the recursive calls.

■ Postorder : Put the vertex on a queue after the recursive calls.

■ Reverse postorder : Put the vertex on a stack after the recursive calls.
Reverse postorder in DAG is a topological sort.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/topological/Topological.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(V) 
### Strongly connected components (Kosaraju-Sharir) ###
**Description:** Two vertices v and w are strongly connected if they are mutually reachable: that is, if there is a directed path from v to w and a directed path from w to v. A digraph is strongly connected if all its vertices are strongly connected to one another.
The equivalence classes are maximal subsets of vertices that are strongly connected to one another, with each vertex in exactly one subset. We refer to these subsets as strongly connected components, or strong components for short.
Remarkably, the implementation KosarajuSCC does the job with just a few lines of code added to CC, as follows:

■ Given a digraph G, use DepthFirstOrder to compute the reverse postorder of its reverse, G R.

■ Run standard DFS on G, but consider the unmarked vertices in the order just computed instead of the standard numerical order.

■ All vertices reached on a call to the recursive dfs() from the constructor are in a strong component (!), so identify them as in CC.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/connected_components/KosarajuSharirSCC.java)

**Time complexity:** O(E+V), E - edges, V - vertices

**Space complexity:** O(E+V) 
### Lazy Prim minimum spanning tree ###
**Description:** A cut of a graph is a partition of its vertices into two nonempty disjoint sets. A crossing edge of a cut is an edge that connects a vertex in one set with a vertex in the other.
Given any cut in an edge-weighted graph, the crossing edge of minimum weight is in the MST of the graph.
Under our assumption that edge weights are distinct, every connected graph has a unique MST and the cut property says that the shortest crossing edge for every cut must be in the MST.
Attach a new edge to a single growing tree at each step. Start with any vertex as a single-vertex tree; then add V-1 edges to it, always taking next (colouring black) the minimum weight edge that connects a vertex on the tree to a vertex not yet on the tree (a crossing edge for the cut defined by tree vertices).
Each time that we add an edge to the tree, we also add a vertex to the tree. To maintain the set of crossing edges, we need to add to the priority queue all edges from that vertex to any non-tree vertex (using marked[] to identify such edges). But we must do more: any edge connecting the vertex just added to a tree vertex that is already on the priority queue now becomes ineligible (it is no longer a crossing edge because it connects two tree vertices). An eager implementation of Prim’s algorithm would remove such edges from the priority queue; we first consider a simpler lazy implementation of the algorithm where we leave such edges on the priority queue, deferring the eligibility test to when we remove them.
After having added V vertices (and V-1 edges), the MST is complete. The remaining edges on the priority queue are ineligible, so we need not examine them again.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/mst/LazyPrimMST.java)

**Time complexity:** O(ElogE), E - edges

**Space complexity:** O(E) 
### Prim minimum spanning tree ###
**Description:** To improve the LazyPrimMST, we might try to delete ineligible edges from the priority queue, so that the priority queue contains only the crossing edges between tree vertices and non-tree vertices. But we can eliminate even more edges. The key is to note that our only interest is in the minimal edge from each non-tree vertex to a tree vertex. When we add a vertex v to the tree, the only possible change with respect to each nontree vertex w is that adding v brings w closer than before to the tree. In short, we do not need to keep on the priority queue all of the edges from w to tree vertices—we just need to keep track of the minimum-weight edge and check whether the addition of v to the tree necessitates that we update that minimum (because of an edge v-w that has lower weight), which we can do as we process each edge in v’s adjacency list. In other words, we maintain on the priority queue just one edge for each non-tree vertex w : the shortest edge that connects it to the tree. Any longer edge connecting w to the tree will become ineligible at some point, so there is no need to keep it on the priority queue.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/mst/PrimMST.java)

**Time complexity:** O(ElogV), E - edges, V - vertices

**Space complexity:** O(V) 
### Kruskal minimum spanning tree ###
**Description:** The second MST algorithm that we consider in detail is to process the edges in order of their weight values (smallest to largest), taking for the MST (colouring black) each edge that does not form a cycle with edges previously added, stopping after adding V-1 edges have been taken. The black edges form a forest of trees that evolves gradually into a single tree, the MST.
Kruskal’s algorithm is also not difficult to implement, given the basic algorithmic tools: we use a priority queue to consider the edges in order by weight, a union-find data structure to identify those that cause cycles, and a queue to collect the MST edges.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/mst/KruskalMST.java)

**Time complexity:** O(ElogE), E - edges, V - vertices

**Space complexity:** O(E) 
### Dijkstra shortest paths ###
**Description:** We build the MST by attaching a new edge to a single growing tree at each step. Dijkstra’s algorithm is an analogous scheme to compute an SPT. We begin by initialising dist[s] to 0 and all other distTo[] entries to positive infinity, then we relax and add to the tree a non-tree vertex with the lowest distTo[] value, continuing until all vertices are on the tree or no non-tree vertex has a finite distTo[] value.
To implement Dijkstra’s algorithm we add to our distTo[] and edgeTo[] data structures an index priority queue pq to keep track of vertices that are candidates for being the next to be relaxed. Recall that an IndexMinPQ allows us to associate indices with keys (priorities) and to remove and return the index corresponding to the lowest key. For this application, we always associate a vertex v with distTo[v], and we have a direct and immediate implementation of Dijkstra’s algorithm as stated. Moreover, it is immediate by induction that the edgeTo[] entries corresponding to reachable vertices form a tree, the SPT.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/shortest_path/DijkstraSP.java)

**Time complexity:** O(ElogV), E - edges, V - vertices

**Space complexity:** O(V) 
### Acyclic shortest paths ###
**Description:** These algorithms are straightforward extensions to the algorithm for topological sort in DAGs. Specifically, vertex relaxation, in combination with topological sorting, immediately presents a solution to the single-source shortest-paths problem for edge-weighted DAGs. We initialize distTo[s] to 0 and all other distTo[] values to infinity, then relax the vertices, one by one, taking the vertices in topological order.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/shortest_path/AcyclicSP.java)

**Time complexity:** O(E + V), E - edges, V - vertices

**Space complexity:** O(V) 
### Bellman-Ford shortest paths ###
**Description:** The following method solves the single source shortest-paths problem from a given source s for any edge-weighted digraph with V vertices and no negative cycles reachable from s: Initialise distTo[s] to 0 and all other distTo[] values to infinity. Then, considering the digraph’s edges in any order, relax all edges. Make V such passes.
Specifically, we can easily determine a priori that numerous edges are not going to lead to a successful relaxation in any given pass: the only edges that could lead to a change in distTo[] are those leaving a vertex whose distTo[] value changed in the previous pass. To keep track of such vertices, we use a FIFO queue.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/shortest_path/BellmanFordSP.java)

**Time complexity:** O(EV), E - edges, V - vertices

**Space complexity:** O(V) 
### Ford-Fulkerson network flow ###
**Description:** Start with zero flow everywhere. Increase the flow along any augmenting path from source to sink (with no full forward edges or empty backward edges), continuing until there are no such paths in the network.
To improve the algorithm such that it always finds a maxflow, we consider a more general way to increase the flow, along a path from source to sink through the network’s underlying undirected graph. The edges on any such path are either forward edges, which go with the flow (when we traverse the path from source to sink, we traverse the edge from its source vertex to its destination vertex), or backward edges, which go against the flow (when we traverse the path from source to sink, we traverse the edge from its destination vertex to its  source vertex). Now, for any path with no full forward edges and no empty backward edges, we can increase the amount of flow in the network by increasing flow in forward edges and decreasing flow in backward edges. The amount by which the flow can be increased is limited by the minimum of the unused capacities in the forward edges and the flows in the backward edges. Such a path is called an augmenting path.

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/graph/FordFulkerson.java)

**Time complexity:** O(VE<sup>2</sup>), E - edges, V - vertices

**Space complexity:** O(V) 
## Data compression ##
### Run length encoding ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/data_compression/RunLength.java)
### Huffman ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/data_compression/Huffman.java)
### LZW ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/algorithms/data_compression/LZW.java)
# **Data structures** #
## Union find ##
### Quick find ###
**Description:** One approach is to maintain the invariant that p and q are connected if and only if id[p] is equal to id[q]. In other words, all sites in a component must have the same value in id[]. This method is called quick-find because find(p) just returns id[p], which immediately implies that connected(p, q) reduces to just the test id[p] == id[q] and returns true if and only if p and q are in the same component. To maintain the invariant for the call union(p, q), we first check whether they are already in the same component, in which case there is nothing to do. Otherwise, we are faced with the situation that all of the id[] entries corresponding to sites in the same component as p have one value and all of the id[] entries corresponding to sites in the same  component as q have another value. To combine the two components into one, we have to make all of the id[] entries corresponding to both sets of sites the same value, as shown in the example at right. To do so, we go through the array, changing all the entries with values equal to id[p] to the value id[q].

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/union_find/UF.java)
### Quick union ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/union_find/QuickUnionUF.java)
### Weighted union find ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/union_find/WeightedQuickUnionUF.java)
## Heap ##
### Priority queue ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/heap/MinPQ.java)
## Tree ##
### Binary search tree ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/tree/BST.java)
### Red-black binary search tree ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/tree/RedBlackBST.java)
### B-Tree ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/tree/BTree.java)
## Trie ##
### R-way trie ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/trie/TrieST.java)
### Ternary trie ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/trie/TST.java)
## Hash table ##
### Separate chaining hash table ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/hash_table/SeparateChainingHashST.java)
### Linear probing hash table ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/hash_table/LinearProbingHashST.java)
## Graph ##
### Undirected graph ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/Graph.java)
### Directed graph ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/Digraph.java)
### Edge ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/Edge.java)
### Edge-weighted graph ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/EdgeWeightedGraph.java)
### Directed edge ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/DirectedEdge.java)
### Directed edge-weighted graph ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/graph/EdgeWeightedDigraph.java)
## Other ##
### Suffix array ###
**Description:** TBD

[Source code](https://github.com/Nobodyhave/Algorithms/blob/master/src/data_structures/SuffixArray.java)
