package com.shyarnis.topsort;

import java.util.*;

/**
 * Detects cycles in a directed graph using depth-first search (DFS).
 * <p>
 * This implementation marks each node with a visitation state and performs
 * DFS traversal to detect cycles in the graph.
 *
 * @param <T> the type of node in the graph
 */
public class CycleDetector<T> {
    private enum State { UNVISITED, VISITING, VISITED }

    /**
     * Private constructor to prevent instantiation of this class.
     */
    CycleDetector() {
        // Prevent instantiation
    }
    /**
     * Checks whether the given graph contains a cycle.
     *
     * @param graph the directed graph to check for cycles
     * @return {@code true} if the graph contains at least one cycle, {@code false} otherwise
     */
    public boolean hasCycle(Graph<T> graph) {
        Map<T, State> state = new HashMap<>();
        // mark each node as unvisited
        for (T node : graph.getNodes()) {
            state.put(node, State.UNVISITED);
        }

        // if node is unvisited, check for cycle using recursion
        for (T node : graph.getNodes()) {
            if (state.get(node) == State.UNVISITED) {
                if (dfsHasCycle(node, graph, state)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Recursively performs DFS to detect a cycle starting from the given node.
     *
     * @param node  the current node being visited
     * @param graph the graph being analyzed
     * @param state a map tracking the visitation state of each node
     * @return {@code true} if a cycle is detected, {@code false} otherwise
     */
    private boolean dfsHasCycle(T node, Graph<T> graph, Map<T, State> state) {
        // mark as visiting
        state.put(node, State.VISITING);

        for (T neighbor : graph.getNeighbors(node)) {
            if (state.get(neighbor) == State.VISITING) return true;
            if (state.get(neighbor) == State.UNVISITED && dfsHasCycle(neighbor, graph, state)) return true;
        }

        // mark as visited
        state.put(node, State.VISITED);
        return false;
    }
}
