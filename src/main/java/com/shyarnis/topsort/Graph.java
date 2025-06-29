package com.shyarnis.topsort;

import java.util.*;

/**
 * A simple generic directed graph representation using an adjacency list.
 * <p>
 * Each node in the graph is represented by a key of type {@code T}, and its
 * outgoing edges are represented as a set of neighboring nodes.
 *
 * @param <T> the type of the nodes in the graph
 */
public class Graph<T> {

    /**
     * Constructs an empty directed graph.
     */
    public Graph() {
        // No initialization needed; the map is already initialized
    }

    /**
     * The internal adjacency list that maps each node to its set of outgoing neighbors.
     */
    private final Map<T, Set<T>> adjacencyList = new HashMap<>();

    /**
     * Adds a node to the graph. If the node already exists, this operation has no effect.
     *
     * @param node the node to be added
     */
    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new HashSet<>());
    }

    /**
     * Adds a directed edge from one node to another.
     * <p>
     * If either {@code from} or {@code to} nodes do not exist in the graph,
     * they are added automatically.
     *
     * @param from the source node of the edge
     * @param to   the destination node of the edge
     */
    public void addEdge(T from, T to) {
        addNode(from);
        addNode(to);
        adjacencyList.get(from).add(to);
    }

    /**
     * Returns the set of all nodes present in the graph.
     *
     * @return a set of nodes
     */
    public Set<T> getNodes() {
        return adjacencyList.keySet();
    }

    /**
     * Returns the neighbors (i.e., directly connected outgoing nodes) of a given node.
     * <p>
     * If the node does not exist in the graph, an empty set is returned.
     *
     * @param node the node whose neighbors are to be retrieved
     * @return a set of neighboring nodes, or an empty set if the node is not found
     */
    public Set<T> getNeighbors(T node) {
        return adjacencyList.getOrDefault(node, Collections.emptySet());
    }

    /**
     * Returns an unmodifiable view of the full adjacency list of the graph.
     * <p>
     * This map contains each node and its corresponding set of neighbors.
     *
     * @return an unmodifiable map representing the adjacency list
     */
    public Map<T, Set<T>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }
}
