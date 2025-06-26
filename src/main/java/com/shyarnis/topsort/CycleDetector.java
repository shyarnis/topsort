package com.shyarnis.topsort;

import java.util.*;

public class CycleDetector<T> {
    private enum State { UNVISITED, VISITING, VISITED }

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
