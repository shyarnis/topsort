package com.shyarnis.topsort;

import java.util.*;

/**
 * Performs a topological sort on a directed acyclic graph (DAG) using Kahn's algorithm.
 * <p>
 * This implementation detects cycles automatically and throws an exception
 * if the graph is not a valid DAG.
 *
 * @param <T> the type of nodes in the graph
 */
public class TopologicalSort<T> {

    /**
     * The graph to be sorted topologically.
     */
    private final Graph<T> graph;

    /**
     * Constructs a new topological sorter for the given graph.
     *
     * @param graph the directed graph to sort
     */
    public TopologicalSort(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * Performs a topological sort on the graph.
     * <p>
     * The method uses Kahn's algorithm, which works by repeatedly removing
     * nodes with zero in-degree and reducing the in-degree of their neighbors.
     * <p>
     * If the graph contains a cycle, an {@link IllegalStateException} is thrown.
     *
     * @return a list of nodes in topological order
     * @throws IllegalStateException if the graph contains a cycle
     */
    public List<T> sort() {
        Map<T, Integer> inDegree = computeInDegrees();
        Queue<T> queue = new LinkedList<>();
        List<T> result = new ArrayList<>();

        // Initialize queue with nodes having 0 in-degree
        for (T node : graph.getNodes()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(current);

            for (T neighbor : graph.getNeighbors(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // cycle detection; no need for CycleDetector.java
        if (result.size() != graph.getNodes().size()) {
            throw new IllegalStateException("Graph has a cycle; topological sort is not possible.");
        }

        return result;
    }

    /**
     * Computes the in-degree (number of incoming edges) for each node in the graph.
     *
     * @return a map where keys are nodes and values are their in-degree counts
     */
    private Map<T, Integer> computeInDegrees() {
        Map<T, Integer> inDegree = new HashMap<>();

        // Initialize all nodes with 0 in-degree
        for (T node : graph.getNodes()) {
            inDegree.put(node, 0);
        }

        // Calculate in-degrees for all nodes
        for (T node : graph.getNodes()) {
            for (T neighbor : graph.getNeighbors(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        return inDegree;
    }
}