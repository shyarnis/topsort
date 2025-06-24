# Topological Sort
- A topological ordering is an ordering of the nodes in a directed edge from node A to node B, node A appears before 
node B in the ordering.
- The topological sort algorithm can be find a topological ordering in `O(V+E)` time.
- Topological orderings are **NOT** unique.
- Not every graph can have a topological ordering, a graph with graphs cannot have valid ordering.
- **DAGs** with directed edges and no cycles.
- How to verify that graph does not contain a directed cycle?
  - DFS with cycle detection
  - Kahn algorithm, BFS based topological sort
  - Tarjan's strongly connected component algorithm which can be used to find these cycles

- Every trees have topological sort since they do not contain any cycles.
  - Iterative pick of leaf nodes way up to root node

## DFS based
1. pick an unvisited node
2. begin with selected node, do DFS exploring only unvisited nodes
3. on recursive callback of DFS, add current node to topological ordering in reverse order

## Kahn's Algorithm
- It is a simple topological sort algorithm can find a topological ordering in `O(V+E)` time
- Intuition: Repeatedly remove nodes without any dependencies from the graph and add them to the topological ordering.
- As nodes without dependencies(and their outgoing edges) are removed from the graph, new nodes without dependencies
should become free.
- Repeat removing nodes without dependencies from the graph until all nodes are processed, of a cycle is discovered.
